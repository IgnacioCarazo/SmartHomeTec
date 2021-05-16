using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndData.InterfaceRepositories
{
    //// <summary>
    /// Interfaz para implementar operaciones sql del modelo Device
    /// </summary>
    public interface IAppDevice
    {
        Task<IEnumerable<AppDevice>> GetAllAppDevices();
        Task<AppDevice> GetAppDevice(int serialNumber);
        Task<bool> InsertAppDevice(AppDevice device);
        public int GetActiveDevice();
    }
}
