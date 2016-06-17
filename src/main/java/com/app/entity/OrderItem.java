package com.app.entity;

import com.app.dictionary.NAType;
import com.app.dictionary.OrderItemState;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "orderItem")
public class OrderItem extends AbstractVersionedEntity{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "cnt")
    private int count;

    @Column(name = "docDate")
    private Date docDate;

    @Column(name = "endPlan")
    private Date endPlan;

    @Column(name = "endActual")
    private Date endActual;

    @ManyToOne
    @JoinColumn(name = "nomenclature")
    private Nomenclature nomenclature;

    @ManyToOne
    @JoinColumn(name = "developer")
    private Person developer;

    @ManyToOne
    @JoinColumn(name = "orders")
    private Order order;

    public OrderItem() {
    }

    public void copyForm(OrderItem orderItem){
        this.name = orderItem.name;
        this.count = orderItem.count;
        this.docDate = orderItem.docDate;
        this.endPlan = orderItem.endPlan;
        this.endActual = orderItem.endActual;
        this.nomenclature = orderItem.nomenclature;
        this.developer = orderItem.developer;
        this.order = orderItem.order;
    }

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Date getEndPlan() {
        return endPlan;
    }

    public void setEndPlan(Date endPlan) {
        this.endPlan = endPlan;
    }

    public Date getEndActual() {
        return endActual;
    }

    public void setEndActual(Date endActual) {
        this.endActual = endActual;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public Person getDeveloper() {
        return developer;
    }

    public void setDeveloper(Person developer) {
        this.developer = developer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
