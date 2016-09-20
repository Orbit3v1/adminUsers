package com.app.web.list;

import com.app.data.entity.Function;
import com.app.utils.AddMessage;
import com.app.utils.Security;
import com.app.web.Loggable;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Named("functionList")
@Scope("view")
public class FunctionList {
    @PersistenceContext
    private EntityManager em;
    @Inject
    protected AddMessage addMessage;
    protected Logger logger = Logger.getLogger(getClass());

    private List<Function> entities;
    private Map<String, Boolean> userPA;
    private Function editEntity;
    private Function original;
    private boolean edit;

    @Loggable
    @PostConstruct
    public void init(){
        Query query = em.createQuery("select p from Function p order by p.name");
        entities = query.getResultList();
        userPA = Security.getUserPrivilegeAction("personList");
        editEntity = new Function();
    }

    public void add(){
        edit = false;
        editEntity = new Function();
    }

    public void edit(Function entity){
        edit = true;
        original = entity;
        editEntity = entity.copy();
    }

    public void save(){
        if (validate()) {
            try {
                saveAttempt();
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
            addMessage.setMessage("mainForm:panel", ".error.title", FacesMessage.SEVERITY_ERROR);
        }
    }

    private boolean validate() {
        return true;
    }

    public void select(Function entity) {
        RequestContext.getCurrentInstance().closeDialog(entity);
    }

    @Transactional
    private void saveAttempt(){
        if(edit){
            original.copyData(editEntity);
            original = em.merge(original);
        } else {
            editEntity = em.merge(editEntity);
            entities.add(editEntity);
        }
        editEntity = new Function();
    }

    public List<Function> getEntities() {
        return entities;
    }

    public void setEntities(List<Function> entities) {
        this.entities = entities;
    }

    public Function getEditEntity() {
        return editEntity;
    }

    public void setEditEntity(Function editEntity) {
        this.editEntity = editEntity;
    }
}
