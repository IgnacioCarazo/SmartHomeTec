using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreSQLBackEnd.DataManagement
{
    public class AdminManager
    {
        public static bool logAuthorization(Admin adminlog, string password)
        {
            if (adminlog.password == password)
            {
                return true;
            }
            return false;
        }
    }
}
