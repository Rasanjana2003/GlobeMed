package patterns.cor.claims;

import model.Bill;
import model.InsuranceClaim;
import model.Patient;

public class CoverageCheckHandler extends BaseClaimHandler {

    @Override
    protected void process(ClaimContext context) {
        Bill bill = context.getBill();
        InsuranceClaim claim = context.getClaim();
        Patient patient = context.getPatient();

        if (bill == null || patient == null) {
            context.markRejected("Coverage check failed: missing bill or patient.");
            return;
        }

        double billAmount = bill.getAmount();

        if (claim == null) {
            context.setCoveredAmount(0.0);
            context.setPatientResponsibility(billAmount);
            context.appendLog("No insurance claim. Patient responsible for full amount: " + billAmount);
            return;
        }

        String serviceType = bill.getServiceType();
        double coveragePercent;

        switch (serviceType.toLowerCase()) {
            case "surgery":
                coveragePercent = 0.9;
                break;
            case "consultation":
                coveragePercent = 0.7;
                break;
            case "treatment":
                coveragePercent = 0.8;
                break;
            case "medication":
                coveragePercent = 0.5;
                break;
            default:
                coveragePercent = 0.6;
                break;
        }

        double covered = billAmount * coveragePercent;
        double patientShare = billAmount - covered;

        context.setCoveredAmount(covered);
        context.setPatientResponsibility(patientShare);

        context.appendLog("Coverage applied for service [" + serviceType + "]: Insurance covers "
                + covered + ", Patient pays " + patientShare);
    }
}
