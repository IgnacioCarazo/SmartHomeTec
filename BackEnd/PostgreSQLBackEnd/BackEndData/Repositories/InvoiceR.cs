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
    /// clase para implementar metodos de la interfaz del modelo Invoice
    /// </summary>
    public class InvoiceR : IInvoice
    {
        private PostgreSQLConfiguration _connectionString;

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="connectionString">string para conectar como db</param>
        public InvoiceR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        /// <summary>
        /// metodo de conexion db
        /// </summary>
        /// <returns>conexion db</returns>
        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }

        /// <summary>
        /// metodo para obtener invoice especifico de la db
        /// </summary>
        /// <param name="invoiceNumber">numero de la invoice a obtener</param>
        /// <returns>invoice</returns>
        public async Task<Invoice> GetInvoice(int invoiceNumber)
        {
            var db = dbConnection();

            var sql = @"
                        SELECT ""invoiceNumber"", ""deviceTypeName"", price, date
                        FROM public.""Invoice"" 
                        WHERE ""invoiceNumber"" = @invoiceNumber ";

            return await db.QueryFirstOrDefaultAsync<Invoice>(sql, new { invoiceNumber = invoiceNumber });
        }

        /// <summary>
        /// metodo para obtener todas las invoice de la db
        /// </summary>
        /// <returns>lita de invoice</returns>
        public async Task<IEnumerable<Invoice>> GetInvoices()
        {
            var db = dbConnection();

            var sql = @"
                        SELECT ""invoiceNumber"", ""deviceTypeName"", price, date
                        FROM public.""Invoice"" ";

            return await db.QueryAsync<Invoice>(sql, new { });

        }


    }
}
