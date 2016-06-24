package com.app.list;

import com.app.dictionary.OrderItemState;
import com.app.entity.Order;
import com.app.entity.OrderItem;
import com.app.entity.OrderListFilter;
import com.app.utils.AddMessage;
import com.app.utils.AppUtil;
import com.app.web.OrderListFilterBean;
import org.apache.log4j.Logger;
import org.richfaces.model.Filter;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import com.app.utils.Security;
import com.app.utils.SessionUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.app.utils.AppUtil.notEmpty;
import static com.app.utils.AppUtil.endDay;

@Named("orderList")
@Scope("request")
public class OrderList {
    @PersistenceContext
    protected EntityManager em;
    protected Logger logger = Logger.getLogger(getClass());

    @Inject
    OrderListFilterBean orderListFilterBean;
    @Inject
    protected AddMessage addMessage;

    private List<OrderItem> orderItems;
    private Map<String, Boolean> userPA;

    private OrderListFilter filter;
    private int gibTotal;

    @PostConstruct
    public void init() {
        logger.info("init");
        userPA = Security.getUserPrivilegeAction("orderList");
        filter = orderListFilterBean.getFilter();
        initList();
    }

    private void initList(){
        logger.info("initList");
        Map<String, Object> parameters = new HashMap<>();
        String sqlFrom = "select r from OrderItem r ";

        String sqlAccess = "";
        if(!Security.hasAccess(userPA, "accessInWork")){
            sqlAccess += " AND r.endActual is not null";
        }
        if(!Security.hasAccess(userPA, "accessFinished")){
            sqlAccess += " AND r.endActual is null";
        }

        String sqlWhere = sqlAccess;

        if(notEmpty(filter.getName())){
            sqlWhere += " AND concat(r.order.name, '_', r.name) like :name";
            parameters.put("name", filter.getName() + "%");
        }
        if(notEmpty(filter.getCustomer())){
            sqlWhere += " AND r.order.customer like :customer";
            parameters.put("customer", filter.getCustomer() + "%");
        }
        if(notEmpty(filter.getNomenclature())){
            sqlWhere += " AND r.nomenclature.name like :nomenclature";
            parameters.put("nomenclature", "%" + filter.getNomenclature() + "%");
        }
        if(notEmpty(filter.getResponsible())){
            sqlWhere += " AND concat(r.order.responsible.lastName, ' ', r.order.responsible.firstName) like :responsible";
            parameters.put("responsible", "%" + filter.getResponsible() + "%");
        }
        if(notEmpty(filter.getDeveloper())){
            sqlWhere += " AND concat(r.developer.lastName, ' ', r.developer.firstName) like :developer";
            parameters.put("developer", "%" + filter.getDeveloper() + "%");
        }
        if(filter.getStartL() != null){
            sqlWhere += " AND r.order.start >= :startL";
            parameters.put("startL", filter.getStartL());
        }
        if(filter.getStartH() != null){
            sqlWhere += " AND r.order.start <= :startH";
            parameters.put("startH", endDay(filter.getStartH()));
        }
        if(filter.getDocDateL() != null){
            sqlWhere += " AND r.docDate >= :docDateL";
            parameters.put("docDateL", filter.getDocDateL());
        }
        if(filter.getDocDateH() != null){
            sqlWhere += " AND r.docDate >= :docDateH";
            parameters.put("docDateH", endDay(filter.getDocDateH()));
        }
        if(filter.getEndPlanL() != null){
            sqlWhere += " AND r.endPlan >= :endPlanL";
            parameters.put("endPlanL", filter.getEndPlanL());
        }
        if(filter.getEndPlanH() != null){
            sqlWhere += " AND r.endPlan >= :endPlanH";
            parameters.put("endPlanH", endDay(filter.getEndPlanH()));
        }
        if(filter.getEndActualL() != null){
            sqlWhere += " AND r.endActual >= :endActualL";
            parameters.put("endActualL", filter.getEndActualL());
        }
        if(filter.getEndActualH() != null){
            sqlWhere += " AND r.endActual >= :endActualH";
            parameters.put("endActualH", endDay(filter.getEndActualH()));
        }
        switch (filter.getState()){
            case IN_WORK:
                sqlWhere += " AND r.endActual is null";
                break;
            case FINISHED:
                sqlWhere += " AND r.endActual is not null";
                break;
            default:
                break;
        }

        if(!sqlWhere.equals("")){
            sqlWhere = "WHERE" + sqlWhere.substring(4);
        }

        String sqlOrder = " order by r.order.name, r.name";
        String sqlFull = sqlFrom + sqlWhere + sqlOrder;

        Query query = em.createQuery(sqlFull);
        for(Map.Entry<String, Object> e : parameters.entrySet()){
            query.setParameter(e.getKey(), e.getValue());
        }
        orderItems = query.getResultList();
        gibTotal = orderItems.stream().filter(v -> v.getNomenclature().getGib() != null).mapToInt(v -> v.getNomenclature().getGib() * v.getCount()).sum();
    }

    public void setEndActual(OrderItem orderItem) {
        logger.info("set actual end. OrderItem.id = " + orderItem.getId());
        Date date = new Date();
        orderItem.setEndActual(date);
        try {
            orderItem = saveData(orderItem);

        } catch (OptimisticLockException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            orderItem.setEndActual(null);
            addMessage.setMessage("mainForm:orders", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            orderItem.setEndActual(null);
            addMessage.setMessage("mainForm:orders", "error.exception", FacesMessage.SEVERITY_ERROR);
        }
    }


    @Transactional
    private OrderItem saveData(OrderItem orderItem) {
        return em.merge(orderItem);
    }

    public void doFilter(){
        logger.info("doFilter");
        initList();
    }

    public void clearFilter() {
        logger.info("clearFilter");
        filter = orderListFilterBean.clear();
        initList();
    }

    public void loadFilter(){
        logger.info("Load order list filter");
        filter = orderListFilterBean.load();
        initList();
        addMessage.setMessage("mainForm:orders", "orderListFilter.loadSuccess", FacesMessage.SEVERITY_INFO);
    }

    public void saveFilter(){
        logger.info("Save order list filter");
        filter = orderListFilterBean.save();
        initList();
    }

    public void refresh(){
        initList();
    }


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }

    public OrderListFilter getFilter() {
        return filter;
    }

    public void setFilter(OrderListFilter filter) {
        this.filter = filter;
    }

    public int getGibTotal(){
        return gibTotal;
    }

    public List<OrderItemState> getFilterStates() {
        return Arrays.asList(OrderItemState.values()).stream().filter(v -> v.getPA() == null || Security.hasAccess(userPA, v.getPA())).collect(Collectors.toList());
    }
}


