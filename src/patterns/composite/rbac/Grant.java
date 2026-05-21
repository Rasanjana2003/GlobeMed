package patterns.composite.rbac;

import model.enums.Action;

public interface Grant {
    boolean allows(Action action);
    String name();
}
