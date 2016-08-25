package com.app.filter;

import com.app.dictionary.ProductionReportSort;
import com.app.entity.Filter;
import com.app.utils.AddMessage;
import com.app.utils.Security;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import java.util.Map;


public abstract class FilterBean {

    @PersistenceContext
    protected EntityManager em;
    @Inject
    protected AddMessage addMessage;

    protected Logger logger = Logger.getLogger(getClass());

    protected Filter filter;
    protected Filter filterOriginal;
    protected Map<String, Boolean> userPA;

    protected abstract String getFilterName();
    protected abstract Filter createNew();

    @PostConstruct
    public void init() {
        userPA = Security.getUserPrivilegeAction(getFilterName());
        filter = createNew();
        loadFilter();
    }

    private void loadFilter(){
        Integer userId = Security.getCurrentUser().getId();
        filter = em.find(filter.getClass(), userId);
        if (filter == null) {
            resetFilter();
        }
        filterOriginal = createNew();
        filterOriginal.copyFrom(filter);
    }

    protected void resetFilter(){
        filter = createNew();
        filter.setId(Security.getCurrentUser().getId());
    }

    public void clear() {
        logger.info("clearFilter");
        filter.clear();
        filterOriginal.copyFrom(filter);
    }

    public void load(){
        logger.info("Load order list filter");
        loadFilter();
        addMessage.setMessage("mainForm:orders", "orderListFilter.loadSuccess", FacesMessage.SEVERITY_INFO);
    }

    public void find() {
        logger.info("find");
        filterOriginal.copyFrom(filter);
    }

    public void setSort(ProductionReportSort sort){
        if(sort.equals(filter.getSort())){
            sort = sort.getReverse();
        }
        filter.setSort(sort);
        filterOriginal.setSort(sort);
    }

    public void save() {
        logger.info("Save order list filter");
        try {
            saveData();
            addMessage.setMessage("mainForm:orders", "orderListFilter.saveSuccess", FacesMessage.SEVERITY_INFO);
            filterOriginal.copyFrom(filter);
        } catch (OptimisticLockException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            addMessage.setMessage("mainForm:orders", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            addMessage.setMessage("mainForm:orders", "error.exception", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Transactional
    private void saveData(){
        filter = em.merge(filter);
    }

}
