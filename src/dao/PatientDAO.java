package dao;

import config.MySQL;
import model.Patient;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    
    private static String sqlEscape(String s) {
        if (s == null) return "";
        return s.replace("'", "''");
    }

    public static int insert(Patient p) throws Exception {
        String sql = String.format(
            "INSERT INTO patients (name, dob, gender, address, phone, nic, medical_history, treatment_plans,email) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s')",
            sqlEscape(p.getName()),
            sqlEscape(p.getDob()),
            sqlEscape(p.getGender()),
            sqlEscape(p.getAddress()),
            sqlEscape(p.getPhone()),
            sqlEscape(p.getNic()),
            sqlEscape(p.getMedicalHistory()),
            sqlEscape(p.getTreatmentPlans()),
            sqlEscape(p.getEmail())
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

    public static int update(Patient p) throws Exception {
        String sql = String.format(
            "UPDATE patients SET name='%s', dob='%s', gender='%s', address='%s', phone='%s', nic='%s', medical_history='%s', treatment_plans='%s', email='%s' WHERE patient_id=%d",
            sqlEscape(p.getName()),
            sqlEscape(p.getDob()),
            sqlEscape(p.getGender()),
            sqlEscape(p.getAddress()),
            sqlEscape(p.getPhone()),
            sqlEscape(p.getNic()),
            sqlEscape(p.getMedicalHistory()),
            sqlEscape(p.getTreatmentPlans()),
            sqlEscape(p.getEmail()),
            p.getId()
        );
        return MySQL.update(sql);
    }

    public static int delete(int patientId) throws Exception {
        String sql = "DELETE FROM patients WHERE patient_id = " + patientId;
        return MySQL.update(sql);
    }

    public static Patient findById(int patientId) throws Exception {
        String sql = "SELECT * FROM patients WHERE patient_id = " + patientId;
        ResultSet rs = MySQL.query(sql);
        Patient p = null;
        if (rs.next()) {
            p = mapRow(rs);
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return p;
    }

    public static List<Patient> findAll() throws Exception {
        String sql = "SELECT * FROM patients ORDER BY name";
        ResultSet rs = MySQL.query(sql);
        List<Patient> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return list;
    }

    public static List<Patient> findByNameOrNic(String q) throws Exception {
        String x = sqlEscape(q);
        String sql = String.format("SELECT * FROM patients WHERE name LIKE '%%%s%%' OR nic LIKE '%%%s%%' ORDER BY name", x, x);
        ResultSet rs = MySQL.query(sql);
        List<Patient> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return list;
    }

    
    private static Patient mapRow(ResultSet rs) {
        try {
            int id = rs.getInt("patient_id");
            String name = rs.getString("name");
            String dob = rs.getString("dob");
            String gender = rs.getString("gender");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            String email = null;
            try { email = rs.getString("email"); } catch (Exception ignore) {}
            String nic = rs.getString("nic");
            String medicalHistory = rs.getString("medical_history");
            String treatmentPlans = rs.getString("treatment_plans");
            return new Patient(id, name, dob, gender, address, phone, email, nic, medicalHistory, treatmentPlans);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
