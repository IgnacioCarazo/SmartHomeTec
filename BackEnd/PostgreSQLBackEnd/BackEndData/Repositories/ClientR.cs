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
    public class ClientR : IClient
    {
        private PostgreSQLConfiguration _connectionString;

        public ClientR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;
        
        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }
        
        public async Task<IEnumerable<Client>> GetAllClients()
        {
            var db = dbConnection();

            var sql = @"
                        SELECT name, ""primaryLastName"", ""secondaryLastName"", email, password, continent, country, ""deliveryAdresses""
                        FROM public.""Client"" ";

            return await db.QueryAsync<Client>(sql, new { });
        }

        public async Task<Client> GetClient(string email)
        {
            var db = dbConnection();

            var sql = @"
                        SELECT *
                        FROM public.""Client"" 
                        WHERE email = @email ";

            return await db.QueryFirstOrDefaultAsync<Client>(sql, new { email = email});
        }

        public async Task<bool> InsertClient(Client client)
        {
            var db = dbConnection();

            var sql = @"
                        INSERT INTO  public.""Client"" (name, ""primaryLastName"", ""secondaryLastName"", email, password, continent, country, ""deliveryAdresses"")
                        VALUES (@name, @primaryLastName, @secondaryLastName, @email, @password, @continent, @country, @deliveryAdresses) ";

            var result = await db.ExecuteAsync(sql, new {client.name, client.primaryLastName, client.secondaryLastName,
                client.email, client.password, client.continent, client.country, client.deliveryAdresses});

            return result > 0;
        }

        public async Task<bool> UpdateClient(Client client)
        {
            var db = dbConnection();

            var sql = @"
                        UPDATE public.""Client"" 
                        SET name = @name, 
                            ""primaryLastName"" =  @primaryLastName,
                            ""secondaryLastName"" = @secondaryLastName, 
                            email = @email, 
                            password = @password, 
                            continent = @continent, 
                            country = @country, 
                            ""deliveryAdresses"" = @deliveryAdresses
                        WHERE email = @email";

            var result = await db.ExecuteAsync(sql, new
            {
                client.name,
                client.primaryLastName,
                client.secondaryLastName,
                client.email,
                client.password,
                client.continent,
                client.country,
                client.deliveryAdresses
            });

            return result > 0;
        }

        public async Task<bool> DeleteClient(Client client)
        {
            var db = dbConnection();

            var sql = @"
                        DELETE
                        FROM public.""Client"" 
                        WHERE email = @email ";

            var result = await db.ExecuteAsync(sql, new
            {
                client.name,
                client.primaryLastName,
                client.secondaryLastName,
                client.email,
                client.continent,
                client.country,
                client.deliveryAdresses
            });

            return result > 0;
        }
    }
}
