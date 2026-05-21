package patterns.cor.claims;

import model.Bill;
import model.Patient;

public class ApprovalHandler extends BaseClaimHandler {

    @Override
    protected void process(ClaimContext context) {
        Bill bill = context.getBill();
        Patient patient = context.getPatient();

        if (bill == null || patient == null) {
            context.markRejected("Approval failed: missing bill or patient data.");
            return;
        }

        double billAmount = bill.getAmount();
        double coveredAmount = context.getCoveredAmount();

        if (billAmount > 10000) {
            context.markRejected("Claim exceeds approval limit. Manual review required.");
            return;
        }

        if (coveredAmount <= 0) {
            context.markRejected("No coverage available for this claim.");
            return;
        }

        context.markApproved();
        context.appendLog("Claim APPROVED for patient [" + patient.getName() + "], bill total: "
                + billAmount + ", covered: " + coveredAmount);
    }
}
