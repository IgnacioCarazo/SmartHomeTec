using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PostgreSQLBackEnd.DataManagement
{
    /// <summary>
    /// clase para manejo de logica de admin
    /// </summary>
    public class AdminManager
    {
        /// <summary>
        /// metodo para verificar autentificacion de login admin
        /// </summary>
        /// <param name="adminlog">admin</param>
        /// <param name="password">password del admin</param>
        /// <returns></returns>
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
