package com.app.web.list;

import com.app.data.entity.Unique;
import com.app.data.entity.Copy;
import com.app.utils.AddMessage;
import com.app.utils.Security;
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
import java.util.List;
import java.util.Map;

public abstract class EntityList <T extends Unique & Copy<T>> {
    @PersistenceContext
    protected EntityManager em;
    @Inject
    protected AddMessage addMessage;
    @Inject
    protected Validator<T> validator;
    protected Logger logger = Logger.getLogger(getClass());

    protected List<T> entities;
    protected List<T> filteredEntities;
    protected Map<String, Boolean> userPA;
    protected T editEntity;
    protected T original;
    protected boolean edit;

    protected abstract T createEntity();
    protected abstract String getScreenName();

    @Loggable
    @PostConstruct
    public void init(){
        userPA = Security.getUserPrivilegeAction(getScreenName());
        editEntity = createEntity();
    }

    public void add(){
        edit = false;
        editEntity = createEntity();
    }

    public void edit(T entity){
        edit = true;
        original = entity;
        editEntity = entity.copy();
    }

    public void save(){
        if (validate()) {
            try {
                saveAttempt();
                addMessage.setMessage(null, "success.save", FacesMessage.SEVERITY_INFO);
            } catch (OptimisticLockException e){
                logger.error(e.getMessage());
                e.printStackTrace();
                addMessage.setMessage("mainForm:entities", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
            } catch (Exception e){
                logger.error(e.getMessage());
                e.printStackTrace();
                addMessage.setMessage("mainForm:entities", "error.exception", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            addMessage.setMessage(null, "error.data", FacesMessage.SEVERITY_ERROR);
        }
    }

    protected boolean validate(){
       return validator.validate(editEntity);
    }

    protected void saveAttempt(){
        if(edit){
            editEntity();
        } else {
            saveEntity();
        }
        closeDialog();
    }

    private void saveEntity(){
        mergeEntity();
        entities.add(editEntity);
    }

    private void editEntity(){
        mergeEntity();
        original.copyData(editEntity);
    }

    @Transactional
    private void mergeEntity(){
        editEntity = em.merge(editEntity);
    }

    private void closeDialog(){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('popup').hide();");
    }

    @Transactional
    public void delete(T entity){
        logger.info("delete. id = " + entity.getId());
        if(canDelete(entity)){
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            entities.remove(entity);
            filteredEntities.remove(entity);
            addMessage.setMessage(null, "success.delete", FacesMessage.SEVERITY_INFO);
            logger.info("delete success");
        } else {
            logger.info("delete fail");
            addMessage.setMessage("mainForm:panel", "error.delete", FacesMessage.SEVERITY_ERROR);
        }
    }


    private boolean canDelete(T entity){
        return true;
    }

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    public List<T> getFilteredEntities() {
        return filteredEntities;
    }

    public void setFilteredEntities(List<T> filteredEntities) {
        this.filteredEntities = filteredEntities;
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }

    public T getEditEntity() {
        return editEntity;
    }

    public void setEditEntity(T editEntity) {
        this.editEntity = editEntity;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
