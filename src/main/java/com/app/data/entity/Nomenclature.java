package com.app.data.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nomenclature")
public class Nomenclature extends AbstractVersionedEntity<Integer> {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "material")
    private String material;

    @Column(name = "gib")
    private Integer gib;

    @Column(name = "ready")
    @Type(type = "yes_no")
    private boolean ready;

    @OneToMany(mappedBy = "nomenclature", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<NomenclatureAttachment> nomenclatureAttachments = new ArrayList<>();

    @OneToMany(mappedBy = "nomenclature", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Component> components = new ArrayList<>();

    @OneToMany(mappedBy = "nomenclature")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "nomenclature")
    private List<Specification> specifications = new ArrayList<>();

    public Nomenclature() {
    }

    public Nomenclature(String name) {
        this.name = name;
    }

    public Nomenclature(Nomenclature nomenclature){
        this.name = nomenclature.name;
        this.description = nomenclature.description;
        this.material = nomenclature.description;
        this.gib = nomenclature.gib;
    }

    @Override
    public String toString() {
        return name;
    }

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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getGib() {
        return gib;
    }

    public void setGib(Integer gib) {
        this.gib = gib;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<NomenclatureAttachment> getNomenclatureAttachments() {
        return nomenclatureAttachments;
    }

    public void setNomenclatureAttachments(List<NomenclatureAttachment> nomenclatureAttachments) {
        this.nomenclatureAttachments = nomenclatureAttachments;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> nomenclatureComponents) {
        this.components = nomenclatureComponents;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
    }

    public Specification getSpecification() {
        return specifications != null && specifications.size() > 0 ? specifications.get(0) : null;
    }
}
