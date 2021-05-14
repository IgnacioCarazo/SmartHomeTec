using BackEndData.InterfaceRepositories;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreSQLBackEnd.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class DistributorController : Controller
    {
        private readonly IDistributor _distributorI;

        public DistributorController(IDistributor distributorI) => _distributorI = distributorI;

        [HttpGet]
        public async Task<IActionResult> GetAllDistributors()
        {
            
            return Ok(await _distributorI.GetDistributors());
        }

        [HttpGet("excel")]
        public IActionResult GetEx()
        {
            _distributorI.GetExcel();

            return Ok("Generated");
        }

        [HttpGet("region")]
        public IActionResult GetReg()
        {
            return Ok(_distributorI.GetDeviceRegion());
        }
    }
}
