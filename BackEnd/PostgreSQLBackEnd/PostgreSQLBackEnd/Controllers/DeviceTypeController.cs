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
    /// controlador para modelo DeviceType
    /// </summary>
    [Route("api/[controller]")]
    [ApiController]
    public class DeviceTypeController : Controller
    {
        private readonly IDeviceType _deviceType;

        /// <summary>
        /// constructor de la clase
        /// </summary>
        /// <param name="deviceType"></param>
        public DeviceTypeController(IDeviceType deviceType) => _deviceType = deviceType;

        /// <summary>
        /// metodo para get de todos los devicetype
        /// </summary>
        /// <returns>lista devicetype</returns>>
        [HttpGet]
        public async Task<IActionResult> GetAllDeviceT()
        {
            return Ok(await _deviceType.GetAllTypes());
        }

        /// <summary>
        /// metodo para get de todos los devicetype
        /// </summary>
        /// <returns>lista devicetype</returns>>
        [HttpGet("{name}")]
        public async Task<IActionResult> GetDType(string name)
        {
            return Ok(await _deviceType.GetType(name));
        }

        /// <summary>
        /// metodo para post de  devicetype
        /// </summary>
        /// <returns>lista devicetype</returns>>
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
            return Ok(await _deviceType.GetAllTypes());
        }

        /// <summary>
        /// metodo para put de  devicetype
        /// </summary>
        /// <returns>lista devicetype</returns>>
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
            return Ok(await _deviceType.GetAllTypes());
        }

        /// <summary>
        /// metodo para delete  devicetype
        /// </summary>
        /// <returns>lista devicetype</returns>>
        [HttpDelete("{name}")]
        public async Task<IActionResult> DltType(string name)
        {
            await _deviceType.DeleteType(new DeviceType { name = name });
            return Ok(await _deviceType.GetAllTypes());
        }

    }
}
