package com.app.data.entity;

import com.app.data.entity.interfaces.Copy;
import com.app.data.entity.interfaces.CopyData;

import javax.persistence.*;

@Entity
@Table(name = "component")
public class Component extends AbstractVersionedEntity implements Copy<Component> {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="nomenclature")
    private Nomenclature nomenclature;

    public Component() {
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

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public void copyData(Component component){
        this.name = component.name;
        this.nomenclature = component.nomenclature;
    }

    @Override
    public Component copy() {
        Component entity = new Component();
        entity.name = this.name;
        entity.nomenclature = this.nomenclature;
        return entity;
    }
}
