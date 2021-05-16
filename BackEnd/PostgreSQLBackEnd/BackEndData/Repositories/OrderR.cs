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
    /// clase para implementar metodos de la interfaz del modelo Order
    /// </summary>
    public class OrderR : IOrder
    {
        private PostgreSQLConfiguration _connectionString;

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="connectionString">string para conectar como db</param>
        public OrderR(PostgreSQLConfiguration connectionString) => _connectionString = connectionString;

        /// <summary>
        /// metodo de conexion db
        /// </summary>
        /// <returns>conexion db</returns>
        protected NpgsqlConnection dbConnection()
        {
            return new NpgsqlConnection(_connectionString.ConnectionString);
        }

        /// <summary>
        /// metodo para obtener todas las order de la db
        /// </summary>
        /// <returns>ista de order</returns>
        public async Task<IEnumerable<Order>> GetAllOrder()
        {
            var db = dbConnection();

            var sql = @"
                        SELECT *
                        FROM public.""Order"" ";

            return await db.QueryAsync<Order>(sql, new { });
        }

        /// <summary>
        /// metodo para obtener order especifica de la db
        /// </summary>
        /// <param name="id">id de la order</param>
        /// <returns>order</returns>
        public async Task<Order> GetOrder(int id)
        {
            var db = dbConnection();
            var sql = @"
                        SELECT *
                        FROM public.""Order"" 
                        WHERE ""orderID"" = @orderID ";

            return await db.QueryFirstOrDefaultAsync<Order>(sql, new { orderID = id });
        }

        /// <summary>
        /// metodo para inserta order en db
        /// </summary>
        /// <param name="order">order a insertar</param>
        /// <returns>int del id de la order</returns>
        public async Task<int> InsertOrder(Order order)
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

            var orderQuery = @"SELECT * FROM public.""Order"" ORDER BY ""orderID"" DESC LIMIT 1";

            Order getOrder = await db.QueryFirstOrDefaultAsync<Order>(orderQuery, new {});
            int id = getOrder.orderID;
            return id;
        }

        /// <summary>
        /// metodo para hacer update a una order de la db
        /// </summary>
        /// <param name="order">order a hacer update</param>
        /// <returns>bool</returns>
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

        /// <summary>
        /// metodo para eliminar order de la db
        /// </summary>
        /// <param name="order">order a eliminar</param>
        /// <returns>bool</returns>
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

        /// <summary>
        /// metodo para insertar invoice en la db por cada order
        /// </summary>
        /// <param name="invoice">invoice a insertar</param>
        /// <returns>bool</returns>
        public async Task<bool> InsertInvoice(Invoice invoice)
        {
            var db = dbConnection();

            var sql = @"
                        INSERT INTO  public.""Invoice"" (""invoiceNumber"", ""deviceTypeName"", price, date)
                        VALUES (@invoiceNumber, @deviceTypeName, @price, @date) ";

            var result = await db.ExecuteAsync(sql, new
            {
                invoice.invoiceNumber,
                invoice.deviceTypeName,
                invoice.price,
                invoice.date
            });

            return result > 0;
        }

        /// <summary>
        /// metodo para obtener el device de cada order 
        /// </summary>
        /// <param name="order">order a la cual se busca el device</param>
        /// <returns>device</returns>
        public async Task<Device> GetDevice(Order order) {

            var db = dbConnection();

            int _serialNumber = order.deviceSerialNumber;

            var sql = @"
                        SELECT name, ""serialNumber"", ""eConsumption"", brand, associated, ""typeName"", ""ownerEmail"", ""dniDistributor"", price
                        FROM public.""Device"" 
                        WHERE ""serialNumber"" = "+_serialNumber+" ";

            Device _device  = await db.QueryFirstOrDefaultAsync<Device>(sql, new { serialNumber = _serialNumber });
            return _device;
        }

        /// <summary>
        /// metodo para obtener tiempo de garantia segun el deviceType
        /// </summary>
        /// <param name="name">nombre del devicetype</param>
        /// <returns>int garantia</returns>
        public async Task<int> GetDeviceWarranty(string name)
        {

            var db = dbConnection();
            var sql = @"
                        SELECT *
                        FROM public.""DeviceType"" 
                        WHERE name = @name ";

            DeviceType _deviceType = await db.QueryFirstOrDefaultAsync<DeviceType>(sql, new { name = name });
            int warranty = _deviceType.warrantyTime;
            return warranty;
        }

        /// <summary>
        /// metodo para insertar nueva garantia en la db por cada order
        /// </summary>
        /// <param name="warranty">warranty a insertar</param>
        /// <returns>bool</returns>
        public async Task<bool> InsertWarranty(Warranty warranty)
        {
            var db = dbConnection();

            var sql = @"
                        INSERT INTO  public.""Warranty"" (""clientName"",""deviceSerialNumber"", brand, ""purchaseDate"", ""expireDate"", ""deviceTypeName"")
                        VALUES (@clientName, @deviceSerialNumber, @brand, @purchaseDate, @expireDate, @deviceTypeName) ";

            var result = await db.ExecuteAsync(sql, new
            {
                warranty.clientName,
                warranty.deviceSerialNumber,
                warranty.brand,
                warranty.purchaseDate,
                warranty.expireDate,
                warranty.deviceTypeName
            });

            return result > 0;
        }

        /// <summary>
        /// metodo para obtener el nombre del nombres de dueno de la order
        /// </summary>
        /// <param name="email">email del dueno a obtener</param>
        /// <returns>string nombre</returns>
        public async Task<string> GetOwnerName(string email)
        {
            var db = dbConnection();
           
            var sql = @"
                        SELECT *
                        FROM public.""Client"" 
                        WHERE email = @email ";


            Client _client = await db.QueryFirstOrDefaultAsync<Client>(sql, new { email = email});

            return _client.name;
        }
    }
}
