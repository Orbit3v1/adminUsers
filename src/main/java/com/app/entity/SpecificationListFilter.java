package com.app.entity;

import com.app.dictionary.OrderItemState;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "specificationListFilter")
public class SpecificationListFilter extends AbstractVersionedEntity  {
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

    @Column(name = "start")
    private Date start;

    @Column(name = "responseDate")
    private Date responseDate;

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
        start = null;
        responseDate = null;
    }

    public void copyFrom(SpecificationListFilter filter){
        name = filter.name;
        type = filter.type;
        nomenclature = filter.nomenclature;
        responsible = filter.responsible;
        developer = filter.developer;
        price = filter.price;
        discount = filter.discount;
        start = filter.start;
        responseDate = filter.responseDate;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }
}
