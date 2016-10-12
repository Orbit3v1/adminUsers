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
import java.util.StringJoiner;

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

        StringJoiner sqlWhere = new StringJoiner(" AND ");
        if (!Security.hasAccess(userPA, "accessInWork")) {
            sqlWhere.add("r.endActual is not null");
        }
        if (!Security.hasAccess(userPA, "accessFinished")) {
            sqlWhere.add("r.endActual is null");
        }

        if (notEmpty(filterOriginal.getName())) {
            sqlWhere.add("concat(r.order.name, '_', r.name) like :name");
            parameters.put("name", "%" + filterOriginal.getName() + "%");
        }
        if (notEmpty(filterOriginal.getCustomer())) {
            sqlWhere.add("r.order.customer like :customer");
            parameters.put("customer", "%" + filterOriginal.getCustomer() + "%");
        }
        if (notEmpty(filterOriginal.getNomenclature())) {
            sqlWhere.add("r.nomenclature.name like :nomenclature");
            parameters.put("nomenclature", "%" + filterOriginal.getNomenclature() + "%");
        }
        if (notEmpty(filterOriginal.getResponsible())) {
            sqlWhere.add("concat(r.order.responsible.lastName, ' ', r.order.responsible.firstName) like :responsible");
            parameters.put("responsible", "%" + filterOriginal.getResponsible() + "%");
        }
        if (notEmpty(filterOriginal.getDeveloper())) {
            sqlWhere.add("concat(r.developer.lastName, ' ', r.developer.firstName) like :developer");
            parameters.put("developer", "%" + filterOriginal.getDeveloper() + "%");
        }
        if (filterOriginal.getStartL() != null) {
            sqlWhere.add("r.order.start >= :startL");
            parameters.put("startL", filterOriginal.getStartL());
        }
        if (filterOriginal.getStartH() != null) {
            sqlWhere.add("r.order.start <= :startH");
            parameters.put("startH", endDay(filterOriginal.getStartH()));
        }
        if (filterOriginal.getDocDateL() != null) {
            sqlWhere.add("r.docDate >= :docDateL");
            parameters.put("docDateL", filterOriginal.getDocDateL());
        }
        if (filterOriginal.getDocDateH() != null) {
            sqlWhere.add("r.docDate <= :docDateH");
            parameters.put("docDateH", endDay(filterOriginal.getDocDateH()));
        }
        if (filterOriginal.getEndPlanL() != null) {
            sqlWhere.add("r.endPlan >= :endPlanL");
            parameters.put("endPlanL", filterOriginal.getEndPlanL());
        }
        if (filterOriginal.getEndPlanH() != null) {
            sqlWhere.add("r.endPlan <= :endPlanH");
            parameters.put("endPlanH", endDay(filterOriginal.getEndPlanH()));
        }
        if (filterOriginal.getEndActualL() != null) {
            sqlWhere.add("r.endActual >= :endActualL");
            parameters.put("endActualL", filterOriginal.getEndActualL());
        }
        if (filterOriginal.getEndActualH() != null) {
            sqlWhere.add("r.endActual <= :endActualH");
            parameters.put("endActualH", endDay(filterOriginal.getEndActualH()));
        }
        switch (filterOriginal.getState()) {
            case IN_WORK:
                sqlWhere.add("r.endActual is null");
                break;
            case FINISHED:
                sqlWhere.add("r.endActual is not null");
                break;
            default:
                break;
        }

        StringJoiner sqlOrder = new StringJoiner(", ");
        if(filterOriginal.getSort() != null){
            sqlOrder.add(filterOriginal.getSort().getSqlOrder());
        }
        if(!ProductionReportSort.NAME_ASC.equals(filterOriginal.getSort())
                && !ProductionReportSort.NAME_DESC.equals(filterOriginal.getSort())) {
            sqlOrder.add("r.order.name");
            sqlOrder.add("cast(r.name as int)");
        }

        String sqlFull = sqlFrom + " WHERE " + sqlWhere.toString() + " order by " + sqlOrder.toString();

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
