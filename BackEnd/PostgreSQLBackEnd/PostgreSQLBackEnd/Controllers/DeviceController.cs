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
    /// Clase controlador para peticiones http de controller
    /// </summary>
    [Route("api/[controller]")]
    [ApiController]
    public class DeviceController : Controller
    {
        private readonly IDevice _device;

        /// <summary>
        /// constructor de clase
        /// </summary>
        /// <param name="device">interfaz device</param>
        public DeviceController(IDevice device) => _device = device;

        /// <summary>
        /// metodo para get de todos los device
        /// </summary>
        /// <returns>lista device</returns>
        [HttpGet]
        public async Task<IActionResult> GetAllDevice()
        {
            await _device.DeviceAVG();
            return Ok(await _device.GetAllDevices());
        }

        /// <summary>
        /// metodo para get de todos los device
        /// </summary>
        /// <returns>lista device</returns>
        [HttpGet("{serialNumber}")]
        public async Task<IActionResult> GetDvice(int serialNumber)
        {
            return Ok(await _device.GetDevice(serialNumber));
        }

        /// <summary>
        /// metodo para post de  device
        /// </summary>
        /// <returns>lista device</returns>
        [HttpPost]
        public async Task<IActionResult> NewDevice([FromBody] Device device)
        {
            if (device == null)
            {
                return BadRequest();
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var insert = await _device.InsertDevice(device);
            return Ok(await _device.GetAllDevices());
        }

        /// <summary>
        /// metodo para put  device
        /// </summary>
        /// <returns>lista device</returns>
        [HttpPut]
        public async Task<IActionResult> UpdtDvc([FromBody] Device device)
        {
            if (device == null)
            {
                return BadRequest();
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            await _device.UpdateDevice(device);
            return Ok(await _device.GetAllDevices());
        }

        /// <summary>
        /// metodo para delete device
        /// </summary>
        /// <returns>lista device</returns>
        [HttpDelete("{_serialNumber}")]
        public async Task<IActionResult> DltDevice(int _serialNumber)
        {
            await _device.DeleteDevice(new Device { serialNumber = _serialNumber });
            return Ok(await _device.GetAllDevices());
        }

        /// <summary>
        /// metodo para obtener promedio de device por usuarios
        /// </summary>
        /// <returns>int</returns>
        [HttpGet("average")]
        public async Task<IActionResult> GetAVG()
        {
            return Ok(await _device.DeviceAVG());
        }
    }
}
