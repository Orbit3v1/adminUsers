package com.app.data.entity;

import javax.persistence.*;

@Entity
@DiscriminatorValue("TNC")
public class ProductTNC extends Product{
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
}
