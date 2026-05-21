package patterns.composite.rbac;

import java.util.ArrayList;
import java.util.List;
import model.enums.Action;

public class RoleComposite implements Grant {

    private final String name;
    private final List<Grant> children = new ArrayList<>();

    public RoleComposite(String name) {
        this.name = name;
    }

    public RoleComposite add(Grant g) {
        children.add(g);
        return this;
    }

    @Override
    public boolean allows(Action a) {
        for (Grant g : children) {
            if (g.allows(a)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String name() {
        return "ROLE(" + name + ")";
    }
}
