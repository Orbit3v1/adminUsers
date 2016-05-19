package com.app.list;

import com.app.entity.Order;
import com.app.utils.AppUtil;
import org.richfaces.model.Filter;
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

    private String flName;
    private String flCustomer;
    private String flNomenclature;
    private String flResponsible;
    private String flDeveloper;
    private Date flStartL;
    private Date flStartH;
    private Date flDocDateL;
    private Date flDocDateH;
    private Date flEndPlanL;
    private Date flEndPlanH;
    private Date flEndActualL;
    private Date flEndActualH;

    @PostConstruct
    public void init() {
        Query query = em.createQuery("select r from Order r order by r.id");
        orders = query.getResultList();
        userPA = Security.getUserPrivilegeAction("orderList");
    }

    public void setEndActual(Order order) {
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
    private Order saveData(Order order) {
        return em.merge(order);
    }

    public Filter<?> getStartFilterImpl() {
        return new Filter<Order>() {
            public boolean accept(Order item) {
                return AppUtil.isInRange(item.getStart(), flStartL, flStartH);
            }
        };
    }

    public Filter<?> getDocDateFilterImpl() {
        return new Filter<Order>() {
            public boolean accept(Order item) {
                return AppUtil.isInRange(item.getDocDate(), flDocDateL, flDocDateH);
            }
        };
    }

    public Filter<?> getEndPlanFilterImpl() {
        return new Filter<Order>() {
            public boolean accept(Order item) {
                return AppUtil.isInRange(item.getEndPlan(), flEndPlanL, flEndPlanH);
            }
        };
    }

    public Filter<?> getEndActualFilterImpl() {
        return new Filter<Order>() {
            public boolean accept(Order item) {
                return AppUtil.isInRange(item.getEndActual(), flEndActualL, flEndActualH);
            }
        };
    }

    public void clearFilter() {
        flName = null;
        flCustomer = null;
        flNomenclature = null;
        flResponsible = null;
        flDeveloper = null;
        flStartL = null;
        flStartH = null;
        flDocDateL = null;
        flDocDateH = null;
        flEndPlanL = null;
        flEndPlanH = null;
        flEndActualL = null;
        flEndActualH = null;
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

    public String getFlName() {
        return flName;
    }

    public void setFlName(String flName) {
        this.flName = flName;
    }

    public String getFlCustomer() {
        return flCustomer;
    }

    public void setFlCustomer(String flCustomer) {
        this.flCustomer = flCustomer;
    }

    public String getFlNomenclature() {
        return flNomenclature;
    }

    public void setFlNomenclature(String flNomenclature) {
        this.flNomenclature = flNomenclature;
    }

    public String getFlResponsible() {
        return flResponsible;
    }

    public void setFlResponsible(String flResponsible) {
        this.flResponsible = flResponsible;
    }

    public String getFlDeveloper() {
        return flDeveloper;
    }

    public void setFlDeveloper(String flDeveloper) {
        this.flDeveloper = flDeveloper;
    }

    public Date getFlStartL() {
        return flStartL;
    }

    public void setFlStartL(Date flStartL) {
        this.flStartL = flStartL;
    }

    public Date getFlStartH() {
        return flStartH;
    }

    public void setFlStartH(Date flStartH) {
        this.flStartH = flStartH;
    }

    public Date getFlDocDateL() {
        return flDocDateL;
    }

    public void setFlDocDateL(Date flDocDateL) {
        this.flDocDateL = flDocDateL;
    }

    public Date getFlDocDateH() {
        return flDocDateH;
    }

    public void setFlDocDateH(Date flDocDateH) {
        this.flDocDateH = flDocDateH;
    }

    public Date getFlEndPlanL() {
        return flEndPlanL;
    }

    public void setFlEndPlanL(Date flEndPlanL) {
        this.flEndPlanL = flEndPlanL;
    }

    public Date getFlEndPlanH() {
        return flEndPlanH;
    }

    public void setFlEndPlanH(Date flEndPlanH) {
        this.flEndPlanH = flEndPlanH;
    }

    public Date getFlEndActualL() {
        return flEndActualL;
    }

    public void setFlEndActualL(Date flEndActualL) {
        this.flEndActualL = flEndActualL;
    }

    public Date getFlEndActualH() {
        return flEndActualH;
    }

    public void setFlEndActualH(Date flEndActualH) {
        this.flEndActualH = flEndActualH;
    }
}


