package com.app.data.entity;

import org.primefaces.event.SelectEvent;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("TNC")
public class ProductTNC extends Product implements Valuable, Converted, Selectable {
    @ManyToOne()
    @JoinColumn(name="calc_tnc")
    private TNC tnc;

    @Override
    public String getName() {
        return tnc.getName();
    }

    public TNC getTnc() {
        return tnc;
    }

    public void setTnc(TNC tnc) {
        this.tnc = tnc;
    }

    @Override
    public BigDecimal getPrice() {
        return tnc == null ? null : tnc.getPrice();
    }

    @Override
    public BigDecimal getRatio() {
        return tnc == null ? null : tnc.getRatio();
    }

    @Override
    public void onSelect(SelectEvent event) {
        TNC tnc = (TNC) event.getObject();
        setTnc(tnc);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ProductTNC product = (ProductTNC) super.clone();
        product.tnc = this.tnc;
        return product;
    }
}
