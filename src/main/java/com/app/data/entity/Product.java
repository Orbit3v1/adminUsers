package com.app.data.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "calc_product")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType= DiscriminatorType.STRING)
@DiscriminatorValue("PRODUCT")
public class Product extends AbstractVersionedEntity implements Copy<Product>{
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
}



