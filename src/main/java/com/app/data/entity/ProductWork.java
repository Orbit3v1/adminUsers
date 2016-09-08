package com.app.data.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "calc_product")
@DiscriminatorValue("WORK")
public class ProductWork  extends Product{
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
}
