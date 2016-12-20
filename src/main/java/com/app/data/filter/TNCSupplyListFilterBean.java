package com.app.data.filter;

import com.app.data.dictionary.TNCRequestSort;
import com.app.data.dictionary.TNCRequestState;
import com.app.data.dictionary.TNCSupplySort;
import com.app.data.entity.TNCRequestItem;
import com.app.data.entity.TNCSupply;
import com.app.data.entity.filter.TNCRequestListFilter;
import com.app.data.entity.filter.TNCSupplyListFilter;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.*;

import static com.app.utils.AppUtil.endDay;
import static com.app.utils.AppUtil.notEmpty;

@TNCSupplyCDI
@Named("tncSupplyListFilterBean")
@Scope("session")
public class TNCSupplyListFilterBean extends FilterBean implements ListFilterBean<TNCSupply> {
    @Override
    protected String getFilterName() {
        return "tncSupplyList";
    }
    @Override
    protected TNCSupplyListFilter createNew() {
        return new TNCSupplyListFilter();
    }

    public TNCSupplyListFilter getFilterOriginal() {
        return (TNCSupplyListFilter) filterOriginal;
    }

    public void setSort(TNCSupplySort sort){
        super.setSort(sort);
    }

    @Override
    public List<TNCSupply> getList() {
        TNCSupplyListFilter filterOriginal = getFilterOriginal();
        Map<String, Object> parameters = new HashMap<>();
        String sqlFrom = "select distinct r from TNCSupply r " +
                "left join fetch r.tncSupplyItems si " +
                "left join fetch si.tnc n ";

        StringJoiner sqlWhere = new StringJoiner(" AND ");
        sqlWhere.add("1 = 1");

        if (notEmpty(filterOriginal.getName())) {
            sqlWhere.add("r.name like :name");
            parameters.put("name", "%" + filterOriginal.getName() + "%");
        }
        if (notEmpty(filterOriginal.getTnc())) {
            sqlWhere.add("si.tnc.name like :tnc");
            parameters.put("tnc", "%" + filterOriginal.getTnc() + "%");
        }
        if (notEmpty(filterOriginal.getProvider())) {
            sqlWhere.add("r.provider like :provider");
            parameters.put("provider", "%" + filterOriginal.getProvider() + "%");
        }
        if (notEmpty(filterOriginal.getAccountNumber())) {
            sqlWhere.add("r.accountNumber like :accountNumber");
            parameters.put("accountNumber", "%" + filterOriginal.getAccountNumber() + "%");
        }
        if (filterOriginal.getStartL() != null) {
            sqlWhere.add("r.start >= :startL");
            parameters.put("startL", filterOriginal.getStartL());
        }
        if (filterOriginal.getStartH() != null) {
            sqlWhere.add("r.start <= :startH");
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
        if (filterOriginal.getPaymentDateL() != null) {
            sqlWhere.add("r.paymentDateL >= :paymentDateL");
            parameters.put("paymentDateL", filterOriginal.getPaymentDateL());
        }
        if (filterOriginal.getPaymentDateH() != null) {
            sqlWhere.add("r.paymentDateH <= :paymentDateH");
            parameters.put("paymentDateH", endDay(filterOriginal.getPaymentDateH()));
        }

        StringJoiner sqlOrder = new StringJoiner(", ");
        if(filterOriginal.getSort() != null){
            sqlOrder.add(filterOriginal.getSort().getSqlOrder());
        }
        if(!TNCSupplySort.NAME_ASC.equals(filterOriginal.getSort())
                && !TNCSupplySort.NAME_DESC.equals(filterOriginal.getSort())) {
            sqlOrder.add("r.name");        }

        String sqlFull = sqlFrom + " WHERE " + sqlWhere.toString() + " order by " + sqlOrder.toString();

        Query query = em.createQuery(sqlFull);
        for (Map.Entry<String, Object> e : parameters.entrySet()) {
            query.setParameter(e.getKey(), e.getValue());
        }

        List<TNCSupply> tncSupplies = query.getResultList();
        return tncSupplies;
    }

    public TNCSupplyListFilter getFilter() {
        return (TNCSupplyListFilter) filter;
    }
}
