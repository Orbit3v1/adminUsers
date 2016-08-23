package com.app.entity;

import com.app.dictionary.NAType;

import javax.persistence.*;

@Entity
@Table(name = "specification_attachment")
public class SpecificationAttachment {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="specification")
    private Specification specification;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="attachment")
    private Attachment attachment;

    public SpecificationAttachment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
