package com.app.data.filter;

import com.app.data.dictionary.OrderItemState;
import com.app.data.dictionary.ProductionReportSort;
import com.app.data.entity.OrderItem;
import com.app.data.entity.filter.OrderListFilter;
import com.app.utils.Security;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.app.utils.AppUtil.endDay;
import static com.app.utils.AppUtil.notEmpty;

@OrderListCDI
@Named("orderListFilterBean")
@Scope("session")
public class OrderListFilterBean extends FilterBean implements ListFilterBean<OrderItem> {

    @Override
    protected String getFilterName() {
        return "orderList";
    }

    @Override
    protected OrderListFilter createNew() {
        return new OrderListFilter();
    }

    @Override
    protected void resetFilter(){
        super.resetFilter();
        getFilter().setState(getDefaultState());
    }

    private OrderItemState getDefaultState() {
        return Security.hasAccess(userPA, "accessInWork") ? OrderItemState.IN_WORK : OrderItemState.ALL;
    }

    public void setSort(ProductionReportSort sort){
        super.setSort(sort);
    }

    @Override
    public List<OrderItem> getList() {
        OrderListFilter filterOriginal = getFilterOriginal();
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


    public OrderListFilter getFilter() {
        return (OrderListFilter) filter;
    }

    public void setFilter(OrderListFilter filter) {
        this.filter = filter;
    }

    public OrderListFilter getFilterOriginal() {
        return (OrderListFilter) filterOriginal;
    }

}
