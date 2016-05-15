package list;

import entity.Order;
import entity.Role;
import org.springframework.context.annotation.Scope;
import utils.Security;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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


