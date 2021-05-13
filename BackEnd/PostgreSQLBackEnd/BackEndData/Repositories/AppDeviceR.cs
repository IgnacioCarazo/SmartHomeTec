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
    public class AppDeviceR : IAppDevice
    {
        private PostgreSQLConfiguration _connectionString;

        public AppDeviceR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }

        public async Task<IEnumerable<AppDevice>> GetAllAppDevices()
        {
            var db = dbConnection();

            var sql = @"
                        SELECT ""serialNumber"", description, consumption, brand, type, room, ""createdDate"" 
                        FROM public.""RegisteredDevice"" ";

            return await db.QueryAsync<AppDevice>(sql, new { });
        }

        public async Task<AppDevice> GetAppDevice(int serialNumber)
        {
            var db = dbConnection();

            var sql = @"
                        SELECT ""serialNumber"", description, consumption, brand, type, room, ""createdDate""
                        FROM public.""RegisteredDevice"" 
                        WHERE ""serialNumber"" = @serialNumber ";

            return await db.QueryFirstOrDefaultAsync<AppDevice>(sql, new { serialNumber = serialNumber });
        }

        public async Task<bool> InsertAppDevice(AppDevice device)
        {
            var db = dbConnection();

            var sql = @"
                        INSERT INTO  public.""RegisteredDevice"" (""serialNumber"", description, consumption, brand, type, room, ""createdDate"")
                        VALUES (@serialNumber, @description, @consumption, @brand, @type, @room, @createdDate) ";

            var result = await db.ExecuteAsync(sql, new
            {
                device.serialNumber,
                device.description,
                device.consumption,
                device.brand,
                device.type,
                device.room,
                device.createdDate
            });

            return result > 0;
        }
    }
}
