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
    public class OrderR : IOrder
    {
        private PostgreSQLConfiguration _connectionString;

        public OrderR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }

        public async Task<IEnumerable<Order>> GetAllOrder()
        {
            var db = dbConnection();

            var sql = @"
                        SELECT *
                        FROM public.""Order"" ";

            return await db.QueryAsync<Order>(sql, new { });
        }

        public async Task<Order> GetOrder(int id)
        {
            var db = dbConnection();
            var sql = @"
                        SELECT *
                        FROM public.""Order"" 
                        WHERE ""orderID"" = @orderID ";

            return await db.QueryFirstOrDefaultAsync<Order>(sql, new { orderID = id });
        }

        public async Task<bool> InsertOrder(Order order)
        {
            var db = dbConnection();

            var query = @"
                        SELECT COUNT(""deviceOwner"")
                        FROM public.""Order"" 
                        WHERE ""deviceOwner"" = @deviceOwner ";

            order.consecutiveNumberOrder = db.ExecuteScalar<int>(query, new
            {
                order.consecutiveNumberOrder,
                order.deviceSerialNumber,
                order.date,
                order.hour,
                order.brand,
                order.price,
                order.deviceOwner,
                order.orderID
            }) + 1;

             
            var sql = @"
                        INSERT INTO  public.""Order"" (""consecutiveNumberOrder"", ""deviceSerialNumber"", date, hour, brand, price, ""deviceOwner"")
                        VALUES (@consecutiveNumberOrder, @deviceSerialNumber, @date, @hour, @brand, @price, @deviceOwner)";

            var result = await db.ExecuteAsync(sql, new
            {
                order.consecutiveNumberOrder,
                order.deviceSerialNumber,
                order.date,
                order.hour,
                order.brand,
                order.price,
                order.deviceOwner,
                order.orderID
            });

            return result > 0;
        }

        public async Task<bool> UpdateOrder(Order order)
        {
            var db = dbConnection();

            var sql = @"
                        UPDATE public.""Order"" 
                        SET ""consecutiveNumberOrder"" = @consecutiveNumberOrder,
                            ""deviceSerialNumber"" = @deviceSerialNumber,
                            date = @date,
                            hour = @hour,
                            brand = @brand,
                            price = @price,
                            ""deviceOwner"" = @deviceOwner
                        WHERE ""orderID"" = @orderID";

            var result = await db.ExecuteAsync(sql, new
            {
                order.consecutiveNumberOrder,
                order.deviceSerialNumber,
                order.date,
                order.hour,
                order.brand,
                order.price,
                order.deviceOwner,
                order.orderID
            });

            return result > 0;

        }
        public async Task<bool> DeleteOrder(Order order)
        {
            var db = dbConnection();

            var sql = @"
                        DELETE
                        FROM public.""Order"" 
                        WHERE ""orderID"" = @orderID";

            var result = await db.ExecuteAsync(sql, new
            {
                order.consecutiveNumberOrder,
                order.deviceSerialNumber,
                order.date,
                order.hour,
                order.brand,
                order.price,
                order.deviceOwner,
                order.orderID
            });

            return result > 0;
        }
    }
}
