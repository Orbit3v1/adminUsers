package menu;

import entity.PrivilegeAction;
import entity.PrivilegeActionId;
import org.springframework.context.annotation.Scope;
import utils.Security;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.Map;

@Named("headerMenu")
@Scope("request")
public class HeaderMenu {

    private boolean canGraphRead;
    private boolean canAdminRead;
    private Map<String, Boolean> userPA;

    public HeaderMenu() {
    }

    @PostConstruct
    public void init() {
        userPA = Security.getUserPrivilegeAction("headerMenu");
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

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }
}
