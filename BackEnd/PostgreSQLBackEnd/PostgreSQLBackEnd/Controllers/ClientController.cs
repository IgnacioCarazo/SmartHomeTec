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
    public class ClientController : Controller
    {
        private readonly IClient _clientI;

        /// <summary>
        /// constructor
        /// </summary>
        /// <param name="clientI">interfaz</param>
        public ClientController(IClient clientI) => _clientI = clientI;

        /// <summary>
        /// metodo get para client
        /// </summary>
        /// <returns>lista de client</returns>
        [HttpGet]
        public async Task<IActionResult> GetAllClients()
        {
            return Ok(await _clientI.GetAllClients());
        }

        /// <summary>
        /// get de un client especifico
        /// </summary>
        /// <param name="email">email de client</param>
        /// <returns>client</returns>
        [HttpGet("{email}")]
        public async Task<IActionResult> GetClient(string email)
        {
            return Ok(await _clientI.GetClient(email));
        }

        /// <summary>
        /// metodo para verificar login de un cliente
        /// </summary>
        /// <param name="email">string email</param>
        /// <param name="password">string password</param>
        /// <returns>client verificado o no</returns>
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

        /// <summary>
        /// 
        /// </summary>
        /// <param name="client"></param>
        /// <returns></returns>
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
            return Ok(await _clientI.GetAllClients());
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
            return Ok(await _clientI.GetAllClients());
        }

        [HttpDelete("{email}")]
        public async Task<IActionResult> DltClient(string email)
        {
            await _clientI.DeleteClient(new Client { email = email});
            return Ok(await _clientI.GetAllClients());
        }
    }
}
