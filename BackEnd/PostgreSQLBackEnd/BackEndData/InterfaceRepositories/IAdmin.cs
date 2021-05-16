using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

///
namespace BackEndData.InterfaceRepositories
{
    /// <summary>
    /// Interfaz para implementar operaciones sql del modelo Admin
    /// </summary>
    public interface IAdmin
    {
        Task<Admin> GetAdmin(string email);
    }
}
