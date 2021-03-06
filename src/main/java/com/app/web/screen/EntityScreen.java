package com.app.web.screen;

import com.app.data.entity.TNCSupplyItem;
import com.app.data.entity.interfaces.Unique;
import com.app.utils.AddMessage;
import com.app.security.Security;
import com.app.utils.EntityUtil;
import com.app.validator.Validator;
import com.app.web.Loggable;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
    @Inject
    protected EntityUtil entityUtil;

    @PersistenceContext
    protected EntityManager em;

    protected T entity;
    protected boolean edit;
    private Map<String, Boolean> userPA;
    protected Logger logger = Logger.getLogger(getClass());

    @Loggable
    @PostConstruct
    protected void initSecurity() {
        userPA = Security.getUserPrivilegeAction(getScreenName());
    }

    protected abstract String getScreenName();
    protected abstract void save();

    public void saveOnly() {
        logger.info("save entity"  + entity.getId());
        if (saveAttempt()) {
            executeJS("save();");
        }
    }

    public void saveAndExit() {
        logger.info("save entity and exit. "  + entity.getId());
        if (saveAttempt()) {
            executeJS("save();");
            exit();
        }
    }

    public void exit() {
        logger.info("exit");
        clearCash();
        executeJS("exit();");
    }

    protected void clearCash(){

    }

    protected void executeJS(String script){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute(script);
    }

    protected boolean saveAttempt(){
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

    @Transactional
    public void delete(){
        logger.info("delete. id = " + entity.getId());
        if(canDelete()){
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            postDelete();
            logger.info("delete success");
            executeJS("save();");
            exit();
        } else {
            logger.info("delete fail");
            addMessage.setMessage("mainForm:panel", "error.delete", FacesMessage.SEVERITY_ERROR);
        }
    }

    protected void postDelete(){

    }

    protected boolean canDelete(){
        return true;
    }

    public boolean isDisabled(String privilege){
        return  isDisabled(privilege, this.edit);
    }

    public boolean isDisabled(String privilege, boolean edit){
        Boolean res = edit ? userPA.get(privilege + "E") : userPA.get(privilege + "W");
        return  res == null || !res;
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
