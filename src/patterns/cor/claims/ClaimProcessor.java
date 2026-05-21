package patterns.cor.claims;

public class ClaimProcessor {

    private final ClaimHandler chain;

    public ClaimProcessor() {

        ClaimHandler validation = new ValidationHandler();
        ClaimHandler coverage = new CoverageCheckHandler();
        ClaimHandler approval = new ApprovalHandler();

        validation.setNext(coverage);
        coverage.setNext(approval);

        this.chain = validation; 
    }

    public void process(ClaimContext context) {
        if (context == null) {
            throw new IllegalArgumentException("ClaimContext cannot be null");
        }
        chain.handle(context);
    }
}
