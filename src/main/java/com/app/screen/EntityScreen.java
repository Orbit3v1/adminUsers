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

    protected boolean closed;
    protected boolean saved;

    @PostConstruct
    protected void initSecurity() {
        logger.info("init security");
        userPA = Security.getUserPrivilegeAction(getScreenName());
    }

    protected abstract String getScreenName();
    protected abstract boolean save();


    public void initEntity(){

    }

    public void  exit() {
        logger.info("exit");
        closed = true;
    }

    public void saveOnly() {
        logger.info("save entity"  + entity.getId());
        if (save()) {
            saved = true;
        }
    }

    public void saveAndExit() {
        logger.info("save entity and exit. "  + entity.getId());
        if (save()) {
            saved = true;
            exit();
        }
    }

    public boolean isDisabled(String privilege){
        Boolean res = edit ? userPA.get(privilege + "E") : userPA.get(privilege + "W");
        return  res == null || !res;
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

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
}
