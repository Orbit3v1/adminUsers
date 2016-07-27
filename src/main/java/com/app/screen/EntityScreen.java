package com.app.screen;

import com.app.entity.Nomenclature;
import com.app.entity.Person;
import com.app.entity.Unique;
import com.app.utils.AddMessage;
import com.app.utils.Security;
import com.app.utils.SessionUtil;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;
import java.util.ResourceBundle;


public abstract class EntityScreen<T extends Unique> {

    @Inject
    protected ResourceBundle resourceBundle;
    @Inject
    protected AddMessage addMessage;

    @PersistenceContext
    protected EntityManager em;

    protected T entity;
    protected boolean edit;
    private Map<String, Boolean> userPA;
    protected Logger logger = Logger.getLogger(getClass());

    @PostConstruct
    protected void initSecurity() {
        logger.info("init security");
        userPA = Security.getUserPrivilegeAction(getScreenName());
    }

    protected abstract String getScreenName();
    protected abstract boolean save();

    public String editEntity(T entity) {
        logger.info("edit entity");
        if(entity != null) {
            logger.info("entity id: " + entity.getId());
            edit = true;
            initEntity(entity);
            return "editEntity";
        } else {
            logger.warn("null entity");
            return newEntity();
        }
    }

    public String newEntity() {
        logger.info("add entity");
        initEntity();
        edit = false;
        return "editEntity";
    }

    public void initEntity(T entity){
        this.entity = entity;
    }

    public void initEntity(){

    }

    public String exit() {
        logger.info("exit");
        SessionUtil.cleanSession(getScreenName());
        return "toList";
    }

    public String saveOnly() {
        logger.info("save entity"  + entity.getId());
        save();
        return "";
    }

    public boolean isDisabled(String privilege){
        Boolean res = edit ? userPA.get(privilege + "E") : userPA.get(privilege + "W");
        return  res == null || !res;
    }

    public String saveAndExit() {
        logger.info("save entity and exit. "  + entity.getId());
        return  save() ? exit() : "";
    }

    protected String getParameter(String name){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String passedParameter = facesContext.getExternalContext().getRequestParameterMap().get(name);
        return passedParameter;
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
