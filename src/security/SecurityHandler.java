package security;

public interface SecurityHandler {
    void setNext(SecurityHandler nextHandler);
    AccessResult handle(AccessRequest request);
}
