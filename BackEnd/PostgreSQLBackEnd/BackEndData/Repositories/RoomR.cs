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
    /// clase para implementar metodos de la interfaz del modelo Room
    /// </summary>
    public class RoomR : IRoom
    {
        private PostgreSQLConfiguration _connectionString;

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="connectionString">string para conectar como db</param>
        public RoomR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        /// <summary>
        /// metodo de conexion db
        /// </summary>
        /// <returns>conexion db</returns>
        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }
        
        /// <summary>
        /// metodo para obtener todos los room de la db
        /// </summary>
        /// <returns>lista de room</returns>
        public async  Task<IEnumerable<Room>> GetAllRooms()
        {
            var db = dbConnection();

            var sql = @"
                        SELECT name, ""userEmail""
                        FROM public.""Room"" ";

            return await db.QueryAsync<Room>(sql, new { }); ;
        }

        /// <summary>
        /// metodo para obtener room especifico de la db
        /// </summary>
        /// <param name="name">nombre del room a obtener</param>
        /// <returns>room</returns>
        public async Task<Room> GetRoom(string name)
        {
            var db = dbConnection();

            var sql = @"
                        SELECT name, ""userEmail""
                        FROM public.""Room"" 
                        WHERE name = @name ";

            return await db.QueryFirstOrDefaultAsync<Room>(sql, new { name = name });
        }

        /// <summary>
        /// metodo para insertar un nuevo room en la db
        /// </summary>
        /// <param name="room">room a insertar</param>
        /// <returns>bool</returns>
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

        /// <summary>
        /// metodo para hacer update a un room de la db
        /// </summary>
        /// <param name="room">room a hacer update</param>
        /// <returns>bool</returns>
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

        /// <summary>
        /// metodo para eliminar un room de la db
        /// </summary>
        /// <param name="room">room a eliminar</param>
        /// <returns>bool</returns>
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
