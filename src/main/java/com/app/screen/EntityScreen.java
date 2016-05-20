package com.app.screen;

import com.app.entity.Nomenclature;
import com.app.entity.Person;
import com.app.utils.Security;
import com.app.utils.SessionUtil;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;
import java.util.ResourceBundle;


public abstract class EntityScreen<T> {

    @Inject
    protected ResourceBundle resourceBundle;

    @PersistenceContext
    protected EntityManager em;

    protected T entity;
    protected boolean edit;
    private Map<String, Boolean> userPA;

    @PostConstruct
    protected void initSecurity() {
        userPA = Security.getUserPrivilegeAction(getScreenName());
    }

    protected abstract String getScreenName();
    protected abstract boolean save();

    public String editEntity(T entity) {
        if(entity != null) {
            edit = true;
            initEntity(entity);
            return "editEntity";
        } else {
            return newEntity();
        }
    }

    public String newEntity() {
        return "editEntity";
    }

    public void initEntity(T entity){
        this.entity = entity;
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
