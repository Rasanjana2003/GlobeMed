package dao;

import config.MySQL;
import model.Room;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Appointment;

public class RoomDAO {

    
    private static String sqlEscape(String s) {
        if (s == null) return "";
        return s.replace("'", "''");
    }

    public static int insert(Room room) throws Exception {
        String sql = String.format(
                "INSERT INTO rooms (facility_id, name, type, capacity) VALUES (%d, '%s', '%s', %s)",
                room.getFacilityId(),
                sqlEscape(room.getName()),
                sqlEscape(room.getType()),
                (room.getCapacity() == null ? "NULL" : room.getCapacity().toString())
        );
        MySQL.update(sql);

        ResultSet rs = MySQL.query("SELECT LAST_INSERT_ID() AS id");
        int id = -1;
        if (rs.next()) {
            id = rs.getInt("id");
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return id;
    }

    public static int update(Room room) throws Exception {
        String sql = String.format(
                "UPDATE rooms SET facility_id=%d, name='%s', type='%s', capacity=%s WHERE room_id=%d",
                room.getFacilityId(),
                sqlEscape(room.getName()),
                sqlEscape(room.getType()),
                (room.getCapacity() == null ? "NULL" : room.getCapacity().toString()),
                room.getId()
        );
        return MySQL.update(sql);
    }

    public static int delete(int roomId) throws Exception {
        String sql = "DELETE FROM rooms WHERE room_id = " + roomId;
        return MySQL.update(sql);
    }

    public static Room findById(int roomId) throws Exception {
        String sql = "SELECT * FROM rooms WHERE room_id = " + roomId;
        ResultSet rs = MySQL.query(sql);
        Room r = null;
        if (rs.next()) {
            r = mapRow(rs);
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return r;
    }
    
    public static Room findAvailableRoom(int facilityId, LocalDateTime start, LocalDateTime end) throws Exception {
    String sql = "SELECT * FROM rooms WHERE facility_id = " + facilityId + " ORDER BY name";
    ResultSet rs = MySQL.query(sql);
    Room available = null;

    while (rs.next()) {
        Room room = mapRow(rs);

       
        List<Appointment> overlaps = AppointmentDAO.findOverlappingAppointments(
                -1, 
                room.getId(),
                start,
                end
        );

        if (overlaps.isEmpty()) {
            available = room;
            break;
        }
    }

    try { rs.getStatement().close(); } catch (Exception ignore) {}
    try { rs.close(); } catch (Exception ignore) {}

    return available;
}


    public static List<Room> findByFacility(int facilityId) throws Exception {
        String sql = "SELECT * FROM rooms WHERE facility_id = " + facilityId + " ORDER BY name";
        ResultSet rs = MySQL.query(sql);
        List<Room> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return list;
    }

    public static List<Room> findAll() throws Exception {
        String sql = "SELECT * FROM rooms ORDER BY facility_id, name";
        ResultSet rs = MySQL.query(sql);
        List<Room> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return list;
    }

    private static Room mapRow(ResultSet rs) {
        try {
            int id = rs.getInt("room_id");
            int facilityId = rs.getInt("facility_id");
            String name = rs.getString("name");
            String type = rs.getString("type");
            Integer capacity = null;
            try {
                int c = rs.getInt("capacity");
                if (!rs.wasNull()) capacity = c;
            } catch (Exception ignore) {}

            return new Room(id, facilityId, name, type, capacity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
