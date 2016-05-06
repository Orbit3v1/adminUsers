package menu;

import entity.PrivilegeAction;
import entity.PrivilegeActionId;
import org.springframework.context.annotation.Scope;
import utils.Security;

import javax.inject.Named;

@Named("adminMenu")
@Scope("request")
public class AdminMenu {

    private boolean personRead;
    private boolean roleRead;
    private boolean nomenclatureRead;

    public AdminMenu() {
    }

    public boolean isPersonRead() {
        return Security.hasAnyPrivilegeAction(new PrivilegeAction(new PrivilegeActionId("adminMenuPerson", "READ")));
    }

    public void setPersonRead(boolean personRead) {
        this.personRead = personRead;
    }

    public boolean isRoleRead() {
        return Security.hasAnyPrivilegeAction(new PrivilegeAction(new PrivilegeActionId("adminMenuRole", "READ")));
    }

    public void setRoleRead(boolean roleRead) {
        this.roleRead = roleRead;
    }

    public boolean isNomenclatureRead() {
        return Security.hasAnyPrivilegeAction(new PrivilegeAction(new PrivilegeActionId("nomenclatureMenuRole", "READ")));
    }

    public void setNomenclatureRead(boolean nomenclatureRead) {
        this.nomenclatureRead = nomenclatureRead;
    }
}
