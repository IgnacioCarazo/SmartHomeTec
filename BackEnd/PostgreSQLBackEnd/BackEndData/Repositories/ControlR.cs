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
    /// clase para implementar metodos de la interfaz del modelo Control
    /// </summary>
    public class ControlR : IControl
    {
        private PostgreSQLConfiguration _connectionString;

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="connectionString">string para conectar como db</param>
        public ControlR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        /// <summary>
        /// metodo de conexion db
        /// </summary>
        /// <returns>conexion db</returns>
        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }

        /// <summary>
        /// metodo para obtener todos los Control de la db
        /// </summary>
        /// <returns>lista de Control</returns>
        public async Task<IEnumerable<Control>> GetAll()
        {
            var db = dbConnection();
            var sql = @"
                        SELECT *
                        FROM public.""Control"" ";
            return await db.QueryAsync<Control>(sql, new { });
        }

        /// <summary>
        /// metodo para insertar Control en la db
        /// </summary>
        /// <param name="control">control a insertar</param>
        /// <returns>bool</returns>
        public async Task<bool> InsertControl(Control control)
        {
            var db = dbConnection();

            var sql = @"
                        INSERT INTO  public.""Control"" (time, date, ""SerialNumber"")
                        VALUES (@time, @date, @SerialNumber) ";

            var result = await db.ExecuteAsync(sql, new
            {
                control.time,
                control.date,
                control.SerialNumber
            });

            return result > 0;
        }
    }
}
