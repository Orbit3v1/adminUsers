package com.app.data.entity;

import com.app.data.entity.interfaces.Converted;
import com.app.data.entity.interfaces.Selectable;
import com.app.data.entity.interfaces.Valuable;
import org.primefaces.event.SelectEvent;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("WORK")
public class ProductWork  extends Product implements Valuable, Selectable, Converted {
    @ManyToOne()
    @JoinColumn(name="calc_work")
    private Work work;

    public ProductWork(){
        super();
        this.setHeightAlias(null);
        this.setWidthAlias(null);
        this.setLengthAlias(null);
        this.setCountAlias(null);
    }

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
    public BigDecimal getRatio() {
        return new BigDecimal("60");
    }

    @Override
    public String getUnits() {
        return "час";
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
