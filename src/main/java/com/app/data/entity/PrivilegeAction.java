package com.app.data.entity;

import com.app.data.entity.interfaces.Unique;

import javax.persistence.*;

@Entity
@Table(name = "privilege_action")
public class PrivilegeAction implements Unique<PrivilegeActionId> {

    @EmbeddedId
    private PrivilegeActionId id;

    @ManyToOne
    @JoinColumn(name="privilege", insertable=false, updatable=false)
    private Privilege privilege;

    @ManyToOne
    @JoinColumn(name="action", insertable=false, updatable=false)
    private Action action;

    public PrivilegeAction() {
    }

    public PrivilegeAction(PrivilegeActionId id) {
        this.id = id;
    }

    public PrivilegeAction(String privilege, String action){
        this.id = new PrivilegeActionId(privilege, action);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null && getClass() == obj.getClass() && id != null)
                ? id.equals(((PrivilegeAction) obj).id)
                : (obj == this);
    }

    @Override
    public int hashCode() {
        return (getClass().hashCode() + id.hashCode());
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
