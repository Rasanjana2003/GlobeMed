package security;

public class LoginCheckHandler extends BaseHandler {

    @Override
    protected AccessResult processRequest(AccessRequest request) {
        if (request.getRole() == null || request.getRole().isEmpty()) {
            return new AccessResult(false, "Access denied: User not logged in.");
        }

        
        return new AccessResult(true, "User logged in.");
    }
}
