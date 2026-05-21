package patterns.cor.claims;

public abstract class BaseClaimHandler implements ClaimHandler {

    private ClaimHandler next;

    @Override
    public ClaimHandler setNext(ClaimHandler next) {
        this.next = next;
        return next;
    }

    @Override
    public void handle(ClaimContext context) {

        process(context);

        if (next != null && !context.isFinalized()) {
            next.handle(context);
        }
    }

    protected abstract void process(ClaimContext context);
}
