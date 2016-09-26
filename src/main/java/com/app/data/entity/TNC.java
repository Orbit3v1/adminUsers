package com.app.data.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@SqlResultSetMapping(
        name = "TNCMapping",
        classes = @ConstructorResult(
                targetClass = TNC1C.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "unit"),
                        @ColumnResult(name = "price"),
                        @ColumnResult(name = "exist")}))
@Entity
@Table(name = "calc_tnc")
public class TNC extends AbstractVersionedEntity implements Copy<TNC>{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "detailName")
    private String description;

    @Column(name = "unitsFrom")
    private String unitsFrom;

    @Column(name = "unitsTo")
    private String unitsTo;

    @Column(name = "ratio", precision = 21, scale = 2)
    private BigDecimal ratio;

    @Column(name = "price", precision = 21, scale = 2)
    private BigDecimal price;

    @Column(name = "link1C")
    private byte[] link1C;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String detailName) {
        this.description = detailName;
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

    public byte[] getLink1C() {
        return link1C;
    }

    public void setLink1C(byte[] link1C) {
        this.link1C = link1C;
    }

    public TNC copy(){
        TNC copy = new TNC();
        copy.name = this.name;
        copy.description = this.description;
        copy.price = this.price;
        copy.unitsFrom = this.unitsFrom;
        copy.unitsTo = this.unitsTo;
        copy.ratio = this.ratio;
        copy.link1C = this.link1C;
        copy.id = this.id;
        copy.setVersion(this.getVersion());
        return copy;
    }

    public void copyData(TNC copy){
        this.id = copy.id;
        this.setVersion(copy.getVersion());
        this.name = copy.name;
        this.description = copy.description;
        this.price = copy.price;
        this.unitsFrom = copy.unitsFrom;
        this.unitsTo = copy.unitsTo;
        this.ratio = copy.ratio;
        this.link1C = copy.link1C;
    }
}
