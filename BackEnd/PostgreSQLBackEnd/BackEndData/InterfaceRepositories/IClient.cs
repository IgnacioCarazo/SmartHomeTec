using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndData.InterfaceRepositories
{
    /// <summary>
    /// Interfaz para implementar operaciones sql del modelo Client
    /// </summary>
    public interface IClient
    {
        Task<IEnumerable<Client>> GetAllClients();
        Task<Client> GetClient(string email);
        Task<bool> InsertClient(Client client);
        Task<bool> UpdateClient(Client client);
        Task<bool> DeleteClient(Client client);
    }
}
