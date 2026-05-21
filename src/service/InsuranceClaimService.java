package service;

import dao.InsuranceClaimDAO;
import model.InsuranceClaim;
import model.enums.ClaimStatus;

import java.util.List;
import model.Bill;
import model.Patient;
import model.Staff;
import patterns.cor.claims.ClaimContext;
import patterns.cor.claims.ClaimProcessor;

public class InsuranceClaimService {

    
    public int createClaim(Bill bill, InsuranceClaim claim, Patient patient, Staff submittedBy) throws Exception {

        ClaimContext context = new ClaimContext(bill, claim, patient, submittedBy);

        ClaimProcessor processor = new ClaimProcessor();
        processor.process(context);

        System.out.println("Decision: " + (context.isApproved() ? "Approved" : "Rejected"));
        System.out.println("Log: " + context.getDecisionLog());
        
        if(context.isApproved()){
            claim.setClaimStatus(ClaimStatus.APPROVED);
        }else if (context.isRejected()){
            claim.setClaimStatus(ClaimStatus.REJECTED);
        }
        claim.setReviewedAt(java.time.LocalDateTime.now().toString());
        claim.setNotes(context.getDecisionLog());

        return InsuranceClaimDAO.insert(claim);
    }

    
    public boolean updateClaim(InsuranceClaim claim) throws Exception {
        return InsuranceClaimDAO.update(claim) > 0;
    }

    
    public boolean deleteClaim(int claimId) throws Exception {
        return InsuranceClaimDAO.delete(claimId) > 0;
    }

    
    public InsuranceClaim getClaimById(int claimId) throws Exception {
        return InsuranceClaimDAO.findById(claimId);
    }
    
    
    public InsuranceClaim getClaimByBillId(int billId) throws Exception {
        return InsuranceClaimDAO.findByBillId(billId);
    }

    
    public List<InsuranceClaim> getAllClaims() throws Exception {
        return InsuranceClaimDAO.findAll();
    }

    
    public boolean reviewClaim(int claimId, ClaimStatus status, String notes) throws Exception {
        InsuranceClaim claim = InsuranceClaimDAO.findById(claimId);
        if (claim == null) {
            return false;
        }
        claim.setClaimStatus(status);
        claim.setReviewedAt(java.time.LocalDateTime.now().toString());
        claim.setNotes(notes);
        return InsuranceClaimDAO.update(claim) > 0;
    }
}
