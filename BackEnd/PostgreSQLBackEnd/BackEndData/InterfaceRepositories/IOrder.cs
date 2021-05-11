using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndData.InterfaceRepositories
{
    public interface IOrder
    {
        Task<IEnumerable<Order>> GetAllOrder();
        Task<Order> GetOrder(int id);
        Task<bool> InsertOrder(Order order);
        Task<bool> UpdateOrder(Order order);
        Task<bool> DeleteOrder(Order order);
    }
}
