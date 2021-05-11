using BackEndData.InterfaceRepositories;
using BackEndModel;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreSQLBackEnd.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class OrderController : Controller
    {
        private readonly IOrder _orderI;

        public OrderController(IOrder orderI) => _orderI = orderI;

        [HttpGet]
        public async Task<IActionResult> GetAllOrders()
        {
            return Ok(await _orderI.GetAllOrder());
        }

        [HttpGet("{orderID}")]
        public async Task<IActionResult> GetOrdr(int orderID)
        {
            return Ok(await _orderI.GetOrder(orderID));
        }

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
            var insertOr = await _orderI.InsertOrder(order);
            return Ok(await _orderI.GetAllOrder());
        }

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

        [HttpDelete("{_orderID}")]
        public async Task<IActionResult> DltOrder(int _orderID)
        {
            await _orderI.DeleteOrder(new Order { orderID = _orderID });
            return Ok(await _orderI.GetAllOrder());
        }
    }
}
