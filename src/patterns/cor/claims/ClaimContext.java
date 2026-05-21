package patterns.cor.claims;

import model.Bill;
import model.InsuranceClaim;
import model.Patient;
import model.Staff;

import java.util.HashMap;
import java.util.Map;

public class ClaimContext {

    private Bill bill;
    private InsuranceClaim claim;
    private Patient patient;
    private Staff submittedBy;

    private boolean approved = false;
    private boolean rejected = false;
    private String rejectedReason;

    private double coveredAmount = 0.0;
    private double patientResponsibility = 0.0;

    private final StringBuilder decisionLog = new StringBuilder();

    private final Map<String, Object> attributes = new HashMap<>();

    public ClaimContext() {
    }

    public ClaimContext(Bill bill, InsuranceClaim claim, Patient patient, Staff submittedBy) {
        this.bill = bill;
        this.claim = claim;
        this.patient = patient;
        this.submittedBy = submittedBy;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public InsuranceClaim getClaim() {
        return claim;
    }

    public void setClaim(InsuranceClaim claim) {
        this.claim = claim;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Staff getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(Staff submittedBy) {
        this.submittedBy = submittedBy;
    }

    public boolean isApproved() {
        return approved;
    }

    public boolean isRejected() {
        return rejected;
    }

    public String getRejectedReason() {
        return rejectedReason;
    }

    public double getCoveredAmount() {
        return coveredAmount;
    }

    public void setCoveredAmount(double coveredAmount) {
        this.coveredAmount = coveredAmount;
    }

    public double getPatientResponsibility() {
        return patientResponsibility;
    }

    public void setPatientResponsibility(double patientResponsibility) {
        this.patientResponsibility = patientResponsibility;
    }

    public void markApproved() {
        this.approved = true;
        this.rejected = false;
        appendLog("Claim marked APPROVED.");
    }

    public void markRejected(String reason) {
        this.rejected = true;
        this.approved = false;
        this.rejectedReason = reason;
        appendLog("Claim marked REJECTED: " + reason);
    }

    public boolean isFinalized() {
        return approved || rejected;
    }

    public void appendLog(String entry) {
        if (entry == null) {
            return;
        }
        if (decisionLog.length() > 0) {
            decisionLog.append(" | ");
        }
        decisionLog.append(entry);
    }

    public String getDecisionLog() {
        return decisionLog.toString();
    }

    public void putAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public Map<String, Object> getAllAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "ClaimContext{"
                + "billId=" + (bill != null ? bill.getId() : "null")
                + ", claimId=" + (claim != null ? claim.getId() : "null")
                + ", patientId=" + (patient != null ? patient.getId() : "null")
                + ", submittedBy=" + (submittedBy != null ? submittedBy.getId() : "null")
                + ", approved=" + approved
                + ", rejected=" + rejected
                + ", coveredAmount=" + coveredAmount
                + ", patientResponsibility=" + patientResponsibility
                + '}';
    }
}
