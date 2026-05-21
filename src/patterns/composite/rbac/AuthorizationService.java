package patterns.composite.rbac;

import model.enums.Action;
import model.Staff;

public class AuthorizationService {

    public boolean can(Staff staff, Action a) {
        return staff != null && staff.getRoleGrant() != null && staff.getRoleGrant().allows(a);
    }
}
