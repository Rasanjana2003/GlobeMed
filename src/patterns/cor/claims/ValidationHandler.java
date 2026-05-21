package patterns.cor.claims;

import model.Bill;
import model.InsuranceClaim;
import model.Patient;

public class ValidationHandler extends BaseClaimHandler {

    @Override
    protected void process(ClaimContext context) {
        Bill bill = context.getBill();
        Patient patient = context.getPatient();
        InsuranceClaim claim = context.getClaim();

        if (bill == null) {
            context.markRejected("Enter Bill Details.");
            return;
        }

        if (patient == null) {
            context.markRejected("Enter Patient Details.");
            return;
        }

        if (bill.getAmount() <= 0) {
            context.markRejected("Bill amount should be lager than 0.");
            return;
        }

        context.appendLog("Successful.");
    }
}
