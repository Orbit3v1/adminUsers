package com.app.web.list;

import com.app.data.entity.TNC;
import com.app.data.entity.Work;
import com.app.utils.AddMessage;
import com.app.utils.Security;
import com.app.web.Loggable;
import org.apache.log4j.Logger;
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

@Named("workList")
@Scope("view")
public class WorkList {
    @PersistenceContext
    private EntityManager em;
    @Inject
    protected AddMessage addMessage;
    protected Logger logger = Logger.getLogger(getClass());

    private List<Work> works;
    private Map<String, Boolean> userPA;
    private Work editEntity;
    private Work original;
    private boolean edit;

    @Loggable
    @PostConstruct
    public void init(){
        Query query = em.createQuery("select p from Work p order by p.name");
        works = query.getResultList();
        userPA = Security.getUserPrivilegeAction("personList");
        editEntity = new Work();
    }

    public void add(){
        edit = false;
        editEntity = new Work();
    }

    public void edit(Work work){
        edit = true;
        original = work;
        editEntity = work.copy();
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

    @Transactional
    private void saveAttempt(){
        if(edit){
            original.copyData(editEntity);
            original = em.merge(original);
        } else {
            editEntity = em.merge(editEntity);
            works.add(editEntity);
        }
        editEntity = new Work();
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        works = works;
    }

    public Work getEditEntity() {
        return editEntity;
    }

    public void setEditEntity(Work editEntity) {
        this.editEntity = editEntity;
    }
}
