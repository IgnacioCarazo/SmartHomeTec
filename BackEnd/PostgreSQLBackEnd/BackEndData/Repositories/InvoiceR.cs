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
    public class InvoiceR : IInvoice
    {
        private PostgreSQLConfiguration _connectionString;

        public InvoiceR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }
        public async Task<Invoice> GetInvoice(int invoiceNumber)
        {
            var db = dbConnection();

            var sql = @"
                        SELECT ""invoiceNumber"", ""deviceTypeName"", price, date
                        FROM public.""Invoice"" 
                        WHERE ""invoiceNumber"" = @invoiceNumber ";

            return await db.QueryFirstOrDefaultAsync<Invoice>(sql, new { invoiceNumber = invoiceNumber });
        }

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
