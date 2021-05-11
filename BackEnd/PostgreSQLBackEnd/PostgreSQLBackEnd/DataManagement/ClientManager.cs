using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreSQLBackEnd.DataManagement
{
    public class ClientManager
    {
        public static bool logAuthorization(Client clientlog, string password)
        {
            if(clientlog.password == password)
            {
                return true;
            }
            return false;
        }
    }
}
