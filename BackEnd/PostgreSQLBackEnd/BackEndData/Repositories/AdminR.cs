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
    /// clase para implementar metodos de la interfaz del modelo Admin
    /// </summary>
    public class AdminR : IAdmin
    {
        private PostgreSQLConfiguration _connectionString;

        /// <summary>
        /// Constructor de clase
        /// </summary>
        /// <param name="connectionString">string para conectar a postgresql</param>
        public AdminR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        /// <summary>
        /// conecta a postgresql
        /// </summary>
        /// <returns>conexion a base de datos</returns>
        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }

        /// <summary>
        /// metodo para obtener un admin de la base de datos
        /// </summary>
        /// <param name="_email">email del admin se desea obtener</param>
        /// <returns>admin</returns>
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
