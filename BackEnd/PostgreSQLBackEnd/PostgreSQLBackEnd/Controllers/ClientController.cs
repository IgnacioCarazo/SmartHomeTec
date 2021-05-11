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
    public class ClientController : Controller
    {
        private readonly IClient _clientI;

        public ClientController(IClient clientI) => _clientI = clientI;

        [HttpGet]
        public async Task<IActionResult> GetAllClients()
        {
            return Ok(await _clientI.GetAllClients());
        }

        [HttpGet("{email}")]
        public async Task<IActionResult> GetClient(string email)
        {
            return Ok(await _clientI.GetClient(email));
        }

        [HttpGet("login/{email}/{password}")]
        public async Task<IActionResult> GetClientLogin(string email, string password)
        {
            Client clientlog = await _clientI.GetClient(email);
            if(clientlog == null)
            {
                clientlog = new Client();
                clientlog.name = "";
                return Ok(clientlog);
            }
            if (ClientManager.logAuthorization(clientlog,password)==false)
            {
                clientlog.name = "";
            }
            return Ok(clientlog);
        }

        [HttpPost]
        public async Task<IActionResult> NewClient([FromBody] Client client)
        {
            if(client == null)
            {
                return BadRequest();
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var insertClient = await _clientI.InsertClient(client);
            return Ok(client);
        }

        [HttpPut]
        public async Task<IActionResult> UpdtClient([FromBody] Client client)
        {
            if (client == null)
            {
                return BadRequest();
            }
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            await _clientI.UpdateClient(client);
            return Ok();
        }

        [HttpDelete("{email}")]
        public async Task<IActionResult> DltClient(string email)
        {
            await _clientI.DeleteClient(new Client { email = email});
            return Ok();
        }
    }
}
