using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreSQLBackEnd.DataManagement
{
    /// <summary>
    /// clase para manejo del login del client
    /// </summary>
    public class ClientManager
    {
        /// <summary>
        /// metodo para verificar credenciales de client
        /// </summary>
        /// <param name="clientlog">client a verificar</param>
        /// <param name="password">contrasena del client</param>
        /// <returns></returns>
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
