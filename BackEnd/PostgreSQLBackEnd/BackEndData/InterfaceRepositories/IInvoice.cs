using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndData.InterfaceRepositories
{
    /// <summary>
    /// Interfaz para implementar operaciones sql del modelo Invoice
    /// </summary>
    public interface IInvoice
    {
        Task<IEnumerable<Invoice>> GetInvoices();
        Task<Invoice> GetInvoice(int invoiceNumber);
    }
}
