package entity;

import javax.persistence.*;

@Entity
@Table(name = "privilege_action")
public class PrivilegeAction {

    @EmbeddedId
    private PrivilegeActionId id;

    @ManyToOne
    @JoinColumn(name="privilege", insertable=false, updatable=false)
    Privilege privilege;

    @ManyToOne
    @JoinColumn(name="action", insertable=false, updatable=false)
    Action action;

    public PrivilegeAction() {
    }

    public PrivilegeAction(PrivilegeActionId id) {
        this.id = id;
    }

    public PrivilegeActionId getId() {
        return id;
    }

    public void setId(PrivilegeActionId id) {
        this.id = id;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}