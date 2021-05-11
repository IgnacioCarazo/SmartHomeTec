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
        public async Task<IActionResult> GetAllClients()
        {
            return Ok(await _distributorI.GetDistributors());
        }
    }
}
