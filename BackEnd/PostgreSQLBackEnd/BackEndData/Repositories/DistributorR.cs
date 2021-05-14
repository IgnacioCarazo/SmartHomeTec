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

        public void GetExcel()
        {
            var db = dbConnection();
            var sql = @"
                        COPY (SELECT ds.name AS ""Distributor"", dv.name AS ""Product"", ds.continent AS ""Continent"", ds.country AS ""Country""
                        FROM public.""Distributor"" ds 
                        INNER JOIN public.""Device"" dv ON ds.dni = dv.""dniDistributor""
                        GROUP BY ""Distributor"",""Product"", ""Continent"",""Country"") TO 'C:\Users\mario\Desktop\excel\distributorDevice.csv' DELIMITER ',' CSV HEADER ";
            db.Query(sql);
            
        }

        public IEnumerable<dynamic> GetDeviceRegion()
        {
            var db = dbConnection();
            var sql = @"
                        SELECT DISTINCT ""Regiones"" AS ""Region"", COUNT(""Regiones"") AS ""Cantidad""
                        FROM (SELECT ds.country AS ""Regiones"",dv.""serialNumber"" AS ""Device""
                              FROM public.""Distributor"" ds 
                              INNER JOIN public.""Device"" dv ON ds.dni = dv.""dniDistributor""
                              GROUP BY ""Regiones"",""Device"") T 
                        GROUP BY ""Region"" ";


            var result = db.Query(sql);
            return result;
        }
    }
}
