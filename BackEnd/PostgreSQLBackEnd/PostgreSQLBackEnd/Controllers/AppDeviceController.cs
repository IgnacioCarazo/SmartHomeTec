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
    public class AppDeviceController : Controller
    {
        private readonly IAppDevice _appDevice;

        /// <summary>
        /// constructor
        /// </summary>
        /// <param name="appDevice">interfaz AppDevice</param>
        public AppDeviceController(IAppDevice appDevice) => _appDevice = appDevice;


        /// <summary>
        /// get de todos los AppDevices
        /// </summary>
        /// <returns>lista appdevices</returns>
        [HttpGet]
        public async Task<IActionResult> GetAllDevice()
        {
            return Ok(await _appDevice.GetAllAppDevices());
        }

        /// <summary>
        /// metodo get appdevice espevifico
        /// </summary>
        /// <param name="serialNumber">int del appdevice</param>
        /// <returns>appdevice</returns>
        [HttpGet("{serialNumber}")]
        public async Task<IActionResult> GetDevice(int serialNumber)
        {
            return Ok(await _appDevice.GetAppDevice(serialNumber));
        }

        /// <summary>
        /// metodo para insertar un appdevice especifico
        /// </summary>
        /// <param name="device">device a insertar</param>
        /// <returns>string</returns>
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

        /// <summary>
        /// metodo get
        /// </summary>
        /// <returns>int de appdevices activos</returns>
        [HttpGet("active")]
        public IActionResult GetActive()
        {
            return Ok(_appDevice.GetActiveDevice());
        }
    }
}
