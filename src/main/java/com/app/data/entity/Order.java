package com.app.data.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends AbstractVersionedEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "customer")
    private String customer;

    @Column(name = "start")
    private Date start;

    @Column(name = "endPlan")
    private Date endPlan;

    @Column(name = "price", precision = 19, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "responsible")
    private Person responsible;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "order_payment",
            joinColumns = @JoinColumn(name = "orders"),
            inverseJoinColumns = @JoinColumn(name = "payment"))
    private List<Payment> payments;

    public Order() {
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Person getResponsible() {
        return responsible;
    }

    public void setResponsible(Person responsible) {
        this.responsible = responsible;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getEndPlan() {
        return endPlan;
    }

    public void setEndPlan(Date endPlan) {
        this.endPlan = endPlan;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
