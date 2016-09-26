package com.app.data.entity;

import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import javax.persistence.*;
import java.math.BigDecimal;


public class TNC1C {

    private byte[] id;
    private String name;
    private String units;
    private BigDecimal price;

    public TNC1C() {
    }

    public TNC1C(byte[] id, String name, String units, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.units = units;
        this.price = price;
    }

    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
