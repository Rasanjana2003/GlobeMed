package dao;

import config.MySQL;
import model.Staff;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {

    
    private static String sqlEscape(String s) {
        if (s == null) return "";
        return s.replace("'", "''");
    }


    public static void insert(Staff staff) throws Exception {
        String query = "INSERT INTO staff (name, role, speciality, email, phone, password_hash, facility_id) VALUES ("
                + "'" + sqlEscape(staff.getName()) + "',"
                + "'" + sqlEscape(staff.getRole()) + "',"
                + "'" + sqlEscape(staff.getSpeciality()) + "',"
                + "'" + sqlEscape(staff.getEmail()) + "',"
                + "'" + sqlEscape(staff.getPhone()) + "',"
                + "'" + sqlEscape(staff.getPasswordHash()) + "',"
                + (staff.getFacilityId() > 0 ? staff.getFacilityId() : "NULL")
                + ")";
        MySQL.update(query);


    }

   
    public static int update(Staff staff) throws Exception {
        String query = "UPDATE staff SET "
                + "name='" + sqlEscape(staff.getName()) + "', "
                + "role='" + sqlEscape(staff.getRole()) + "', "
                + "speciality='" + sqlEscape(staff.getSpeciality()) + "', "
                + "email='" + sqlEscape(staff.getEmail()) + "', "
                + "phone='" + sqlEscape(staff.getPhone()) + "', "
                + "password_hash='" + sqlEscape(staff.getPasswordHash()) + "', "
                + "facility_id=" + (staff.getFacilityId() > 0 ? staff.getFacilityId() : "NULL")
                + " WHERE staff_id=" + staff.getId();
        return MySQL.update(query);
    }

 
    public static int delete(int staffId) throws Exception {
        String query = "DELETE FROM staff WHERE staff_id=" + staffId;
        return MySQL.update(query);
    }

  
    public static Staff findById(int staffId) throws Exception {
        String query = "SELECT * FROM staff WHERE staff_id=" + staffId;
        ResultSet rs = MySQL.query(query);
        Staff staff = null;
        if (rs.next()) {
            staff = mapRow(rs);
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return staff;
    }
    
    public static Staff findByEmail(String email) throws Exception {
        String query = "SELECT * FROM staff WHERE email='" + email +"'";
        ResultSet rs = MySQL.query(query);
        Staff staff = null;
        if (rs.next()) {
            staff = mapRow(rs);
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return staff;
    }

   
    public static List<Staff> findAll() throws Exception {
        String query = "SELECT * FROM staff ORDER BY name";
        ResultSet rs = MySQL.query(query);
        List<Staff> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return list;
    }

  
    public static List<Staff> findByKeyword(String keyword) throws Exception {
        String x = sqlEscape(keyword);
        String query = "SELECT * FROM staff WHERE name LIKE '%" + x + "%' OR email LIKE '%" + x + "%' OR role LIKE '%" + x + "%' ORDER BY name";
        ResultSet rs = MySQL.query(query);
        List<Staff> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return list;
    }
    
     public static List<Staff> findDoctorByKeyword(String keyword) throws Exception {
        String x = sqlEscape(keyword);
        String query = "SELECT * FROM staff " +
               "WHERE (name LIKE '%" + x + "%' OR email LIKE '%" + x + "%') " +
               "AND role='DOCTOR' " +
               "ORDER BY name";
        ResultSet rs = MySQL.query(query);
        List<Staff> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return list;
    }

   
    private static Staff mapRow(ResultSet rs) {
        try {
            int id = rs.getInt("staff_id");
            String name = rs.getString("name");
            String role = rs.getString("role");
            String speciality = null;
            try { speciality = rs.getString("speciality"); } catch (Exception ignore) {}
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String passwordHash = rs.getString("password_hash");
            int facilityId = rs.getInt("facility_id");

            
            Staff staff = new Staff(id, name, role,speciality, email, phone, passwordHash, facilityId);
            
            try { staff.setSpeciality(speciality); } catch (NoSuchMethodError | Exception ignore) {}
            return staff;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
