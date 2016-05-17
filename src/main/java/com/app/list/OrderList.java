package com.app.list;

import com.app.entity.Order;
import com.app.entity.Role;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import com.app.utils.Security;
import com.app.utils.SessionUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Named("orderList")
@Scope("request")
public class OrderList {
    @PersistenceContext
    protected EntityManager em;

    private List<Order> orders;
    private Map<String, Boolean> userPA;

    private String idFilter;

    @PostConstruct
    public void init(){
        Query query = em.createQuery("select r from Order r order by r.id");
        orders = query.getResultList();
        userPA = Security.getUserPrivilegeAction("orderList");
    }

    public void setEndActual(Order order){
        Date date = new Date();
        order.setEndActual(date);
        try {
            order = saveData(order);

        } catch (OptimisticLockException e) {
            e.printStackTrace();
            order.setEndActual(null);
            SessionUtil.setMessage("mainForm:orders", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            order.setEndActual(null);
            SessionUtil.setMessage("mainForm:orders", "error.exception", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Transactional
    private Order saveData(Order order){
        return em.merge(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }

    public String getIdFilter() {
        return idFilter;
    }

    public void setIdFilter(String idFilter) {
        this.idFilter = idFilter;
    }
}


