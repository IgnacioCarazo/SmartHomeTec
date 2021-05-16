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
    public class ControlController : Controller
    {
        private readonly IControl _controlI;

        /// <summary>
        /// constructor de la clase
        /// </summary>
        /// <param name="controlI">interfaz Control</param>
        public ControlController(IControl controlI) => _controlI = controlI;

        /// <summary>
        /// metodo para get de todo control
        /// </summary>
        /// <returns>lista control</returns>
        [HttpGet]
        public async Task<IActionResult> GetAllControl()
        {
            return Ok(await _controlI.GetAll());
        }

        /// <summary>
        /// metodo para post de nuevo control
        /// </summary>
        /// <param name="control">control a agregar</param>
        /// <returns>string</returns>
        [HttpPost]
        public async Task<IActionResult> NewControl([FromBody] Control control)
        {
            if (control == null)
            {
                return BadRequest();
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var insertType = await _controlI.InsertControl(control);
            return Ok("Inserted");
        }
    }

}
