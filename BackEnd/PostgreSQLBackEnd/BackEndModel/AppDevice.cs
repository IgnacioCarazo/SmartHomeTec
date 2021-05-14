using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndModel
{
    public class AppDevice
    {
        public int serialNumber { get; set; }
        public string description { get; set; }
        public string consumption { get; set; }
        public string brand { get; set; }
        public string type { get; set;}
        public string room { get; set; }
        public string createdDate { get; set; }
        public string emailOwner { get; set; }
        public bool active { get; set; }
    }
}
