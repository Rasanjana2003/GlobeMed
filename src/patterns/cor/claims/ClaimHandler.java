package patterns.cor.claims;

public interface ClaimHandler {

    void handle(ClaimContext context);

    ClaimHandler setNext(ClaimHandler next);
}
