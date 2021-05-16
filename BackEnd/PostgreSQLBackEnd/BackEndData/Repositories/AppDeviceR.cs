using BackEndData.InterfaceRepositories;
using BackEndModel;
using Dapper;
using Npgsql;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndData.Repositories
{
    /// <summary>
    /// clase para implementar metodos de la interfaz del modelo AppDevice
    /// </summary>
    public class AppDeviceR : IAppDevice
    {
        private PostgreSQLConfiguration _connectionString;

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="connectionString">string para conectar como db</param>
        public AppDeviceR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        /// <summary>
        /// metodo de conexion db
        /// </summary>
        /// <returns>conexion db</returns>
        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }

        /// <summary>
        /// Metodo para obtener todos los devices
        /// </summary>
        /// <returns>lista de devices</returns>
        public async Task<IEnumerable<AppDevice>> GetAllAppDevices()
        {
            var db = dbConnection();

            var sql = @"
                        SELECT ""serialNumber"", description, consumption, brand, type, room, ""createdDate"", ""emailOwner"", active
                        FROM public.""RegisteredDevice"" ";

            return await db.QueryAsync<AppDevice>(sql, new { });
        }

        /// <summary>
        /// metodo para obtener un device especifico
        /// </summary>
        /// <param name="serialNumber"></param>
        /// <returns>device</returns>
        public async Task<AppDevice> GetAppDevice(int serialNumber)
        {
            var db = dbConnection();

            var sql = @"
                        SELECT ""serialNumber"", description, consumption, brand, type, room, ""createdDate"", ""ownerEmail"", active
                        FROM public.""RegisteredDevice"" 
                        WHERE ""serialNumber"" = @serialNumber ";

            return await db.QueryFirstOrDefaultAsync<AppDevice>(sql, new { serialNumber = serialNumber });
        }

        /// <summary>
        /// metodo para insertar un nuevo devive en la db
        /// </summary>
        /// <param name="device">device a insertar</param>
        /// <returns>bool</returns>
        public async Task<bool> InsertAppDevice(AppDevice device)
        {
            var db = dbConnection();



            var query = @"
                        SELECT COUNT(*)
                        FROM public.""RegisteredDevice"" 
                        WHERE ""serialNumber"" = @serialNumber ";

            
            int cantDevices = db.ExecuteScalar<int>(query, new {
            
                device.serialNumber,
                device.description,
                device.consumption,
                device.brand,
                device.type,
                device.room,
                device.createdDate
            });

            if (cantDevices > 0) { 
            
                var sqlupdate = @"
                            UPDATE public.""RegisteredDevice"" 
                            SET active = false
                            WHERE ""serialNumber"" = @serialNumber AND active = true";

                await db.ExecuteAsync(sqlupdate, new
                {
                    
                    device.serialNumber
                });
            }
            
            var sql = @"
                        INSERT INTO  public.""RegisteredDevice"" (""serialNumber"", description, consumption, brand, type, room, ""createdDate"", ""emailOwner"", active)
                        VALUES (@serialNumber, @description, @consumption, @brand, @type, @room, @createdDate, @emailOwner, @active) ";

            var result = await db.ExecuteAsync(sql, new
            {
                device.serialNumber,
                device.description,
                device.consumption,
                device.brand,
                device.type,
                device.room,
                device.createdDate,
                device.emailOwner,
                device.active
            });

            return result > 0;
        }

        /// <summary>
        /// metodo para obtener la cantidad de devices activos en la db
        /// </summary>
        /// <returns>int cantidad </returns>
        public int GetActiveDevice()
        {
            var db = dbConnection();

            var query = @"
                        SELECT COUNT(*)
                        FROM public.""RegisteredDevice"" 
                        WHERE ""active"" = true ";


            int cantDevices = db.ExecuteScalar<int>(query, new { });


            return cantDevices;
        }
    }
}
