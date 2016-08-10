package com.app.screen;

import com.app.entity.Nomenclature;
import com.app.entity.Person;
import com.app.entity.Role;
import com.app.entity.Unique;
import com.app.utils.AddMessage;
import com.app.utils.Security;
import com.app.utils.SessionUtil;
import com.app.validator.Validator;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import java.util.Map;
import java.util.ResourceBundle;


public abstract class EntityScreen<T extends Unique> {

    @Inject
    protected ResourceBundle resourceBundle;
    @Inject
    protected AddMessage addMessage;
    @Inject
    protected Validator<T> validator;

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
    protected abstract void save();

    public void saveOnly() {
        logger.info("save entity"  + entity.getId());
        if (saveAttempt()) {
            saved = true;
        }
    }

    public void saveAndExit() {
        logger.info("save entity and exit. "  + entity.getId());
        if (saveAttempt()) {
            saved = true;
            exit();
        }
    }

    public void  exit() {
        logger.info("exit");
        closed = true;
    }

    private boolean saveAttempt(){
        boolean success = false;
        if (validate()) {
            try {
                save();
                postSave();

                success = true;
            } catch (OptimisticLockException e){
                logger.error(e.getMessage());
                e.printStackTrace();
                addMessage.setMessage("mainForm:panel", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
            } catch (Exception e){
                logger.error(e.getMessage());
                e.printStackTrace();
                addMessage.setMessage("mainForm:panel", "error.exception", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            addMessage.setMessage("mainForm:panel", getScreenName() + ".error.title", FacesMessage.SEVERITY_ERROR);
        }
        return success;
    }

    private void postSave(){
        String bundleKey = edit ? getScreenName() + ".success.edit" : getScreenName() + ".success.save";
        addMessage.setMessage("mainForm:panel", bundleKey, FacesMessage.SEVERITY_INFO);
        edit = true;
    }

    protected boolean validate() {
        return validator.validate(entity, edit);
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
