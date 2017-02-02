package com.app.data.filter;

import javax.inject.Named;
import javax.persistence.Query;

import com.app.data.dictionary.CarRequestSort;
import com.app.data.dictionary.TNCSupplySort;
import com.app.data.entity.CarRequest;
import com.app.data.entity.TNCSupply;
import com.app.data.entity.filter.CarRequestListFilter;
import com.app.data.entity.filter.TNCSupplyListFilter;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static com.app.utils.AppUtil.endDay;
import static com.app.utils.AppUtil.notEmpty;

@Named("carRequestListFilterBean")
@Scope("session")
public class CarRequestListFilterBean extends FilterBean implements ListFilterBean<CarRequest> {
    @Override
    protected String getFilterName() {
        return "carRequestList";
    }
    @Override
    protected CarRequestListFilter createNew() {
        return new CarRequestListFilter();
    }

    public CarRequestListFilter getFilterOriginal() {
        return (CarRequestListFilter) filterOriginal;
    }

    public void setSort(CarRequestSort sort){
        super.setSort(sort);
    }

    @Override
    public List<CarRequest> getList() {
        CarRequestListFilter filterOriginal = getFilterOriginal();
        Map<String, Object> parameters = new HashMap<>();
        String sqlFrom = "select distinct r from CarRequest r " +
                "left join fetch r.responsible resp " +
                "left join fetch r.creator cr " +
                "left join fetch r.attachments a ";

        StringJoiner sqlWhere = new StringJoiner(" AND ");
        sqlWhere.add("1 = 1");

        if (notEmpty(filterOriginal.getName())) {
            sqlWhere.add("cast(r.id as string) like :name");
            parameters.put("name", "%" + filterOriginal.getName() + "%");
        }
        if (notEmpty(filterOriginal.getResponsible())) {
            sqlWhere.add("concat(r.responsible.lastName, ' ', r.responsible.firstName) like :responsible");
            parameters.put("responsible", "%" + filterOriginal.getResponsible() + "%");
        }
        if (notEmpty(filterOriginal.getCreator())) {
            sqlWhere.add("concat(r.creator.lastName, ' ', r.creator.firstName) like :creator");
            parameters.put("creator", "%" + filterOriginal.getCreator() + "%");
        }
        if (filterOriginal.getStartL() != null) {
            sqlWhere.add("r.start >= :startL");
            parameters.put("startL", filterOriginal.getStartL());
        }
        if (filterOriginal.getStartH() != null) {
            sqlWhere.add("r.start <= :startH");
            parameters.put("startH", endDay(filterOriginal.getStartH()));
        }
        if (filterOriginal.getEndActualL() != null) {
            sqlWhere.add("r.endActual >= :endActualL");
            parameters.put("endActualL", filterOriginal.getEndActualL());
        }
        if (filterOriginal.getEndActualH() != null) {
            sqlWhere.add("r.endActual <= :endActualH");
            parameters.put("endActualH", endDay(filterOriginal.getEndActualH()));
        }
        if (notEmpty(filterOriginal.getAddressFrom())) {
            sqlWhere.add("r.addressFrom like :addressFrom");
            parameters.put("addressFrom", "%" + filterOriginal.getAddressFrom() + "%");
        }
        if (notEmpty(filterOriginal.getAddressTo())) {
            sqlWhere.add("r.addressTo like :addressTo");
            parameters.put("addressTo", "%" + filterOriginal.getAddressTo() + "%");
        }
        if (notEmpty(filterOriginal.getReceiverName())) {
            sqlWhere.add("r.receiverName like :receiverName");
            parameters.put("receiverName", "%" + filterOriginal.getReceiverName() + "%");
        }
        if (notEmpty(filterOriginal.getReceiverPhone())) {
            sqlWhere.add("r.receiverPhone like :receiverPhone");
            parameters.put("receiverPhone", "%" + filterOriginal.getReceiverPhone() + "%");
        }
        if (notEmpty(filterOriginal.getPayment())) {
            sqlWhere.add("r.payment like :payment");
            parameters.put("payment", "%" + filterOriginal.getPayment() + "%");
        }
        if (notEmpty(filterOriginal.getDescription())) {
            sqlWhere.add("r.description like :description");
            parameters.put("description", "%" + filterOriginal.getDescription() + "%");
        }
        if (notEmpty(filterOriginal.getPriority())) {
            sqlWhere.add("r.priority = :priority");
            parameters.put("priority", filterOriginal.getPriority());
        }

        StringJoiner sqlOrder = new StringJoiner(", ");
        if(filterOriginal.getSort() != null){
            sqlOrder.add(filterOriginal.getSort().getSqlOrder());
        }
        if(!CarRequestSort.ID_ASC.equals(filterOriginal.getSort())
                && !CarRequestSort.ID_DESC.equals(filterOriginal.getSort())) {
            sqlOrder.add(CarRequestSort.ID_ASC.getSqlOrder());        }

        String sqlFull = sqlFrom + " WHERE " + sqlWhere.toString() + " order by " + sqlOrder.toString();

        Query query = em.createQuery(sqlFull);
        for (Map.Entry<String, Object> e : parameters.entrySet()) {
            query.setParameter(e.getKey(), e.getValue());
        }

        List<CarRequest> entities = query.getResultList();
        return entities;
    }

    public CarRequestListFilter getFilter() {
        return (CarRequestListFilter) filter;
    }
}
