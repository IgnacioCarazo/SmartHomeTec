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
    public class ControlR : IControl
    {
        private PostgreSQLConfiguration _connectionString;

        public ControlR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }

        public async Task<IEnumerable<Control>> GetAll()
        {
            var db = dbConnection();
            var sql = @"
                        SELECT *
                        FROM public.""Control"" ";
            return await db.QueryAsync<Control>(sql, new { });
        }

        public async Task<bool> InsertControl(Control control)
        {
            var db = dbConnection();

            var sql = @"
                        INSERT INTO  public.""Control"" (id, time, date, ""SerialNumber"")
                        VALUES (@id, @time, @date, @SerialNumber) ";

            var result = await db.ExecuteAsync(sql, new
            {
                control.id,
                control.time,
                control.date,
                control.SerialNumber
            });

            return result > 0;
        }
    }
}
