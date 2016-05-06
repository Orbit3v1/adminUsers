package screen;

import entity.Nomenclature;
import entity.Person;
import utils.Security;
import utils.SessionUtil;
import validator.Validator;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by ayaroslavtsev on 06.05.2016.
 */
public abstract class EntityScreen<T> {

    @Inject
    protected EntityManagerFactory entityManagerFactory;
    @Inject
    protected ResourceBundle resourceBundle;

    protected T entity;
    protected boolean edit;
    private Map<String, Boolean> userPA;


    protected void initSecurity() {
        userPA = Security.getUserPrivilegeAction(getScreenName());
    }

    protected abstract String getScreenName();
    public abstract boolean save();

    public String editEntity(T entity) {
        edit = true;
        this.entity = entity;
        return "editEntity";
    }

    public String newEntity() {
        return "editEntity";
    }

    public String exit() {
        SessionUtil.cleanSession(getScreenName());
        return "toList";
    }

    public String saveOnly() {
        save();
        return "";
    }

    public String saveAndExit() {
        return  save() ? exit() : "";
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }
}
