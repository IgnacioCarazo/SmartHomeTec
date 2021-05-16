using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndData.InterfaceRepositories
{
    /// <summary>
    /// Interfaz para implementar operaciones sql del modelo Devic
    /// </summary>
    public interface IDevice
    {
        Task<IEnumerable<Device>> GetAllDevices();
        Task<Device> GetDevice(int _serialNumber);
        Task<bool> InsertDevice(Device device);
        Task<bool> UpdateDevice(Device device);
        Task<bool> DeleteDevice(Device device);
        Task<int> DeviceAVG();
    }
}
