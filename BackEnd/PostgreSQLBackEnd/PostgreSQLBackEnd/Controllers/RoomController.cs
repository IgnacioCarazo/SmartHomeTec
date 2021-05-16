using BackEndData.InterfaceRepositories;
using BackEndModel;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreSQLBackEnd.Controllers
{
    /// <summary>
    /// Clase para peticiones room
    /// </summary>
    [Route("api/[controller]")]
    [ApiController]
    public class RoomController : Controller
    {
        private readonly IRoom _roomI;
        public RoomController(IRoom roomI) => _roomI = roomI;

        /// <summary>
        /// Get para obtener room
        /// </summary>
        /// <returns>lista de room</returns>
        [HttpGet]
        public async Task<IActionResult> GetRooms()
        {
            return Ok(await _roomI.GetAllRooms());
        }

        /// <summary>
        /// Get para obtener room especifico
        /// </summary>
        /// <param name="name">nombre del room al cual obtener</param>
        /// <returns>room deseado</returns>
        [HttpGet("{email}")]
        public async Task<IActionResult> GetRoom(string name)
        {
            return Ok(await _roomI.GetRoom(name));
        }

        /// <summary>
        /// Peticion Post para Room
        /// </summary>
        /// <param name="room">room que se desea ingresar</param>
        /// <returns>string indicando que se inserto</returns>
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

        /// <summary>
        /// Put para update de un room
        /// </summary>
        /// <param name="room">nuevo room para hacer update</param>
        /// <returns></returns>
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

        /// <summary>
        /// Delete para room
        /// </summary>
        /// <param name="name">nombre del room a elimnar</param>
        /// <returns>ok de eliminacion</returns>
        [HttpDelete("{name}")]
        public async Task<IActionResult> DltRoom(string name)
        {
            await _roomI.DeleteRoom(new Room { name = name });
            return Ok("Deleted");
        }
    }
}
