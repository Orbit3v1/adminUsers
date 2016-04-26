package menu;

import entity.PrivilegeAction;
import entity.PrivilegeActionId;
import org.springframework.context.annotation.Scope;
import utils.Security;

import javax.inject.Named;

@Named("headerMenu")
@Scope("request")
public class HeaderMenu {

    private boolean canGraphRead;
    private boolean canAdminRead;

    public HeaderMenu() {
    }

    public boolean isCanGraphRead() {
        return Security.hasAnyPrivilegeAction(new PrivilegeAction(new PrivilegeActionId("graphMenu", "READ")));
    }

    public void setCanGraphRead(boolean canGraphRead) {
        this.canGraphRead = canGraphRead;
    }

    public boolean isCanAdminRead() {
        return Security.hasAnyPrivilegeAction(new PrivilegeAction(new PrivilegeActionId("adminMenu", "READ")));
    }

    public void setCanAdminRead(boolean canAdminRead) {
        this.canAdminRead = canAdminRead;
    }

}
