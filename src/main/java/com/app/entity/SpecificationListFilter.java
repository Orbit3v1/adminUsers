package com.app.entity;

import com.app.dictionary.OrderItemState;
import com.app.dictionary.ProductionReportSort;
import com.app.dictionary.Sort;
import com.app.dictionary.SpecificationSort;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "specificationListFilter")
public class SpecificationListFilter extends AbstractVersionedEntity implements Filter<SpecificationListFilter> {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "nomenclature")
    private String nomenclature;

    @Column(name = "price")
    private String price;

    @Column(name = "discount")
    private String discount;

    @Column(name = "responsible")
    private String responsible;

    @Column(name = "developer")
    private String developer;

    @Column(name = "startL")
    private Date startL;

    @Column(name = "startH")
    private Date startH;

    @Column(name = "responseDateL")
    private Date responseDateL;

    @Column(name = "responseDateH")
    private Date responseDateH;

    @Column(name = "sort")
    @Enumerated(EnumType.STRING)
    private SpecificationSort sort;

    public SpecificationListFilter() {
    }

    public void clear() {
        name = null;
        type = null;
        nomenclature = null;
        responsible = null;
        developer = null;
        price = null;
        discount = null;
        startL = null;
        startH = null;
        responseDateL = null;
        responseDateH = null;
        sort = null;
    }

    public void copyFrom(SpecificationListFilter filter){

        name = filter.name;
        type = filter.type;
        nomenclature = filter.nomenclature;
        responsible = filter.responsible;
        developer = filter.developer;
        price = filter.price;
        discount = filter.discount;
        startL = filter.startL;
        startH = filter.startH;
        responseDateL = filter.responseDateL;
        responseDateH = filter.responseDateH;
        sort = filter.sort;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Date getStartL() {
        return startL;
    }

    public void setStartL(Date startL) {
        this.startL = startL;
    }

    public Date getStartH() {
        return startH;
    }

    public void setStartH(Date startH) {
        this.startH = startH;
    }

    public Date getResponseDateL() {
        return responseDateL;
    }

    public void setResponseDateL(Date responseDateL) {
        this.responseDateL = responseDateL;
    }

    public Date getResponseDateH() {
        return responseDateH;
    }

    public void setResponseDateH(Date responseDateH) {
        this.responseDateH = responseDateH;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = (SpecificationSort) sort;
    }
}
