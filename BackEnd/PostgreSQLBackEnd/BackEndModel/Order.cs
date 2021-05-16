
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndModel
{
    public class Order
    {
        public int consecutiveNumberOrder { get; set; }
        public int deviceSerialNumber { get; set; }
        public string date { get; set; }
        public string hour { get; set; }
        public string brand { get; set; }
        public int price { get; set; }
        public string deviceOwner { get; set; }
        public int orderID { get; set; }
    }
}
