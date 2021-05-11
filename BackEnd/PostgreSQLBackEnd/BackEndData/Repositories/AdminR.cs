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
    public class AdminR : IAdmin
    {
        private PostgreSQLConfiguration _connectionString;

        public AdminR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }

        public async Task<Admin> GetAdmin(string _email)
        {
            var db = dbConnection();

            var sql = @"
                        SELECT email, password
                        FROM public.""Admin"" 
                        WHERE email = @email ";

            return await db.QueryFirstOrDefaultAsync<Admin>(sql, new { email = _email });
        }
    }
}
