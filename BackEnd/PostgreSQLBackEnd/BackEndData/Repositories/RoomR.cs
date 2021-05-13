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
    public class RoomR : IRoom
    {
        private PostgreSQLConfiguration _connectionString;

        public RoomR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }
        

        public async  Task<IEnumerable<Room>> GetAllRooms()
        {
            var db = dbConnection();

            var sql = @"
                        SELECT name, ""userEmail""
                        FROM public.""Room"" ";

            return await db.QueryAsync<Room>(sql, new { }); ;
        }

        public async Task<Room> GetRoom(string name)
        {
            var db = dbConnection();

            var sql = @"
                        SELECT name, ""userEmail""
                        FROM public.""Room"" 
                        WHERE name = @name ";

            return await db.QueryFirstOrDefaultAsync<Room>(sql, new { name = name });
        }

        public async Task<bool> InsertRoom(Room room)
        {
            var db = dbConnection();

            var sql = @"
                        INSERT INTO  public.""Room"" (name, ""userEmail"")
                        VALUES (@name, @userEmail) ";

            var result = await db.ExecuteAsync(sql, new
            {
                room.name,
                room.userEmail
            });

            return result > 0;
        }

        public async Task<bool> UpdateRoom(Room room)
        {
            var db = dbConnection();

            var sql = @"
                        UPDATE public.""Room"" 
                        SET name = @name, 
                            ""userEmail"" = @userEmail
                        WHERE name = @name";

            var result = await db.ExecuteAsync(sql, new
            {
                room.name,
                room.userEmail
            });
            return result > 0;
        }

        public async Task<bool> DeleteRoom(Room room)
        {
            var db = dbConnection();

            var sql = @"
                        DELETE
                        FROM public.""Room"" 
                        WHERE name = @name ";

            var result = await db.ExecuteAsync(sql, new
            {
                room.name,
                room.userEmail
            });

            return result > 0;
        }
    }
}
