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
    public class AppDeviceController : Controller
    {
        private readonly IAppDevice _appDevice;

        public AppDeviceController(IAppDevice appDevice) => _appDevice = appDevice;

        [HttpGet]
        public async Task<IActionResult> GetAllDevice()
        {
            return Ok(await _appDevice.GetAllAppDevices());
        }

        [HttpGet("{serialNumber}")]
        public async Task<IActionResult> GetDevice(int serialNumber)
        {
            return Ok(await _appDevice.GetAppDevice(serialNumber));
        }

        [HttpPost]
        public async Task<IActionResult> NewDevice([FromBody] AppDevice device)
        {
            if (device == null)
            {
                return BadRequest();
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var insertType = await _appDevice.InsertAppDevice(device);
            return Ok("Inserted");
        }

        [HttpGet("active")]
        public IActionResult GetActive()
        {
            return Ok(_appDevice.GetActiveDevice());
        }
    }
}
