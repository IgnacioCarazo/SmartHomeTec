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
    /// clase para implementar metodos de la interfaz del modelo Device
    /// </summary>
    public class DeviceR : IDevice
    {
        private PostgreSQLConfiguration _connectionString;

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="connectionString">string para conectar como db</param>
        public DeviceR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        /// <summary>
        /// metodo de conexion db
        /// </summary>
        /// <returns>conexion db</returns>
        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }

        /// <summary>
        /// metodo para obtener todos los device de la db
        /// </summary>
        /// <returns>lista de device</returns>
        public async Task<IEnumerable<Device>> GetAllDevices()
        {
            var db = dbConnection();
            var sql = @"
                        SELECT name, ""serialNumber"", ""eConsumption"", brand, associated, ""typeName"", ""ownerEmail"", ""dniDistributor"", price
                        FROM public.""Device"" ";

            return await db.QueryAsync<Device>(sql, new { });
        }

        /// <summary>
        /// metodo para obtener device especifico de la db
        /// </summary>
        /// <param name="_serialNumber">numero serial del device a obtener</param>
        /// <returns>device</returns>
        public async Task<Device> GetDevice(int _serialNumber)
        {
            var db = dbConnection();

            var sql = @"
                        SELECT name, ""serialNumber"", ""eConsumption"", brand, associated, ""typeName"", ""ownerEmail"", ""dniDistributor"", price
                        FROM public.""Device"" 
                        WHERE ""serialNumber"" = @serialNumber ";

            return await db.QueryFirstOrDefaultAsync<Device>(sql, new { serialNumber = _serialNumber });
        }

        /// <summary>
        /// metodo para insertar device en la db
        /// </summary>
        /// <param name="device">device a insertar</param>
        /// <returns>bool</returns>
        public async Task<bool> InsertDevice(Device device)
        {
            var db = dbConnection();

            var sql = @"
                        INSERT INTO  public.""Device"" (name, ""eConsumption"", brand, associated, ""typeName"", ""ownerEmail"", ""dniDistributor"", price)
                        VALUES (@name, @eConsumption, @brand, @associated, @typeName, @ownerEmail, @dniDistributor, @price) ";

            var result = await db.ExecuteAsync(sql, new
            {
                device.name,
                device.serialNumber,
                device.eConsumption,
                device.brand,
                device.associated,
                device.typeName,
                device.ownerEmail,
                device.dniDistributor,
                device.price
            });

            return result > 0;
        }

        /// <summary>
        /// metodo para hacer update de device
        /// </summary>
        /// <param name="device">device a update</param>
        /// <returns>bool</returns>
        public async Task<bool> UpdateDevice(Device device)
        {
            var db = dbConnection();

            var sql = @"
                        UPDATE public.""Device"" 
                        SET name = @name, 
                            ""eConsumption"" = @eConsumption,
                            brand = @brand,
                            associated = @associated,
                            ""typeName"" = @typeName,
                            ""ownerEmail"" = @ownerEmail,
                            ""dniDistributor"" = @dniDistributor,
                            price = @price
                        WHERE ""serialNumber"" = @serialNumber";

            var result = await db.ExecuteAsync(sql, new
            {
                device.name,
                device.serialNumber,
                device.eConsumption,
                device.brand,
                device.associated,
                device.typeName,
                device.ownerEmail,
                device.dniDistributor,
                device.price
            });

            return result > 0;
        }

        /// <summary>
        /// metodo para hacer delete de un device en db
        /// </summary>
        /// <param name="device">device a eliminar</param>
        /// <returns>bool</returns>
        public async Task<bool> DeleteDevice(Device device)
        {
            var db = dbConnection();

            var sql = @"
                        DELETE
                        FROM public.""Device"" 
                        WHERE ""serialNumber"" = @serialNumber ";

            var result = await db.ExecuteAsync(sql, new
            {
                device.name,
                device.serialNumber,
                device.eConsumption,
                device.brand,
                device.associated,
                device.typeName,
                device.ownerEmail,
                device.dniDistributor,
                device.price
            });

            return result > 0;
        }

        /// <summary>
        /// metodo para obtener el promedio de dispostivos por usuarios
        /// </summary>
        /// <returns>int promedio</returns>
        public async Task<int> DeviceAVG()
        {
            var db = dbConnection();

            var query = @"
                        SELECT COUNT(DISTINCT ""ownerEmail"")
                        FROM public.""Device"" 
                        WHERE associated = true ";

            
            int users = db.ExecuteScalar<int>(query, new{});

            var query2 = @"
                        SELECT COUNT(""serialNumber"")
                        FROM public.""Device"" 
                        WHERE associated = true";
            int devices = db.ExecuteScalar<int>(query2, new { });

            if (users == 0)
            {
                return 0;
            }
            int average = (int)Math.Round((double)devices / users);
            return average;
            
        }
    }
}
