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
    [Route("api/[controller]")]
    [ApiController]
    public class InvoiceController : Controller
    {
        private readonly IInvoice _invoiceI;

        public InvoiceController(IInvoice invoiceI) => _invoiceI = invoiceI;

        [HttpGet]
        public async Task<IActionResult> GetAllInvoices()
        {
            return Ok(await _invoiceI.GetInvoices());
        }

        [HttpGet("{invoiceNumber}")]
        public async Task<IActionResult> GetClient(int invoiceNumber)
        {
            return Ok(await _invoiceI.GetInvoice(invoiceNumber));
        }

    }
}
