using BackEndData.InterfaceRepositories;
using BackEndModel;
using Microsoft.AspNetCore.Mvc;
using PostgreSQLBackEnd.DataManagement;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreSQLBackEnd.Controllers
{
    /// <summary>
    /// controlador del modelo Order
    /// </summary>
    [Route("api/[controller]")]
    [ApiController]
    public class OrderController : Controller
    {
        private readonly IOrder _orderI;

        /// <summary>
        /// constructor de la clase
        /// </summary>
        /// <param name="orderI">interfaz Order</param>
        public OrderController(IOrder orderI) => _orderI = orderI;

        /// <summary>
        /// metodo para get de todas las order
        /// </summary>
        /// <returns>lista order</returns>
        [HttpGet]
        public async Task<IActionResult> GetAllOrders()
        {
            return Ok(await _orderI.GetAllOrder());
        }

        /// <summary>
        /// metodo get obtener order especifica
        /// </summary>
        /// <param name="orderID">id de la order</param>
        /// <returns>order</returns>
        [HttpGet("{orderID}")]
        public async Task<IActionResult> GetOrdr(int orderID)
        {
            return Ok(await _orderI.GetOrder(orderID));
        }

        /// <summary>
        /// metodo post para order y generar factura y garantia
        /// </summary>
        /// <param name="order">order</param>
        /// <returns>lista order</returns>
        [HttpPost]
        public async Task<IActionResult> NewOrder([FromBody] Order order)
        {
            if (order == null)
            {
                return BadRequest();
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            int insertOrID = await _orderI.InsertOrder(order);

            order.orderID = insertOrID;


            InvoiceReport invoiceReport = new InvoiceReport();

            Device orderDevice = await _orderI.GetDevice(order);

            string ownerName = await _orderI.GetOwnerName(order.deviceOwner);

            int warrantyTime = await _orderI.GetDeviceWarranty(orderDevice.typeName);

            Invoice invoicepdf = invoiceReport.generateInvoice(order,orderDevice.typeName);

            Warranty warrantypdf = invoiceReport.generateWarranty(order,ownerName,orderDevice,warrantyTime);

            await _orderI.InsertInvoice(invoicepdf);
            await _orderI.InsertWarranty(warrantypdf);

            invoiceReport.sendPDF(invoicepdf,order.deviceOwner,warrantypdf);

           
            return Ok(await _orderI.GetAllOrder());
        }

        /// <summary>
        /// metodo put para order
        /// </summary>
        /// <param name="order">order updated</param>
        /// <returns>list order</returns>
        [HttpPut]
        public async Task<IActionResult> UpdtOrder([FromBody] Order order)
        {
            if (order == null)
            {
                return BadRequest();
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            await _orderI.UpdateOrder(order);
            return Ok(await _orderI.GetAllOrder());
        }

        /// <summary>
        /// metodo para delete order
        /// </summary>
        /// <param name="_orderID">id de la order</param>
        /// <returns>list orderreturns>
        [HttpDelete("{_orderID}")]
        public async Task<IActionResult> DltOrder(int _orderID)
        {
            await _orderI.DeleteOrder(new Order { orderID = _orderID });
            return Ok(await _orderI.GetAllOrder());
        }
    }
}
