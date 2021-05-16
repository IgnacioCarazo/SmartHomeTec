using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndData.InterfaceRepositories
{
    /// <summary>
    /// Interfaz para implementar operaciones sql del modelo Distributor
    /// </summary>
    public interface IDistributor
    {
        public Task<IEnumerable<Distributor>> GetDistributors();
        public void GetExcel();
        public IEnumerable<dynamic> GetDeviceRegion();
    }
}
