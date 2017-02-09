package com.app.data.entity;

import com.app.data.entity.interfaces.Converted;
import com.app.data.entity.interfaces.Selectable;
import com.app.data.entity.interfaces.Valuable;
import org.primefaces.event.SelectEvent;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("TNC")
public class ProductTNC extends Product implements Valuable, Converted, Selectable {
    @ManyToOne()
    @JoinColumn(name="calc_tnc")
    private TNC tnc;

    @Override
    public String getName() {
        return tnc.getNameInner();
    }

    public TNC getTnc() {
        return tnc;
    }

    public void setTnc(TNC tnc) {
        this.tnc = tnc;
    }

    public ProductTNC(){
        super();
        this.setHeightAlias(null);
        this.setWidthAlias(null);
        this.setLengthAlias(null);
        this.setCountAlias(null);
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
    public String getUnits() {
        return tnc == null ? null : tnc.getUnitsFrom();
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
