package dao;

import config.MySQL;
import model.InsuranceClaim;
import model.enums.ClaimStatus;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InsuranceClaimDAO {

    private static String sqlEscape(String s) {
        if (s == null) return "";
        return s.replace("'", "''");
    }

    public static int insert(InsuranceClaim claim) throws Exception {
        String sql = String.format(
            "INSERT INTO insurance_claims (bill_id, patient_id, insurance_provider, claim_status, submitted_at, reviewed_at, notes, policy_number) " +
            "VALUES (%d,%d, '%s', '%s', '%s', %s, '%s','%s')",
            claim.getBillId(),
            claim.getPatientId(),
            sqlEscape(claim.getInsuranceProvider()),
            sqlEscape(claim.getClaimStatus().name()),
            sqlEscape(claim.getSubmittedAt()),
            (claim.getReviewedAt() == null ? "NULL" : "'" + sqlEscape(claim.getReviewedAt()) + "'"),
            sqlEscape(claim.getNotes()),
            sqlEscape(claim.getPolicyNumber())
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

    public static int update(InsuranceClaim claim) throws Exception {
        String sql = String.format(
            "UPDATE insurance_claims SET bill_id=%d, patient_id=%d, insurance_provider='%s', claim_status='%s', submitted_at='%s', reviewed_at=%s, notes='%s', policy_number='%s' " +
            "WHERE claim_id=%d",
            claim.getBillId(),
            claim.getPatientId(),
            sqlEscape(claim.getInsuranceProvider()),
            sqlEscape(claim.getClaimStatus().name()),
            sqlEscape(claim.getSubmittedAt()),
            (claim.getReviewedAt() == null ? "NULL" : "'" + sqlEscape(claim.getReviewedAt()) + "'"),
            sqlEscape(claim.getNotes()),
            sqlEscape(claim.getPolicyNumber()),
            claim.getId()
        );
        return MySQL.update(sql);
    }

    public static int delete(int claimId) throws Exception {
        String sql = "DELETE FROM insurance_claims WHERE claim_id = " + claimId;
        return MySQL.update(sql);
    }

    public static InsuranceClaim findById(int claimId) throws Exception {
        String sql = "SELECT * FROM insurance_claims WHERE claim_id = " + claimId;
        ResultSet rs = MySQL.query(sql);
        InsuranceClaim c = null;
        if (rs.next()) {
            c = mapRow(rs);
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return c;
    }
    
    public static InsuranceClaim findByBillId(int billId) throws Exception {
        String sql = "SELECT * FROM insurance_claims WHERE bill_id = " + billId;
        ResultSet rs = MySQL.query(sql);
        InsuranceClaim c = null;
        if (rs.next()) {
            c = mapRow(rs);
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return c;
    }

    public static List<InsuranceClaim> findAll() throws Exception {
        String sql = "SELECT * FROM insurance_claims ORDER BY submitted_at DESC";
        ResultSet rs = MySQL.query(sql);
        List<InsuranceClaim> list = new ArrayList<>();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
        try { rs.getStatement().close(); } catch (Exception ignore) {}
        try { rs.close(); } catch (Exception ignore) {}
        return list;
    }

    private static InsuranceClaim mapRow(ResultSet rs) {
        try {
            int id = rs.getInt("claim_id");
            int billId = rs.getInt("bill_id");
            int patientId = rs.getInt("patient_id");

            ClaimStatus status = null;
            try {
                String t = rs.getString("claim_status");
                if (t != null) {
                    status = ClaimStatus.valueOf(t);
                }
            } catch (Exception ignore) {}

            String insuranceProvider = rs.getString("insurance_provider");
            String submittedAt = rs.getString("submitted_at");
            String reviewedAt = rs.getString("reviewed_at");
            String notes = rs.getString("notes");
            String policyNumber = rs.getString("policy_number");

            return new InsuranceClaim(id, billId, patientId, insuranceProvider,
                    policyNumber, status, submittedAt, reviewedAt, notes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
