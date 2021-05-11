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
    public class DistributorR : IDistributor
    {
        private PostgreSQLConfiguration _connectionString;

        public DistributorR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }

        public async Task<IEnumerable<Distributor>> GetDistributors()
        {
            var db = dbConnection();
            var sql = @"
                        SELECT dni, name, continent, country
                        FROM public.""Distributor"" ";
            return await db.QueryAsync<Distributor>(sql, new { });
        }

        
    }
}
