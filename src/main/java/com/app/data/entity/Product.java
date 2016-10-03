package com.app.data.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "calc_product")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType= DiscriminatorType.STRING)
@DiscriminatorValue("PRODUCT")
public class Product extends AbstractVersionedEntity implements Copy<Product>, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "detail")
    private String detail;

    @Column(name = "height")
    private Float height;

    @Column(name = "width")
    private Float width;

    @Column(name = "length")
    private Float length;

    @Column(name = "formula")
    private String formula;

    @Column(name = "type", insertable = false, updatable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name="parentId")
    private Product parent;

    @OneToMany(mappedBy="parent", fetch = FetchType.EAGER, cascade={CascadeType.ALL}, orphanRemoval = true)
    private List<Product> subordinates = new ArrayList<>();


    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public String getFormula() {
        return formula == null ? "" : formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Product getParent() {
        return parent;
    }

    public void setParent(Product parent) {
        this.parent = parent;
    }

    public List<Product> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Product> subordinates) {
        this.subordinates = subordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Product copy(){
        Product copy = new Product();
        copy.name = this.name;
        copy.id = this.id;
        copy.setVersion(this.getVersion());
        return copy;
    }

    public void copyData(Product copy){
        this.id = copy.id;
        this.setVersion(copy.getVersion());
        this.name = copy.name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Product product = (Product) super.clone();
        if(product.parent == null){
            product.name = this.name + "_" + getNameSuffix();
        }
        product.setId(0);
        product.setVersion(this.getVersion());
        product.detail = this.detail;
        product.height = this.height;
        product.width = this.width;
        product.length = this.length;
        product.formula = this.formula;
        List<Product> subordinates = new ArrayList<>();
        for(Product p : this.subordinates){
            Product subProduct = (Product) p.clone();
            subProduct.parent = product;
            subordinates.add(subProduct);
        }
        product.subordinates = subordinates;
        return product;
    }

    private String getNameSuffix(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }
}



