package com.app.web.list;

import com.app.data.entity.interfaces.Unique;
import com.app.data.entity.interfaces.Copy;
import com.app.utils.AddMessage;
import com.app.security.Security;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class EntityList<T extends Unique & Copy<T>> {
    @PersistenceContext
    protected EntityManager em;
    @Inject
    protected AddMessage addMessage;
    @Inject
    protected Validator<T> validator;
    protected Logger logger = Logger.getLogger(getClass());

    protected List<T> entities = new ArrayList<>();
    protected List<T> filteredEntities;
    protected Map<String, Boolean> userPA;
    protected T editEntity;
    protected T original;
    protected boolean edit;

    protected abstract T createEntity();

    protected abstract String getScreenName();

    protected abstract List<T> getData();

    @Loggable
    @PostConstruct
    public void init() {
        userPA = Security.getUserPrivilegeAction(getScreenName());
        editEntity = createEntity();
        initList();
        filteredEntities = entities;
    }

    public void initList() {
        entities.clear();
        entities.addAll(getData());
    }

    public void add() {
        edit = false;
        editEntity = createEntity();
    }

    public void edit(T entity) {
        edit = true;
        original = entity;
        editEntity = entity.copy();
    }

    public boolean save() {
        boolean success = false;
        preSave();
        if (validate()) {
            try {
                saveAttempt();
                addMessage.setMessage(null, "success.save", FacesMessage.SEVERITY_INFO);
                success = true;
            } catch (OptimisticLockException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
                addMessage.setMessage(":mainForm:infoPanel", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
                addMessage.setMessage(":mainForm:infoPanel", "error.exception", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            addMessage.setMessage(null, "error.data", FacesMessage.SEVERITY_ERROR);
        }
        return success;
    }

    public void saveExit(){
        if(save()){
            closeDialog();
        }
    }

    protected void preSave() {
    }

    protected void postSave() {
    }

    protected void postEdit() {
    }

    protected boolean validate() {
        return validator.validate(editEntity);
    }

    protected void saveAttempt() {
        if (edit) {
            editEntity();
            postEdit();
        } else {
            saveEntity();
            postSave();
            edit(editEntity);
        }
    }

    private void saveEntity() {
        mergeEntity();
        entities.add(editEntity);
    }

    private void editEntity() {
        mergeEntity();
        original.copyData(editEntity);
    }

    @Transactional
    protected void mergeEntity() {
        editEntity = em.merge(editEntity);
    }

    public void closeDialog() {
        closeDialog("popup");
    }

    public void closeDialog(String popupName) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('" + popupName + "').hide();");
    }

    public boolean delete(T entity) {
        logger.info("delete. id = " + entity.getId());
        if (canDelete(entity)) {
            deleteAttempt(entity);
            removeFromParent(entity);
            entities.remove(entity);
            filteredEntities.remove(entity);
            addMessage.setMessage(null, "success.delete", FacesMessage.SEVERITY_INFO);
            logger.info("delete success");
            return true;
        } else {
            logger.info("delete fail");
            setErrorMessage();
            return false;
        }

    }

    @Transactional
    protected void deleteAttempt(T entity) {
        em.remove(em.find(entity.getClass(), entity.getId()));
    }

    protected void removeFromParent(T entity) {
    }

    protected void setErrorMessage() {
        addMessage.setMessage(":mainForm:infoPanel", "error.calc.delete", FacesMessage.SEVERITY_ERROR);
    }

    protected boolean canDelete(T entity) {
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
