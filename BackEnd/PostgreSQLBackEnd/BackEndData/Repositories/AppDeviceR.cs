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
                        SELECT ""serialNumber"", description, consumption, brand, type, room, ""createdDate"", ""emailOwner"", active
                        FROM public.""RegisteredDevice"" ";

            return await db.QueryAsync<AppDevice>(sql, new { });
        }

        public async Task<AppDevice> GetAppDevice(int serialNumber)
        {
            var db = dbConnection();

            var sql = @"
                        SELECT ""serialNumber"", description, consumption, brand, type, room, ""createdDate"", ""ownerEmail"", active
                        FROM public.""RegisteredDevice"" 
                        WHERE ""serialNumber"" = @serialNumber ";

            return await db.QueryFirstOrDefaultAsync<AppDevice>(sql, new { serialNumber = serialNumber });
        }

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
    }
}
