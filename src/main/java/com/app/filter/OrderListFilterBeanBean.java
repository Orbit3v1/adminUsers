package com.app.filter;

import com.app.dictionary.OrderItemState;
import com.app.dictionary.ProductionReportSort;
import com.app.entity.OrderItem;
import com.app.entity.OrderListFilter;
import com.app.utils.AddMessage;
import com.app.utils.Security;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.app.utils.AppUtil.endDay;
import static com.app.utils.AppUtil.notEmpty;

@Named("orderListFilterBean")
@Scope("session")
public class OrderListFilterBeanBean implements ListFilterBean<OrderItem> {

    @PersistenceContext
    protected EntityManager em;
    @Inject
    protected AddMessage addMessage;

    protected Logger logger = Logger.getLogger(getClass());

    private OrderListFilter filter;
    private OrderListFilter filterOriginal;
    private Map<String, Boolean> userPA;

    @PostConstruct
    public void init() {
        userPA = Security.getUserPrivilegeAction("orderList");
        Integer userId = Security.getCurrentUser().getId();
        filter = em.find(OrderListFilter.class, userId);
        if (filter == null) {
            filter = createNew();
        }
        filterOriginal = new OrderListFilter();
        filterOriginal.copyFrom(filter);
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

    public void clear() {
        logger.info("clearFilter");
        filter.clear();
        filterOriginal.copyFrom(filter);
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

    public void load(){
        logger.info("Load order list filter");
        filter = em.find(OrderListFilter.class, Security.getCurrentUser().getId());
        if(filter == null){
            filter = createNew();
        }
        filterOriginal.copyFrom(filter);
        addMessage.setMessage("mainForm:orders", "orderListFilter.loadSuccess", FacesMessage.SEVERITY_INFO);
    }

    public void setSort(ProductionReportSort sort){
        if(sort.equals(filter.getSort())){
            sort = sort.getReverse();
        }
        filter.setSort(sort);
        filterOriginal.setSort(sort);
    }

    public void find() {
        logger.info("find");
        filterOriginal.copyFrom(filter);
    }

    @Override
    public List<OrderItem> getList() {
        Map<String, Object> parameters = new HashMap<>();
        String sqlFrom = "select r from OrderItem r " +
                "left join fetch r.order " +
                "left join fetch r.nomenclature " +
                "left join fetch r.developer ";

        String sqlAccess = "";
        if (!Security.hasAccess(userPA, "accessInWork")) {
            sqlAccess += " AND r.endActual is not null";
        }
        if (!Security.hasAccess(userPA, "accessFinished")) {
            sqlAccess += " AND r.endActual is null";
        }

        String sqlWhere = sqlAccess;

        if (notEmpty(filterOriginal.getName())) {
            sqlWhere += " AND concat(r.order.name, '_', r.name) like :name";
            parameters.put("name", "%" + filterOriginal.getName() + "%");
        }
        if (notEmpty(filterOriginal.getCustomer())) {
            sqlWhere += " AND r.order.customer like :customer";
            parameters.put("customer", "%" + filterOriginal.getCustomer() + "%");
        }
        if (notEmpty(filterOriginal.getNomenclature())) {
            sqlWhere += " AND r.nomenclature.name like :nomenclature";
            parameters.put("nomenclature", "%" + filterOriginal.getNomenclature() + "%");
        }
        if (notEmpty(filterOriginal.getResponsible())) {
            sqlWhere += " AND concat(r.order.responsible.lastName, ' ', r.order.responsible.firstName) like :responsible";
            parameters.put("responsible", "%" + filterOriginal.getResponsible() + "%");
        }
        if (notEmpty(filterOriginal.getDeveloper())) {
            sqlWhere += " AND concat(r.developer.lastName, ' ', r.developer.firstName) like :developer";
            parameters.put("developer", "%" + filterOriginal.getDeveloper() + "%");
        }
        if (filterOriginal.getStartL() != null) {
            sqlWhere += " AND r.order.start >= :startL";
            parameters.put("startL", filterOriginal.getStartL());
        }
        if (filterOriginal.getStartH() != null) {
            sqlWhere += " AND r.order.start <= :startH";
            parameters.put("startH", endDay(filterOriginal.getStartH()));
        }
        if (filterOriginal.getDocDateL() != null) {
            sqlWhere += " AND r.docDate >= :docDateL";
            parameters.put("docDateL", filterOriginal.getDocDateL());
        }
        if (filterOriginal.getDocDateH() != null) {
            sqlWhere += " AND r.docDate <= :docDateH";
            parameters.put("docDateH", endDay(filterOriginal.getDocDateH()));
        }
        if (filterOriginal.getEndPlanL() != null) {
            sqlWhere += " AND r.endPlan >= :endPlanL";
            parameters.put("endPlanL", filterOriginal.getEndPlanL());
        }
        if (filterOriginal.getEndPlanH() != null) {
            sqlWhere += " AND r.endPlan <= :endPlanH";
            parameters.put("endPlanH", endDay(filterOriginal.getEndPlanH()));
        }
        if (filterOriginal.getEndActualL() != null) {
            sqlWhere += " AND r.endActual >= :endActualL";
            parameters.put("endActualL", filterOriginal.getEndActualL());
        }
        if (filterOriginal.getEndActualH() != null) {
            sqlWhere += " AND r.endActual <= :endActualH";
            parameters.put("endActualH", endDay(filterOriginal.getEndActualH()));
        }
        switch (filterOriginal.getState()) {
            case IN_WORK:
                sqlWhere += " AND r.endActual is null";
                break;
            case FINISHED:
                sqlWhere += " AND r.endActual is not null";
                break;
            default:
                break;
        }

        if (!sqlWhere.equals("")) {
            sqlWhere = "WHERE" + sqlWhere.substring(4);
        }

        String sqlOrder = "";
        if(filterOriginal.getSort() != null){
            sqlOrder += filterOriginal.getSort().getSqlOrder();
        }
        if(!ProductionReportSort.NAME_ASC.equals(filterOriginal.getSort())
                && !ProductionReportSort.NAME_DESC.equals(filterOriginal.getSort())) {
            sqlOrder += (sqlOrder.equals("") ? "" : ", ") + "r.order.name, cast(r.name as int)";
        }
        sqlOrder = " order by " + sqlOrder;

        String sqlFull = sqlFrom + sqlWhere + sqlOrder;

        Query query = em.createQuery(sqlFull);
        for (Map.Entry<String, Object> e : parameters.entrySet()) {
            query.setParameter(e.getKey(), e.getValue());
        }

        List<OrderItem> orderItems = query.getResultList();
        return orderItems;
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

    public OrderListFilter getFilterOriginal() {
        return filterOriginal;
    }

    public void setFilterOriginal(OrderListFilter filterOriginal) {
        this.filterOriginal = filterOriginal;
    }
}
