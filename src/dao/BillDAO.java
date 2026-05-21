package dao;

import config.MySQL;
import model.Bill;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.enums.BillStatus;
import model.enums.PaymentMethod;

public class BillDAO {

    private static String sqlEscape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("'", "''");
    }

    public static int insert(Bill bill) throws Exception {
        String sql = String.format(
                "INSERT INTO bills (patient_id,appointment_id, amount, service_type, created_at, status,payment_method) VALUES (%d,%d, %.2f, '%s', '%s', '%s', '%s')",
                bill.getPatientId(),
                bill.getAppointmentId(),
                bill.getAmount(),
                sqlEscape(bill.getServiceType()),
                sqlEscape(bill.getCreatedAt()), 
                sqlEscape(bill.getStatus().name()),
                sqlEscape(bill.getPaymentMethod().name())
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

    public static int update(Bill bill) throws Exception {
        String sql = String.format(
                "UPDATE bills SET patient_id=%d, amount=%.2f, service_type='%s', status='%s',payment_method='%s' WHERE bill_id=%d",
                bill.getPatientId(),
                bill.getAmount(),
                sqlEscape(bill.getServiceType()),
                sqlEscape(bill.getStatus().name()),
                sqlEscape(bill.getPaymentMethod().name()),
                bill.getId()
        );
        return MySQL.update(sql);
    }

    public static int delete(int billId) throws Exception {
        String sql = "DELETE FROM bills WHERE bill_id = " + billId;
        return MySQL.update(sql);
    }

    public static Bill findById(int billId) throws Exception {
        String sql = "SELECT * FROM bills WHERE bill_id = " + billId;
        ResultSet rs = MySQL.query(sql);
        Bill b = null;
        if (rs.next()) {
            b = mapRow(rs);
        }
        try {
            rs.getStatement().close();
        } catch (Exception ignore) {
        }
        try {
            rs.close();
        } catch (Exception ignore) {
        }
        return b;
    }
    
    
    
    public static Bill findByAppointmentId(int appointmentId) throws Exception {
        String sql = "SELECT * FROM bills WHERE appointment_id = " + appointmentId;
        ResultSet rs = MySQL.query(sql);
        Bill b = null;
        if (rs.next()) {
            b = mapRow(rs);
        }
        try {
            rs.getStatement().close();
        } catch (Exception ignore) {
        }
        try {
            rs.close();
        } catch (Exception ignore) {
        }
        return b;
    }

    public static List<Bill> findAll() throws Exception {
        String sql = "SELECT * FROM bills ORDER BY created_date DESC";
        ResultSet rs = MySQL.query(sql);
        List<Bill> list = new ArrayList<>();
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
    
    public static List<Bill> findBillsByPatient(int patientId) throws Exception {
        String sql = "SELECT * FROM bills WHERE patient_id = '"+patientId+"'";
        ResultSet rs = MySQL.query(sql);
        List<Bill> list = new ArrayList<>();
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

    private static Bill mapRow(ResultSet rs) {
        try {
            int id = rs.getInt("bill_id");
            int patientId = rs.getInt("patient_id");
            int appointmentId = rs.getInt("appointment_id");
            double amount = rs.getDouble("amount");

            BillStatus status = null;
            try {
                String t = rs.getString("status");
                if (t != null) {
                    status = BillStatus.valueOf(t);
                }
            } catch (Exception ignore) {
            }

            PaymentMethod paymentMethod = null;
            try {
                String m = rs.getString("payment_method");
                if (m != null) {
                    paymentMethod = PaymentMethod.valueOf(m);
                }
            } catch (Exception ignore) {
            }

            String serviceType = rs.getString("service_type");
            String createdAt = rs.getString("created_at");
            return new Bill(id,patientId, appointmentId, amount, paymentMethod, status, createdAt, serviceType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
