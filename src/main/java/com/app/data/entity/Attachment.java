package com.app.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "attachment")
public class Attachment extends AbstractVersionedEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Long size;

    @OneToOne(optional = false, fetch = FetchType.LAZY, mappedBy = "attachment", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AttachmentContent content;

    @Column(name = "type")
    private String type;

    public Attachment() {
    }

    @Override
    public int hashCode() {
        return (getClass().hashCode() + Integer.valueOf(id).hashCode() + name.hashCode() + size.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null && getClass() == obj.getClass())
                ? (id == ((Attachment) obj).id
                && name.equals(((Attachment) obj).name)
                && size.equals(((Attachment) obj).size))
                : (obj == this);
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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public AttachmentContent getContent() {
        return content;
    }

    public void setContent(AttachmentContent content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
