package security;

public abstract class BaseHandler implements SecurityHandler {
    protected SecurityHandler nextHandler;

    @Override
    public void setNext(SecurityHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public AccessResult handle(AccessRequest request) {
        
        AccessResult result = processRequest(request);

        
        if (!result.isGranted() && nextHandler != null) {
            return nextHandler.handle(request);
        }

        return result;
    }

    protected abstract AccessResult processRequest(AccessRequest request);
}
