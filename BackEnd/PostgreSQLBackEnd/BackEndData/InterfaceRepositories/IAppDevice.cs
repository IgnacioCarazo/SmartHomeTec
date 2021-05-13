using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndData.InterfaceRepositories
{
    public interface IAppDevice
    {
        Task<IEnumerable<AppDevice>> GetAllAppDevices();
        Task<AppDevice> GetAppDevice(int serialNumber);
        Task<bool> InsertAppDevice(AppDevice device);
    }
}
