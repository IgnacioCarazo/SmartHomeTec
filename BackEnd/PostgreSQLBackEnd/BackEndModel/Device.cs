using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndModel
{
    public class Device
    {
        public string name { get; set; }
        public int serialNumber { get; set; }
        public string eConsumption { get; set; }
        public string brand { get; set; }
        public bool associated { get; set; }
        public string typeName { get; set; }
        public string ownerEmail { get; set; }
        public int dniDistributor { get; set; }
        public int price { get; set; }
    }
}
