package com.app.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nomenclature")
public class Nomenclature extends AbstractVersionedEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "nomenclature", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
    private List<NomenclatureAttachment> nomenclatureAttachments = new ArrayList<>();

    public Nomenclature() {
    }

    public Nomenclature(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
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

    public List<NomenclatureAttachment> getNomenclatureAttachments() {
        return nomenclatureAttachments;
    }

    public void setNomenclatureAttachments(List<NomenclatureAttachment> nomenclatureAttachments) {
        this.nomenclatureAttachments = nomenclatureAttachments;
    }
}
