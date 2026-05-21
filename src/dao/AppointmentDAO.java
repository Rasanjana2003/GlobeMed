package dao;

import config.MySQL;
import model.Appointment;
import model.enums.AppointmentStatus;
import model.enums.AppointmentType;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    private static final DateTimeFormatter SQL_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    
    private static String sqlEscape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("'", "''");
    }

    private static String formatDateTime(LocalDateTime dt) {
        if (dt == null) {
            return null;
        }
        return dt.format(SQL_DATETIME);
    }

    public static int insert(Appointment a) throws Exception {
        String sql = String.format(
                "INSERT INTO appointments (patient_id, staff_id, created_by, facility_id, room_id, type, title, notes, start_time, end_time, status) VALUES (%d, %d, %d, %d, %s, '%s', '%s', '%s', '%s', '%s', '%s')",
                a.getPatientId(),
                a.getStaffId(),
                a.getCreatedBy(),
                a.getFacilityId(),
                (a.getRoomId() == null ? "NULL" : a.getRoomId().toString()),
                sqlEscape(a.getType().name()),
                sqlEscape(a.getTitle()),
                sqlEscape(a.getNotes()),
                formatDateTime(a.getStartTime()),
                formatDateTime(a.getEndTime()),
                sqlEscape(a.getStatus().name())
        );

        MySQL.update(sql);

        ResultSet rs = MySQL.query("SELECT LAST_INSERT_ID() AS id");
        int id = -1;
        if (rs.next()) {
            id = rs.getInt("id");
        }
        try {
            rs.getStatement().close();
        } catch (Exception ignore) {
        }
        try {
            rs.close();
        } catch (Exception ignore) {
        }
        return id;
    }

    public static int update(Appointment a) throws Exception {
        String sql = String.format(
                "UPDATE appointments SET patient_id=%d, staff_id=%d, created_by=%d, facility_id=%d, room_id=%s, type='%s', title='%s', notes='%s', start_time='%s', end_time='%s', status='%s' WHERE appointment_id=%d",
                a.getPatientId(),
                a.getStaffId(),
                a.getCreatedBy(),
                a.getFacilityId(),
                (a.getRoomId() == null ? "NULL" : a.getRoomId().toString()),
                sqlEscape(a.getType().name()),
                sqlEscape(a.getTitle()),
                sqlEscape(a.getNotes()),
                formatDateTime(a.getStartTime()),
                formatDateTime(a.getEndTime()),
                sqlEscape(a.getStatus().name()),
                a.getId()
        );
        return MySQL.update(sql);
    }

    public static int delete(int appointmentId) throws Exception {
        String sql = "DELETE FROM appointments WHERE appointment_id = " + appointmentId;
        return MySQL.update(sql);
    }

    public static Appointment findById(int appointmentId) throws Exception {
        String sql = "SELECT * FROM appointments WHERE appointment_id = " + appointmentId;
        ResultSet rs = MySQL.query(sql);
        Appointment a = null;
        if (rs.next()) {
            a = mapRow(rs);
        }
        try {
            rs.getStatement().close();
        } catch (Exception ignore) {
        }
        try {
            rs.close();
        } catch (Exception ignore) {
        }
        return a;
    }

    public static List<Appointment> findByPatient(int patientId) throws Exception {
        String sql = "SELECT * FROM appointments WHERE patient_id = " + patientId + " ORDER BY start_time";
        ResultSet rs = MySQL.query(sql);
        List<Appointment> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        try {
            rs.getStatement().close();
        } catch (Exception ignore) {
        }
        try {
            rs.close();
        } catch (Exception ignore) {
        }
        return list;
    }

    public static List<Appointment> findByStaff(int staffId) throws Exception {
        String sql = "SELECT * FROM appointments WHERE staff_id = " + staffId + " ORDER BY start_time";
        ResultSet rs = MySQL.query(sql);
        List<Appointment> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        try {
            rs.getStatement().close();
        } catch (Exception ignore) {
        }
        try {
            rs.close();
        } catch (Exception ignore) {
        }
        return list;
    }

    public static List<Appointment> findByStaffAndDate(int staffId, LocalDate date) throws Exception {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        String sql = "SELECT * FROM appointments WHERE staff_id = " + staffId
                + " AND start_time >= '" + formatDateTime(startOfDay)
                + "' AND start_time <= '" + formatDateTime(endOfDay)
                + "' ORDER BY start_time";

        ResultSet rs = MySQL.query(sql);
        List<Appointment> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        try {
            rs.getStatement().close();
        } catch (Exception ignore) {
        }
        try {
            rs.close();
        } catch (Exception ignore) {
        }
        return list;
    }

    public static List<Appointment> findAll() throws Exception {
        String sql = "SELECT * FROM appointments ORDER BY start_time";
        ResultSet rs = MySQL.query(sql);
        List<Appointment> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        try {
            rs.getStatement().close();
        } catch (Exception ignore) {
        }
        try {
            rs.close();
        } catch (Exception ignore) {
        }
        return list;
    }

    public static List<Appointment> searchAppointment(String q) throws Exception {
        if (q == null || q.trim().isEmpty()) {
            return findAll(); 
        }

        String searchTerm = sqlEscape(q.trim());

        
        String sql = "SELECT a.* FROM appointments a "
                + "LEFT JOIN patients p ON a.patient_id = p.patient_id "
                + "LEFT JOIN staff s ON a.staff_id = s.staff_id "
                + "WHERE a.title LIKE '%" + searchTerm + "%' OR "
                + "a.type LIKE '%" + searchTerm + "%' OR "
                + "a.status LIKE '%" + searchTerm + "%' OR "
                + "p.name LIKE '%" + searchTerm + "%' OR "
                + "s.name LIKE '%" + searchTerm + "%' "
                + "ORDER BY a.start_time";

        ResultSet rs = MySQL.query(sql);
        List<Appointment> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        try {
            rs.getStatement().close();
        } catch (Exception ignore) {
        }
        try {
            rs.close();
        } catch (Exception ignore) {
        }
        return list;
    }

    
    public static List<Appointment> findOverlappingAppointments(int staffId, Integer roomId, LocalDateTime start, LocalDateTime end) throws Exception {
        String roomCondition = (roomId == null) ? "1=1" : "(room_id IS NOT NULL AND room_id = " + roomId + ")";
        String sql = "SELECT * FROM appointments WHERE (staff_id = " + staffId + " OR " + roomCondition + ") "
                + "AND (start_time < '" + formatDateTime(end) + "' AND end_time > '" + formatDateTime(start) + "') "
                + "AND status IN ('PENDING','CONFIRMED') ORDER BY start_time";

        ResultSet rs = MySQL.query(sql);
        List<Appointment> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        try {
            rs.getStatement().close();
        } catch (Exception ignore) {
        }
        try {
            rs.close();
        } catch (Exception ignore) {
        }
        return list;
    }

    private static Appointment mapRow(ResultSet rs) {
        try {
            int id = rs.getInt("appointment_id");
            int patientId = rs.getInt("patient_id");
            int staffId = rs.getInt("staff_id");
            int createdBy = rs.getInt("created_by");
            int facilityId = rs.getInt("facility_id");

            Integer roomId = null;
            try {
                int r = rs.getInt("room_id");
                if (!rs.wasNull()) {
                    roomId = r;
                }
            } catch (Exception ignore) {
            }

            AppointmentType type = null;
            try {
                String t = rs.getString("type");
                if (t != null) {
                    type = AppointmentType.valueOf(t);
                }
            } catch (Exception ignore) {
            }

            String title = rs.getString("title");
            String notes = rs.getString("notes");

            LocalDateTime start = null;
            try {
                java.sql.Timestamp ts = rs.getTimestamp("start_time");
                if (ts != null) {
                    start = ts.toLocalDateTime();
                }
            } catch (Exception ignore) {
            }

            LocalDateTime end = null;
            try {
                java.sql.Timestamp ts2 = rs.getTimestamp("end_time");
                if (ts2 != null) {
                    end = ts2.toLocalDateTime();
                }
            } catch (Exception ignore) {
            }

            AppointmentStatus status = null;
            try {
                String s = rs.getString("status");
                if (s != null) {
                    status = AppointmentStatus.valueOf(s);
                }
            } catch (Exception ignore) {
            }

            Appointment a = new Appointment(id, patientId, staffId, createdBy, facilityId, roomId, type, title, notes, start, end, status);
            return a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
