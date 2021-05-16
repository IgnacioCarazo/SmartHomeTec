using BackEndData.InterfaceRepositories;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreSQLBackEnd.Controllers
{
    /// <summary>
    /// controlador del modelo Distributor
    /// </summary>
    [Route("api/[controller]")]
    [ApiController]
    public class DistributorController : Controller
    {
        private readonly IDistributor _distributorI;

        /// <summary>
        /// constructor 
        /// </summary>
        /// <param name="distributorI">interfaz distributor</param>
        public DistributorController(IDistributor distributorI) => _distributorI = distributorI;

        /// <summary>
        /// metodo para get de todos los Distributor
        /// </summary>
        /// <returns>lista distributor</returns>>
        [HttpGet]
        public async Task<IActionResult> GetAllDistributors()
        {
            
            return Ok(await _distributorI.GetDistributors());
        }

        /// <summary>
        /// metodo para generar y obtener excel de dispositivos y distributor
        /// </summary>
        /// <returns>string</returns>
        [HttpGet("excel")]
        public IActionResult GetEx()
        {
            _distributorI.GetExcel();

            return Ok("Generated");
        }

        /// <summary>
        /// metodo get para obtener tabla dispositivos por region
        /// </summary>
        /// <returns>tabla sql</returns>
        [HttpGet("region")]
        public IActionResult GetReg()
        {
            return Ok(_distributorI.GetDeviceRegion());
        }
    }
}
