using BackEndData.InterfaceRepositories;
using BackEndModel;
using Microsoft.AspNetCore.Mvc;
using PostgreSQLBackEnd.DataManagement;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Threading.Tasks;

namespace PostgreSQLBackEnd.Controllers
{
    /// <summary>
    /// controlador del modelo Invoice
    /// </summary>
    [Route("api/[controller]")]
    [ApiController]
    public class InvoiceController : Controller
    {
        private readonly IInvoice _invoiceI;

        /// <summary>
        /// constructor de clase
        /// </summary>
        /// <param name="invoiceI">interfaz Invoice</param>
        public InvoiceController(IInvoice invoiceI) => _invoiceI = invoiceI;

        /// <summary>
        /// metodo para get de todos los Invoice
        /// </summary>
        /// <returns>lista invoice</returns>
        [HttpGet]
        public async Task<IActionResult> GetAllInvoices()
        {
            return Ok(await _invoiceI.GetInvoices());
        }

        /// <summary>
        /// metodo para get de un invoice
        /// </summary>
        /// <param name="invoiceNumber">numero de invoice</param>
        /// <returns>invoice</returns>
        [HttpGet("{invoiceNumber}")]
        public async Task<IActionResult> GetClient(int invoiceNumber)
        {
            return Ok(await _invoiceI.GetInvoice(invoiceNumber));
        }

    }
}
