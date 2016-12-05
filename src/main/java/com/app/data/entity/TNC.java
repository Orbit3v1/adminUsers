package com.app.data.entity;

import com.app.data.entity.interfaces.Copy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
public class TNC extends AbstractVersionedEntity implements Copy<TNC> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "name_inner")
    private String nameInner;

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

    @Column(name = "limit_low")
    private Integer limitLow;

    @Column(name = "limit_high")
    private Integer limitHigh;

    @Column(name = "balance")
    private Integer balance;

    @OneToMany(mappedBy = "tnc")
    private List<ProductTNC> products;

    @OneToMany(mappedBy = "tnc", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TNCAttachment> TNCAttachments = new ArrayList<>();

    @OneToMany(mappedBy = "tnc", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TNCLink> tncLinks = new ArrayList<>();

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

    public List<ProductTNC> getProducts() {
        return products;
    }

    public void setProducts(List<ProductTNC> products) {
        this.products = products;
    }

    public String getNameInner() {
        return nameInner;
    }

    public void setNameInner(String nameInner) {
        this.nameInner = nameInner;
    }

    public Integer getLimitLow() {
        return limitLow;
    }

    public void setLimitLow(Integer limitLow) {
        this.limitLow = limitLow;
    }

    public Integer getLimitHigh() {
        return limitHigh;
    }

    public void setLimitHigh(Integer limitHigh) {
        this.limitHigh = limitHigh;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public List<TNCAttachment> getTNCAttachments() {
        return TNCAttachments;
    }

    public List<TNCLink> getTncLinks() {
        return tncLinks;
    }

    public void setTncLinks(List<TNCLink> tncLinks) {
        this.tncLinks = tncLinks;
    }

    public void setTNCAttachments(List<TNCAttachment> TNCAttachments) {
        this.TNCAttachments = TNCAttachments;
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
        copy.nameInner = this.nameInner;
        copy.limitLow = this.limitLow;
        copy.limitHigh = this.limitHigh;
        copy.balance = this.balance;
        copy.TNCAttachments = this.TNCAttachments;
        copy.tncLinks = this.tncLinks;
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
        this.nameInner = copy.nameInner;
        this.limitLow = copy.limitLow;
        this.limitHigh = copy.limitHigh;
        this.balance = copy.balance;
        this.TNCAttachments = copy.TNCAttachments;
        this.tncLinks = copy.tncLinks;
    }
}
