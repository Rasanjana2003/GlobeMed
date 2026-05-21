package model;

import model.enums.ClaimStatus;

public class InsuranceClaim {

    private int id;
    private int billId;
    private int patientId;
    private String insuranceProvider;
    private String policyNumber;
    private ClaimStatus claimStatus;
    private String submittedAt;
    private String reviewedAt;
    private String notes;
    
    public InsuranceClaim(){}

    public InsuranceClaim(int id, int billId, int patientId, String insuranceProvider,
            String policyNumber, ClaimStatus claimStatus, String submittedAt, String reviewedAt, String notes) {
        this.id = id;
        this.billId = billId;
        this.patientId = patientId;
        this.insuranceProvider = insuranceProvider;
        this.policyNumber = policyNumber;
        this.claimStatus = claimStatus;
        this.submittedAt = submittedAt;
        this.reviewedAt = reviewedAt;
        this.notes = notes;
    }

    public InsuranceClaim(int billId, int patientId, String insuranceProvider,
            String policyNumber, ClaimStatus claimStatus, String submittedAt, String reviewedAt, String notes) {

        this.billId = billId;
        this.patientId = patientId;
        this.insuranceProvider = insuranceProvider;
        this.policyNumber = policyNumber;
        this.claimStatus = claimStatus;
        this.submittedAt = submittedAt;
        this.reviewedAt = reviewedAt;
        this.notes = notes;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(String submittedAt) {
        this.submittedAt = submittedAt;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ClaimStatus getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getReviewedAt() {
        return reviewedAt;
    }

    public void setReviewedAt(String reviewedAt) {
        this.reviewedAt = reviewedAt;
    }

   

}
