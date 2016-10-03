package com.app.data.entity;

import org.primefaces.event.SelectEvent;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("WORK")
public class ProductWork  extends Product implements Valuable, Selectable {
    @ManyToOne()
    @JoinColumn(name="calc_work")
    private Work work;

    @Override
    public String getName() {
        return work.getName();
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    @Override
    public BigDecimal getPrice() {
        return work == null ? null : work.getPrice();
    }

    @Override
    public void onSelect(SelectEvent event) {
        Work work = (Work) event.getObject();
        setWork(work);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ProductWork product = (ProductWork) super.clone();
        product.work = this.work;
        return product;
    }
}
