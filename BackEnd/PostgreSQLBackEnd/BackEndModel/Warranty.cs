using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndModel
{
    public class Warranty
    {
        public string clientName { get; set; }
        public int deviceSerialNumber { get; set; }
        public string brand { get; set; }
        public string purchaseDate { get; set; }
        public string expireDate { get; set; }
        public string deviceTypeName { get; set; }
    }
}
