using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndModel
{
    public class Client
    {
        public string name { get; set; }
        public string primaryLastName { get; set; }
        public string secondaryLastName { get; set; }
        public string email { get; set; }
        public string password { get; set; }
        public string continent { get; set; }
        
        public string country { get; set;}

        public string[] deliveryAdresses { get; set; }
    }
}
