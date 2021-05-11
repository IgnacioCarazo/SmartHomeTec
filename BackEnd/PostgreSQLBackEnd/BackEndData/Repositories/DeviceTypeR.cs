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
    public class DeviceTypeR : IDeviceType
    {

        private PostgreSQLConfiguration _connectionString;

        public DeviceTypeR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }

        
        public async Task<IEnumerable<DeviceType>> GetAllTypes()
        {
            var db = dbConnection();
            var sql = @"
                        SELECT name, description, ""warrantyTime""
                        FROM public.""DeviceType"" ";

            return await db.QueryAsync<DeviceType>(sql, new { });
        }

        public async Task<DeviceType> GetType(string name)
        {
            var db = dbConnection();

            var sql = @"
                        SELECT *
                        FROM public.""DeviceType"" 
                        WHERE name = @name ";

            return await db.QueryFirstOrDefaultAsync<DeviceType>(sql, new { name = name });
        }

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

        public async  Task<bool> UpdateType(DeviceType type)
        {
            var db = dbConnection();

            var sql = @"
                        UPDATE public.""DeviceType"" 
                        SET name = @name, 
                            description = @description,
                            ""warrantyTime"" = @warrantyType
                        WHERE name = @name";

            var result = await db.ExecuteAsync(sql, new
            {
                type.name,
                type.description,
                type.warrantyTime
            });

            return result > 0;
        }

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
