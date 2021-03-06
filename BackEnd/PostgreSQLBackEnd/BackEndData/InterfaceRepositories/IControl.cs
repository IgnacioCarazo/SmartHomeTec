using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndData.InterfaceRepositories
{
    /// <summary>
    /// Interfaz para modelo Control
    /// </summary>
    public interface IControl
    {
        Task<IEnumerable<Control>> GetAll();
        Task<bool> InsertControl(Control control);
    }
}
