package com.app.data.filter;

import com.app.data.dictionary.*;
import com.app.data.entity.OrderItem;
import com.app.data.entity.TNCRequestItem;
import com.app.data.entity.filter.OrderListFilter;
import com.app.data.entity.filter.TNCRequestListFilter;
import com.app.security.Security;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

import static com.app.utils.AppUtil.endDay;
import static com.app.utils.AppUtil.notEmpty;

@TNCRequestCDI
@Named("tncRequestListFilterBean")
@Scope("session")
public class TNCRequestListFilterBean extends FilterBean implements ListFilterBean<TNCRequestItem> {
    @Override
    protected String getFilterName() {
        return "tncRequestList";
    }

    @Override
    protected TNCRequestListFilter createNew() {
        return new TNCRequestListFilter();
    }

    public TNCRequestListFilter getFilterOriginal() {
        return (TNCRequestListFilter) filterOriginal;
    }

    public void setSort(TNCRequestSort sort){
        super.setSort(sort);
    }

    @Override
    public List<TNCRequestItem> getList() {
        TNCRequestListFilter filterOriginal = getFilterOriginal();
        Map<String, Object> parameters = new HashMap<>();
        String sqlFrom = "select r from TNCRequestItem r " +
                "left join fetch r.tncRequest o " +
                "left join fetch r.tnc n " +
                "left join fetch o.responsible resp " +
                "left join fetch o.creator cr ";

        StringJoiner sqlWhere = new StringJoiner(" AND ");
        sqlWhere.add("1 = 1");

        if (notEmpty(filterOriginal.getName())) {
            sqlWhere.add("concat(r.tncRequest.name, '_', r.name) like :name");
            parameters.put("name", "%" + filterOriginal.getName() + "%");
        }
        if (notEmpty(filterOriginal.getTnc())) {
            sqlWhere.add("r.tnc.name like :tnc");
            parameters.put("tnc", "%" + filterOriginal.getTnc() + "%");
        }
        if (notEmpty(filterOriginal.getResponsible())) {
            sqlWhere.add("concat(r.tncRequest.responsible.lastName, ' ', r.tncRequest.responsible.firstName) like :responsible");
            parameters.put("responsible", "%" + filterOriginal.getResponsible() + "%");
        }
        if (notEmpty(filterOriginal.getCreator())) {
            sqlWhere.add("concat(r.tncRequest.creator.lastName, ' ', r.tncRequest.creator.firstName) like :developer");
            parameters.put("developer", "%" + filterOriginal.getCreator() + "%");
        }
        if (filterOriginal.getStartL() != null) {
            sqlWhere.add("r.tncRequest.start >= :startL");
            parameters.put("startL", filterOriginal.getStartL());
        }
        if (filterOriginal.getStartH() != null) {
            sqlWhere.add("r.tncRequest.start <= :startH");
            parameters.put("startH", endDay(filterOriginal.getStartH()));
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
        if (filterOriginal.getState() != null) {
            sqlWhere.add("r.state = :state");
            parameters.put("state", filterOriginal.getState());
        }


        StringJoiner sqlOrder = new StringJoiner(", ");
        if(filterOriginal.getSort() != null){
            sqlOrder.add(filterOriginal.getSort().getSqlOrder());
        }
        if(!TNCRequestSort.NAME_ASC.equals(filterOriginal.getSort())
                && !TNCRequestSort.NAME_DESC.equals(filterOriginal.getSort())) {
            sqlOrder.add("r.tncRequest.name");
            sqlOrder.add("r.name)");
        }

        String sqlFull = sqlFrom + " WHERE " + sqlWhere.toString() + " order by " + sqlOrder.toString();

        Query query = em.createQuery(sqlFull);
        for (Map.Entry<String, Object> e : parameters.entrySet()) {
            query.setParameter(e.getKey(), e.getValue());
        }

        List<TNCRequestItem> tncRequestItems = query.getResultList();
        return tncRequestItems;
    }

    public TNCRequestListFilter getFilter() {
        return (TNCRequestListFilter) filter;
    }

    public List<TNCRequestState> getFilterStates() {
        return Arrays.asList(TNCRequestState.values());
    }
}
