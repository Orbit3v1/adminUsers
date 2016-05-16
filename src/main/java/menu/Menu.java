package menu;

import entity.PrivilegeAction;
import entity.PrivilegeActionId;
import org.springframework.context.annotation.Scope;
import utils.Security;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.Map;

@Named("menu")
@Scope("session")
public class Menu {

    private Map<String, Boolean> userPA;

    public Menu() {
    }

    @PostConstruct
    public void init() {
        userPA = Security.getUserPrivilegeAction("menu");
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }
}
