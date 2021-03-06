package com.app.data.filter;

import com.app.data.dictionary.SpecificationSort;
import com.app.data.entity.Specification;
import com.app.data.entity.filter.SpecificationListFilter;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.app.utils.AppUtil.endDay;
import static com.app.utils.AppUtil.notEmpty;

@SpecificationCDI
@Named("specificationListFilterBean")
@Scope("session")
public class SpecificationListFilterBean extends FilterBean implements ListFilterBean<Specification>  {
    @Override
    protected String getFilterName() {
        return "orderList";
    }

    @Override
    protected SpecificationListFilter createNew() {
        return new SpecificationListFilter();
    }

    public void setSort(SpecificationSort sort){
        super.setSort(sort);
    }

    @Override
    public List<Specification> getList() {
        SpecificationListFilter filterOriginal = getFilterOriginal();
        Map<String, Object> parameters = new HashMap<>();
        String sqlFrom = "select distinct r from Specification r " +
                "left join fetch r.nomenclature n " +
                "left join fetch r.developer " +
                "left join fetch r.approvedBy " +
                "left join fetch r.responsible " +
                "left join fetch n.orderItems oi " +
                "left join fetch oi.order "
                ;

        String sqlWhere = "";


        if (notEmpty(filterOriginal.getName())) {
            sqlWhere += " AND concat(r.name, '-', cast(r.subName as string)) like :name";
            parameters.put("name", "%" + filterOriginal.getName() + "%");
        }
        if (notEmpty(filterOriginal.getType())) {
            sqlWhere += " AND r.type like :type";
            parameters.put("type", "%" + filterOriginal.getType() + "%");
        }
        if (notEmpty(filterOriginal.getNomenclature())) {
            sqlWhere += " AND r.nomenclature.name like :nomenclature";
            parameters.put("nomenclature", "%" + filterOriginal.getNomenclature() + "%");
        }
        if (notEmpty(filterOriginal.getPrice())) {
            sqlWhere += " AND r.price like :price";
            parameters.put("price", "%" + filterOriginal.getPrice() + "%");
        }
        if (notEmpty(filterOriginal.getDiscount())) {
            sqlWhere += " AND r.discount = :discount";
            parameters.put("discount", filterOriginal.getDiscount());
        }
        if (notEmpty(filterOriginal.getResponsible())) {
            sqlWhere += " AND concat(r.responsible.lastName, ' ', r.responsible.firstName) like :responsible";
            parameters.put("responsible", "%" + filterOriginal.getResponsible() + "%");
        }
        if (notEmpty(filterOriginal.getDeveloper())) {
            sqlWhere += " AND concat(r.developer.lastName, ' ', r.developer.firstName) like :developer";
            parameters.put("developer", "%" + filterOriginal.getDeveloper() + "%");
        }
        if (filterOriginal.getStartL() != null) {
            sqlWhere += " AND r.start >= :startL";
            parameters.put("startL", filterOriginal.getStartL());
        }
        if (filterOriginal.getStartH() != null) {
            sqlWhere += " AND r.start <= :startH";
            parameters.put("startH", endDay(filterOriginal.getStartH()));
        }
        if (filterOriginal.getResponseDateL() != null) {
            sqlWhere += " AND r.responseDate >= :responseDateL";
            parameters.put("responseDateL", filterOriginal.getResponseDateL());
        }
        if (filterOriginal.getResponseDateH() != null) {
            sqlWhere += " AND r.responseDate <= :responseDateH";
            parameters.put("responseDateH", endDay(filterOriginal.getResponseDateH()));
        }

        if (!sqlWhere.equals("")) {
            sqlWhere = "WHERE" + sqlWhere.substring(4);
        }

        String sqlOrder = "";
        if(filterOriginal.getSort() != null){
            sqlOrder += filterOriginal.getSort().getSqlOrder();
        }
        if(!SpecificationSort.NAME_ASC.equals(filterOriginal.getSort())
                && !SpecificationSort.NAME_DESC.equals(filterOriginal.getSort())) {
            sqlOrder += (sqlOrder.equals("") ? "" : ", ") + "r.name, r.subName";
        }
        sqlOrder = " order by " + sqlOrder;

        String sqlFull = sqlFrom + sqlWhere + sqlOrder;

        Query query = em.createQuery(sqlFull);
        for (Map.Entry<String, Object> e : parameters.entrySet()) {
            query.setParameter(e.getKey(), e.getValue());
        }

        List<Specification> specifications = query.getResultList();
        return specifications;
    }

    public SpecificationListFilter getFilter() {
        return (SpecificationListFilter) filter;
    }

    public void setFilter(SpecificationListFilter filter) {
        this.filter = filter;
    }

    public SpecificationListFilter getFilterOriginal() {
        return (SpecificationListFilter) filterOriginal;
    }
}
