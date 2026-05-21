package service;

import dao.RoomDAO;
import java.util.List;
import model.Room;


public class RoomService {
    
    private RoomDAO roomDAO;
    
    public RoomService(){
        this.roomDAO = new RoomDAO();
    }
    
    public List<Room> getRoomsByFacility(int id) throws Exception{
        return roomDAO.findByFacility(id);
    }
    
    public Room getRoomById(int id) throws Exception{
        return roomDAO.findById(id);
    }
    
    public void addRoom(Room room) throws Exception{
        roomDAO.insert(room);
    }
    
    public void updateRoom(Room room) throws Exception{
        roomDAO.update(room);
    }
    
    public void deleteRoom(int roomId) throws Exception{
        roomDAO.delete(roomId);
    }
}
