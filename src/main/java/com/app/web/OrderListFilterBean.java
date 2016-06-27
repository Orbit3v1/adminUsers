package com.app.web;

import com.app.dictionary.OrderItemState;
import com.app.entity.OrderListFilter;
import com.app.utils.AddMessage;
import com.app.utils.Security;
import com.app.utils.SessionUtil;
import org.apache.log4j.Logger;
import org.hibernate.StaleObjectStateException;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import java.util.Map;

@Named("orderListFilterBean")
@Scope("session")
public class OrderListFilterBean {

    @PersistenceContext
    protected EntityManager em;
    @Inject
    protected AddMessage addMessage;

    protected Logger logger = Logger.getLogger(getClass());

    private OrderListFilter filter;
    private Map<String, Boolean> userPA;

    @PostConstruct
    public void init() {
        userPA = Security.getUserPrivilegeAction("orderList");
        Integer userId = Security.getCurrentUser().getId();
        filter = em.find(OrderListFilter.class, userId);
        if (filter == null) {
            filter = createNew();
        }
    }

    private OrderListFilter createNew(){
        OrderListFilter filter = new OrderListFilter();
        filter.setId(Security.getCurrentUser().getId());
        filter.setState(getDefaultState());
        return filter;
    }

    public OrderItemState getDefaultState() {
        return Security.hasAccess(userPA, "accessInWork") ? OrderItemState.IN_WORK : OrderItemState.ALL;
    }

    public OrderListFilter clear() {
        filter.clear();
        return filter;
    }

    public OrderListFilter save() {
        try {
            saveData();
            addMessage.setMessage("mainForm:orders", "orderListFilter.saveSuccess", FacesMessage.SEVERITY_INFO);
        } catch (OptimisticLockException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            addMessage.setMessage("mainForm:orders", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            addMessage.setMessage("mainForm:orders", "error.exception", FacesMessage.SEVERITY_ERROR);
        }
        return filter;
    }

    public OrderListFilter load(){
        filter = em.find(OrderListFilter.class, Security.getCurrentUser().getId());
        if(filter == null){
            filter = createNew();
        }
        return filter;
    }

    @Transactional
    private void saveData(){
        filter = em.merge(filter);
    }

    public OrderListFilter getFilter() {
        return filter;
    }

    public void setFilter(OrderListFilter filter) {
        this.filter = filter;
    }
}
