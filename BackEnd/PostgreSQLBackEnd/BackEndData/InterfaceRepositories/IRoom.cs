using BackEndModel;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BackEndData.InterfaceRepositories
{
    public interface IRoom
    {
        Task<IEnumerable<Room>> GetAllRooms();
        Task<Room> GetRoom(string name);
        Task<bool> InsertRoom(Room room);
        Task<bool> UpdateRoom(Room room);
        Task<bool> DeleteRoom(Room room);
    }
}
