package com.app.data.entity;

import com.app.data.entity.interfaces.Copy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "calc_work")
public class Work extends AbstractVersionedEntity implements Copy<Work> {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 21, scale = 4)
    private BigDecimal price;

    @OneToMany(mappedBy = "work")
    private List<ProductWork> products;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ProductWork> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWork> products) {
        this.products = products;
    }

    public Work copy(){
        Work copy = new Work();
        copy.name = this.name;
        copy.description = this.description;
        copy.price = this.price;
        copy.id = this.id;
        copy.setVersion(this.getVersion());
        return copy;
    }

    public void copyData(Work copy){
        this.id = copy.id;
        this.setVersion(copy.getVersion());
        this.name = copy.name;
        this.description = copy.description;
        this.price = copy.price;
    }
}
