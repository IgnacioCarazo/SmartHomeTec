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
    /// clase para implementar metodos de la interfaz del modelo DeviceType
    /// </summary>
    public class DeviceTypeR : IDeviceType
    {

        private PostgreSQLConfiguration _connectionString;

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="connectionString">string para conectar como db</param>
        public DeviceTypeR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        /// <summary>
        /// metodo de conexion db
        /// </summary>
        /// <returns>conexion db</returns>
        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }

        /// <summary>
        /// metodo para obtener todos los deviceType de la db
        /// </summary>
        /// <returns>lista de deviceType</returns>
        public async Task<IEnumerable<DeviceType>> GetAllTypes()
        {
            var db = dbConnection();
            var sql = @"
                        SELECT name, description, ""warrantyTime""
                        FROM public.""DeviceType"" ";

            return await db.QueryAsync<DeviceType>(sql, new { });
        }

        /// <summary>
        /// metodo para obtener devicetype especifico
        /// </summary>
        /// <param name="name">nombre del devicetype a obtener</param>
        /// <returns>devicetype</returns>
        public async Task<DeviceType> GetType(string name)
        {
            var db = dbConnection();

            var sql = @"
                        SELECT *
                        FROM public.""DeviceType"" 
                        WHERE name = @name ";

            return await db.QueryFirstOrDefaultAsync<DeviceType>(sql, new { name = name });
        }

        /// <summary>
        /// metodo para insertar un nuevo deviceType en la db
        /// </summary>
        /// <param name="type">devicetype a insertar</param>
        /// <returns>bool</returns>
        public async Task<bool> InsertType(DeviceType type)
        {
            var db = dbConnection();

            var sql = @"
                        INSERT INTO  public.""DeviceType"" (name, description, ""warrantyTime"")
                        VALUES (@name, @description, @warrantyTime) ";

            var result = await db.ExecuteAsync(sql, new
            {
                type.name,
                type.description,
                type.warrantyTime
              
            });

            return result > 0;
        }

        /// <summary>
        /// metodo para update un nuevo deviceType en la db
        /// </summary>
        /// <param name="type">devicetype a update</param>
        /// <returns>bool</returns>
        public async  Task<bool> UpdateType(DeviceType type)
        {
            var db = dbConnection();

            var sql = @"
                        UPDATE public.""DeviceType"" 
                        SET name = @name, 
                            description = @description,
                            ""warrantyTime"" = @warrantyTime
                        WHERE name = @name";

            var result = await db.ExecuteAsync(sql, new 
            {
                type.name,
                type.description,
                type.warrantyTime
            });

            return result > 0;
        }

        /// <summary>
        /// metodo para eliminar un nuevo deviceType en la db
        /// </summary>
        /// <param name="type">devicetype a eliminar</param>
        /// <returns>bool</returns>
        public async Task<bool> DeleteType(DeviceType type)
        {
            var db = dbConnection();

            var sql = @"
                        DELETE
                        FROM public.""DeviceType"" 
                        WHERE name = @name ";

            var result = await db.ExecuteAsync(sql, new
            {
                type.name,
                type.description,
                type.warrantyTime
            });

            return result > 0;
        }
    }
}
