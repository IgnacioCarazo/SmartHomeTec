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
    public class ControlController : Controller
    {
        private readonly IControl _controlI;

        public ControlController(IControl controlI) => _controlI = controlI;

        [HttpGet]
        public async Task<IActionResult> GetAllControl()
        {
            return Ok(await _controlI.GetAll());
        }

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
