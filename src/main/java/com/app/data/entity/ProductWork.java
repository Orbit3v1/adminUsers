package com.app.data.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("WORK")
public class ProductWork  extends Product implements Valuable {
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
}
