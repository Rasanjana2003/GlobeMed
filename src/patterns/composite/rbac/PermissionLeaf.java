package patterns.composite.rbac;

import model.enums.Action;


public class PermissionLeaf implements Grant {

    private final Action action;

    public PermissionLeaf(Action action) {
        this.action = action;
    }

    @Override
    public boolean allows(Action a) {
        return this.action == a;
    }

    @Override
    public String name() {
        return "PERM(" + action.name() + ")";
    }
}
