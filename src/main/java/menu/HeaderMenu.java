package menu;

import entity.PrivilegeAction;
import entity.PrivilegeActionId;
import org.springframework.context.annotation.Scope;
import utils.Security;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Named("headerMenu")
@Scope("request")
public class HeaderMenu {

    private boolean graphRead;
    private boolean adminRead;

    public HeaderMenu() {
    }

    public boolean isGraphRead() {
        return Security.hasAnyPrivilegeAction(new PrivilegeAction(new PrivilegeActionId("graphMenu", "READ")));
    }

    public void setGraphRead(boolean graphRead) {
        this.graphRead = graphRead;
    }

    public boolean isAdminRead() {
        return Security.hasAnyPrivilegeAction(new PrivilegeAction(new PrivilegeActionId("adminMenu", "READ")));
    }

    public void setAdminRead(boolean adminRead) {
        this.adminRead = adminRead;
    }
}
