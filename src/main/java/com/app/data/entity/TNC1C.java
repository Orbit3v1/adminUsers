package com.app.data.entity;

import java.math.BigDecimal;


public class TNC1C {

    private byte[] id;
    private String name;
    private String unit;
    private BigDecimal price;
    private boolean exist;

    public TNC1C() {
    }

    public TNC1C(byte[] id, String name, String units, BigDecimal price, String exist) {
        this.id = id;
        this.name = name;
        this.unit = units;
        this.price = price;
        this.exist = exist.equals("Y");
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }
}
