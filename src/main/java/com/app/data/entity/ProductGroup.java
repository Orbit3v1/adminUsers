package com.app.data.entity;

import com.app.data.entity.interfaces.Copy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "calc_product_group")
public class ProductGroup  extends AbstractVersionedEntity<Integer> implements Copy<ProductGroup> {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="parentId")
    private ProductGroup parent;

    @OneToMany(mappedBy="parent", fetch = FetchType.EAGER, cascade={CascadeType.REMOVE}, orphanRemoval = true)
    private List<ProductGroup> subordinates = new ArrayList<>();

    @OneToMany(mappedBy="productGroup", fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

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

    public ProductGroup getParent() {
        return parent;
    }

    public void setParent(ProductGroup parent) {
        this.parent = parent;
    }

    public List<ProductGroup> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<ProductGroup> subordinates) {
        this.subordinates = subordinates;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public ProductGroup copy(){
        ProductGroup copy = new ProductGroup();
        copy.name = this.name;
        copy.id = this.id;
        copy.setVersion(this.getVersion());
        copy.setParent(this.getParent());
        return copy;
    }

    public void copyData(ProductGroup copy){
        this.id = copy.id;
        this.setVersion(copy.getVersion());
        this.name = copy.name;
    }
}
