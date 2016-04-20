package Entity2;

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
