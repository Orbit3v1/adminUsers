package com.app.entity;

import com.app.dictionary.OrderItemState;
import com.app.dictionary.ProductionReportSort;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orderListFilter")
public class OrderListFilter extends AbstractVersionedEntity {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "customer")
    private String customer;

    @Column(name = "nomenclature")
    private String nomenclature;

    @Column(name = "responsible")
    private String responsible;

    @Column(name = "developer")
    private String developer;

    @Column(name = "startL")
    private Date startL;

    @Column(name = "startH")
    private Date startH;

    @Column(name = "docDateL")
    private Date docDateL;

    @Column(name = "docDateH")
    private Date docDateH;

    @Column(name = "endPlanL")
    private Date endPlanL;

    @Column(name = "endPlanH")
    private Date endPlanH;

    @Column(name = "endActualL")
    private Date endActualL;

    @Column(name = "endActualH")
    private Date endActualH;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private OrderItemState state;

    @Column(name = "sort")
    @Enumerated(EnumType.STRING)
    private ProductionReportSort sort;

    public OrderListFilter() {
    }

    public void clear() {
        name = null;
        customer = null;
        nomenclature = null;
        responsible = null;
        developer = null;
        startL = null;
        startH = null;
        docDateL = null;
        docDateH = null;
        endPlanL = null;
        endPlanH = null;
        endActualL = null;
        endActualH = null;
        state = OrderItemState.ALL;
        sort = null;
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
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

    public Date getDocDateL() {
        return docDateL;
    }

    public void setDocDateL(Date docDateL) {
        this.docDateL = docDateL;
    }

    public Date getDocDateH() {
        return docDateH;
    }

    public void setDocDateH(Date docDateH) {
        this.docDateH = docDateH;
    }

    public Date getEndPlanL() {
        return endPlanL;
    }

    public void setEndPlanL(Date endPlanL) {
        this.endPlanL = endPlanL;
    }

    public Date getEndPlanH() {
        return endPlanH;
    }

    public void setEndPlanH(Date endPlanH) {
        this.endPlanH = endPlanH;
    }

    public Date getEndActualL() {
        return endActualL;
    }

    public void setEndActualL(Date endActualL) {
        this.endActualL = endActualL;
    }

    public Date getEndActualH() {
        return endActualH;
    }

    public void setEndActualH(Date endActualH) {
        this.endActualH = endActualH;
    }

    public OrderItemState getState() {
        return state;
    }

    public void setState(OrderItemState state) {
        this.state = state;
    }

    public ProductionReportSort getSort() {
        return sort;
    }

    public void setSort(ProductionReportSort sort) {
        this.sort = sort;
    }
}
