package patterns.composite.rbac;

import java.util.EnumMap;
import model.enums.Action;

public class RoleFactory {

    private static final EnumMap<Action, PermissionLeaf> P = new EnumMap<>(Action.class);

    private static PermissionLeaf perm(Action a) {
        return P.computeIfAbsent(a, PermissionLeaf::new);
    }

    public static Grant of(String roleName) {
        String r = roleName == null ? "" : roleName.trim().toUpperCase();
        RoleComposite nurse = new RoleComposite("NURSE")
                .add(perm(Action.VIEW_PATIENT_BASIC))
                .add(perm(Action.VIEW_PATIENT_LIST));

        RoleComposite doctor = new RoleComposite("DOCTOR")
                .add(perm(Action.VIEW_PATIENT_BASIC))
                .add(perm(Action.VIEW_PATIENT_LIST))
                .add(perm(Action.VIEW_PATIENT_SENSITIVE))
                .add(perm(Action.UPDATE_PATIENT));

        RoleComposite pharmacist = new RoleComposite("PHARMACIST")
                .add(perm(Action.VIEW_PATIENT_BASIC)); 

        RoleComposite admin = new RoleComposite("ADMIN")
                .add(doctor) // inherits Doctor perms
                .add(perm(Action.MANAGE_BILLING))
                .add(perm(Action.MANAGE_CLAIMS))
                .add(perm(Action.MANAGE_USERS));

        RoleComposite receptionist = new RoleComposite("RECEPTIONIST")
                .add(perm(Action.VIEW_PATIENT_BASIC))
                .add(perm(Action.VIEW_PATIENT_LIST));

        switch (r) {
            case "DOCTOR":
                return doctor;
            case "NURSE":
                return nurse;
            case "PHARMACIST":
                return pharmacist;
            case "ADMIN":
                return admin;
            case "RECEPTIONIST":
                return receptionist;
            default:
                return new RoleComposite("GUEST"); // no permissions
        }
    }
}
