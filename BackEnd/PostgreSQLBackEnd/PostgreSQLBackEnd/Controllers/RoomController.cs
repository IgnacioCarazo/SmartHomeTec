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
    public class RoomController : Controller
    {
        private readonly IRoom _roomI;
        public RoomController(IRoom roomI) => _roomI = roomI;


        [HttpGet]
        public async Task<IActionResult> GetRooms()
        {
            return Ok(await _roomI.GetAllRooms());
        }

        [HttpGet("{email}")]
        public async Task<IActionResult> GetClient(string name)
        {
            return Ok(await _roomI.GetRoom(name));
        }

        [HttpPost]
        public async Task<IActionResult> NewRoom([FromBody] Room room)
        {
            if (room == null)
            {
                return BadRequest();
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var insertClient = await _roomI.InsertRoom(room);
            return Ok("Inserted");
        }

        [HttpPut]
        public async Task<IActionResult> UpdtRoom([FromBody] Room room)
        {
            if (room == null)
            {
                return BadRequest();
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            await _roomI.UpdateRoom(room);
            return Ok("Updated");
        }

        [HttpDelete("{name}")]
        public async Task<IActionResult> DltRoom(string name)
        {
            await _roomI.DeleteRoom(new Room { name = name });
            return Ok("Deleted");
        }
    }
}
