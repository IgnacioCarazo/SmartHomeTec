using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndData.InterfaceRepositories
{
    public interface IDeviceType
    {
        Task<IEnumerable<DeviceType>> GetAllTypes();
        Task<DeviceType> GetType(string email);
        Task<bool> InsertType(DeviceType type);
        Task<bool> UpdateType(DeviceType type);
        Task<bool> DeleteType(DeviceType type);
    }
}
