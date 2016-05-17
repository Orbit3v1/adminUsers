package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PrivilegeActionId implements Serializable{

    @Column(name="privilege")
    private String privilege;

    @Column(name="action")
    private String action;

    public PrivilegeActionId() {
    }

    public PrivilegeActionId(String privilege, String action) {
        this.privilege = privilege;
        this.action = action;
    }

    @Override
    public int hashCode() {
        return (privilege != null && action != null)
                ? (getClass().hashCode() + privilege.hashCode() + action.hashCode())
                : super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null && getClass() == obj.getClass() && privilege != null && action != null)
                ? privilege.equals(((PrivilegeActionId) obj).privilege) && action.equals(((PrivilegeActionId) obj).action)
                : (obj == this);
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
