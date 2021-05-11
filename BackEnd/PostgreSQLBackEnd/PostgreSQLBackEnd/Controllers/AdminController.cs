using BackEndData.InterfaceRepositories;
using BackEndModel;
using Microsoft.AspNetCore.Mvc;
using PostgreSQLBackEnd.DataManagement;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreSQLBackEnd.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AdminController : Controller
    {
        private readonly IAdmin _adminI;

        public AdminController(IAdmin adminI) => _adminI = adminI;

        [HttpGet("{email}")]
        public async Task<IActionResult> GetAdmn(string email)
        { 
            return Ok(await _adminI.GetAdmin(email));
        }

        [HttpGet("login/{email}/{password}")]
        public async Task<IActionResult> GetAdminLogin(string email, string password)
        {
            Admin adminlog = await _adminI.GetAdmin(email);
            if (adminlog == null)
            {
                adminlog = new Admin();
                adminlog.email = "";
                return Ok(adminlog);
            }
            if (AdminManager.logAuthorization(adminlog, password) == false)
            {
                adminlog.email = "";
            }
            return Ok(adminlog);
        }
    }
}
