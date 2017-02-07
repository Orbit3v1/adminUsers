package com.app.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "calc_productInParams")
public class ProductInParameter extends AbstractVersionedEntity<Integer> implements Cloneable{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "calc_product")
    private Product product;

    public ProductInParameter(){

    }

    public ProductInParameter(ProductInParameter productInParameter){
        this.name = productInParameter.name;
        this.description = productInParameter.description;
        this.value = productInParameter.value;
    }

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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ProductInParameter inParameter = (ProductInParameter) super.clone();
        return inParameter;
    }
}
