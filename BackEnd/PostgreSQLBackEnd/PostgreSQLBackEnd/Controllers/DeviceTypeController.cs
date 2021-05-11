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
    public class DeviceTypeController : Controller
    {
        private readonly IDeviceType _deviceType;

        public DeviceTypeController(IDeviceType deviceType) => _deviceType = deviceType;

        [HttpGet]
        public async Task<IActionResult> GetAllDeviceT()
        {
            return Ok(await _deviceType.GetAllTypes());
        }

        [HttpGet("{name}")]
        public async Task<IActionResult> GetDType(string name)
        {
            return Ok(await _deviceType.GetType(name));
        }

        [HttpPost]
        public async Task<IActionResult> NewType([FromBody] DeviceType deviceType)
        {
            if (deviceType == null)
            {
                return BadRequest();
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var insertType = await _deviceType.InsertType(deviceType);
            return Ok(deviceType);
        }

        [HttpPut]
        public async Task<IActionResult> UpdtType([FromBody] DeviceType deviceType)
        {
            if (deviceType == null)
            {
                return BadRequest();
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            await _deviceType.UpdateType(deviceType);
            return Ok();
        }

        [HttpDelete("{name}")]
        public async Task<IActionResult> DltType(string name)
        {
            await _deviceType.DeleteType(new DeviceType { name = name });
            return Ok();
        }

    }
}
