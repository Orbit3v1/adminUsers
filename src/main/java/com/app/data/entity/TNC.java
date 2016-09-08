package com.app.data.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "calc_tnc")
public class TNC extends AbstractVersionedEntity{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "detailName")
    private String detailName;

    @Column(name = "unitsFrom")
    private String unitsFrom;

    @Column(name = "unitsTo")
    private String unitsTo;

    @Column(name = "ratio", precision = 21, scale = 4)
    private BigDecimal ratio;

    @Column(name = "price", precision = 21, scale = 4)
    private BigDecimal price;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    public String getUnitsFrom() {
        return unitsFrom;
    }

    public void setUnitsFrom(String unitsFrom) {
        this.unitsFrom = unitsFrom;
    }

    public String getUnitsTo() {
        return unitsTo;
    }

    public void setUnitsTo(String unitsTo) {
        this.unitsTo = unitsTo;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
