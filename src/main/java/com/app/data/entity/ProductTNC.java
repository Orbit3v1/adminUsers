package com.app.data.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("TNC")
public class ProductTNC extends Product implements Valuable, Converted {
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
}
