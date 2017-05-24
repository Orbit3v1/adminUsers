package com.app.data.filter;

import com.app.data.dictionary.CarRequestSort;
import com.app.data.dictionary.CarRequestState;
import com.app.data.dictionary.ServiceRequestSort;
import com.app.data.dictionary.ServiceRequestState;
import com.app.data.entity.CarRequest;
import com.app.data.entity.ServiceRequest;
import com.app.data.entity.filter.CarRequestListFilter;
import com.app.data.entity.filter.ServiceRequestListFilter;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static com.app.utils.AppUtil.endDay;
import static com.app.utils.AppUtil.notEmpty;

@Named("serviceRequestListFilterBean")
@Scope("session")
public class ServiceRequestListFilterBean  extends FilterBean implements ListFilterBean<ServiceRequest>{
    @Override
    protected String getFilterName() {
        return "serviceRequestList";
    }
    @Override
    protected ServiceRequestListFilter createNew() {
        return new ServiceRequestListFilter();
    }
    @Override
    protected void resetFilter(){
        super.resetFilter();
        getFilter().setState(ServiceRequestState.ALL);
    }

    public ServiceRequestListFilter getFilterOriginal() {
        return (ServiceRequestListFilter) filterOriginal;
    }

    public void setSort( ServiceRequestSort sort){
        super.setSort(sort);
    }
    @Override
    public List<ServiceRequest> getList() {
        ServiceRequestListFilter filterOriginal = getFilterOriginal();
        Map<String, Object> parameters = new HashMap<>();
        String sqlFrom = "select distinct r from ServiceRequest r " +
                "left join fetch r.responsible resp " +
                "left join fetch r.creator cr ";

        StringJoiner sqlWhere = new StringJoiner(" AND ");
        sqlWhere.add("1 = 1");

        if (notEmpty(filterOriginal.getName())) {
            sqlWhere.add("r.name like :name");
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
        if (notEmpty(filterOriginal.getWarrantyNumber())) {
            sqlWhere.add("r.warrantyNumber like :warrantyNumber");
            parameters.put("warrantyNumber", "%" + filterOriginal.getWarrantyNumber() + "%");
        }
        if (notEmpty(filterOriginal.getCounterparty())) {
            sqlWhere.add("r.counterparty like :counterparty");
            parameters.put("counterparty", "%" + filterOriginal.getCounterparty() + "%");
        }
        if (notEmpty(filterOriginal.getContact())) {
            sqlWhere.add("concat(r.contactName, ' ', r.contactPhone) like :contact");
            parameters.put("contact", "%" + filterOriginal.getContact() + "%");
        }
        if (notEmpty(filterOriginal.getPayment())) {
            sqlWhere.add("r.payment like :payment");
            parameters.put("payment", "%" + filterOriginal.getPayment() + "%");
        }
        if (notEmpty(filterOriginal.getDescription())) {
            sqlWhere.add("r.description like :description");
            parameters.put("description", "%" + filterOriginal.getDescription() + "%");
        }
        if (notEmpty(filterOriginal.getAddress())) {
            sqlWhere.add("r.address like :address");
            parameters.put("address", "%" + filterOriginal.getAddress() + "%");
        }
        if (notEmpty(filterOriginal.getResult())) {
            sqlWhere.add("r.result like :result");
            parameters.put("result", "%" + filterOriginal.getResult() + "%");
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
        if(!ServiceRequestSort.ID_ASC.equals(filterOriginal.getSort())
                && !ServiceRequestSort.ID_DESC.equals(filterOriginal.getSort())) {
            sqlOrder.add(ServiceRequestSort.ID_ASC.getSqlOrder());        }

        String sqlFull = sqlFrom + " WHERE " + sqlWhere.toString() + " order by " + sqlOrder.toString();

        Query query = em.createQuery(sqlFull);
        for (Map.Entry<String, Object> e : parameters.entrySet()) {
            query.setParameter(e.getKey(), e.getValue());
        }

        List<ServiceRequest> entities = query.getResultList();
        return entities;
    }

    public ServiceRequestListFilter getFilter() {
        return (ServiceRequestListFilter) filter;
    }
}
