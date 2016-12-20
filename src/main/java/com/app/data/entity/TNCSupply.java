package com.app.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tnc_supply")
public class TNCSupply extends AbstractVersionedEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "provider")
    private String provider;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "deliveryType")
    private String deliveryType;

    @Column(name = "start")
    private Date start;

    @Column(name = "paymentDate")
    private Date paymentDate;

    @Column(name = "endPlan")
    private Date endPlan;

    @Column(name = "endActual")
    private Date endActual;

    @OneToMany(mappedBy = "tncSupply", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TNCSupplyItem> tncSupplyItems = new ArrayList<>();

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

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
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

    public List<TNCSupplyItem> getTncSupplyItems() {
        return tncSupplyItems;
    }

    public void setTncSupplyItems(List<TNCSupplyItem> tncSupplyItems) {
        this.tncSupplyItems = tncSupplyItems;
    }
}
