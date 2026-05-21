package security;

import model.Staff;
import model.enums.Action;
import patterns.composite.rbac.AuthorizationService;

public class RoleCheckHandler extends BaseHandler {

    private final AuthorizationService auth = new AuthorizationService();

    @Override
    protected AccessResult processRequest(AccessRequest request) {
        Staff staff = request.getStaff();

        boolean canViewSensitive = auth.can(staff, Action.VIEW_PATIENT_SENSITIVE);
        boolean canListPatients = auth.can(staff, Action.VIEW_PATIENT_LIST);

        Action action = request.getAction();

        if (action == Action.UPDATE_PATIENT) {
            boolean canUpdate = auth.can(staff, Action.UPDATE_PATIENT);
            if (!canUpdate) {
                return new AccessResult(false, "You do not have permission to update patient data.", true);
            }
            return new AccessResult(true, "Update permitted.", false);
        }

        if (action == Action.DELETE_PATIENT) {
            boolean canDelete = auth.can(staff, Action.DELETE_PATIENT);
            if (!canDelete) {
                return new AccessResult(false, "You do not have permission to delete patient data.", true);
            }
            return new AccessResult(true, "Delete permitted.", false);
        }

        if (action == Action.VIEW_PATIENT_LIST) {
            if (!canListPatients) {
                return new AccessResult(false, "You are not allowed to view patient lists.", true);
            }
            
            return new AccessResult(true, "Patient list access granted.", !canViewSensitive);
        }

        if (action == Action.VIEW_PATIENT_SENSITIVE) {
            if (canViewSensitive) {
                return new AccessResult(true, "Full access granted.", false);
            } else {
                return new AccessResult(true, "Restricted: sensitive fields masked.", true);
            }
        }

        return new AccessResult(false, "Unknown action.", true);
    }

}
