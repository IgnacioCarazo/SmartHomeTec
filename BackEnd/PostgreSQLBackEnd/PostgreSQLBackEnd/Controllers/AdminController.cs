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
    /// <summary>
    /// Clase controlador para peticiones http de controller
    /// </summary>
    [Route("api/[controller]")]
    [ApiController]
    public class AdminController : Controller
    {
        private readonly IAdmin _adminI;

        /// <summary>
        /// constructor de la clase
        /// </summary>
        /// <param name="adminI">interfaz admin</param>
        public AdminController(IAdmin adminI) => _adminI = adminI;

        /// <summary>
        /// metodo GET para un admin especifico
        /// </summary>
        /// <param name="email">Admin a obtener</param>
        /// <returns>admin</returns>
        [HttpGet("{email}")]
        public async Task<IActionResult> GetAdmn(string email)
        { 
            return Ok(await _adminI.GetAdmin(email));
        }

        /// <summary>
        /// metodo para verificar login
        /// </summary>
        /// <param name="email">email del admin</param>
        /// <param name="password">password del admin</param>
        /// <returns>admin</returns>
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
