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
    public class DeviceController : Controller
    {
        private readonly IDevice _device;

        public DeviceController(IDevice device) => _device = device;

        [HttpGet]
        public async Task<IActionResult> GetAllDevice()
        {
            return Ok(await _device.GetAllDevices());
        }

        [HttpGet("{serialNumber}")]
        public async Task<IActionResult> GetDvice(int serialNumber)
        {
            return Ok(await _device.GetDevice(serialNumber));
        }

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

        [HttpDelete("{_serialNumber}")]
        public async Task<IActionResult> DltDevice(int _serialNumber)
        {
            await _device.DeleteDevice(new Device { serialNumber = _serialNumber });
            return Ok(await _device.GetAllDevices());
        }
    }
}
