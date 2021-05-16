using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndData.InterfaceRepositories
{
    /// <summary>
    /// Interfaz para implementar operaciones sql del modelo Order
    /// </summary>
    public interface IOrder
    {
        Task<IEnumerable<Order>> GetAllOrder();
        Task<Order> GetOrder(int id);
        Task<int> InsertOrder(Order order);
        Task<bool> UpdateOrder(Order order);
        Task<bool> DeleteOrder(Order order);
        Task<Device> GetDevice(Order order);
        Task<bool> InsertInvoice(Invoice invoice);
        Task<string> GetOwnerName(string email);
        Task<bool> InsertWarranty(Warranty warranty);
        Task<int> GetDeviceWarranty(string name);
    }
}
