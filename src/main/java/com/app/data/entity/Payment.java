package com.app.data.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment extends AbstractVersionedEntity{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "amount", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "date")
    private Date date;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void copyData(Payment copy){
        this.id = copy.id;
        this.setVersion(copy.getVersion());
        this.description = copy.description;
        this.amount = copy.amount;
        this.date = copy.date;
    }
}
