package com.app.entity;

import javax.persistence.*;

@Entity
@Table(name = "privilege")
public class Privilege {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "pos")
    private int pos;

    public Privilege() {
    }

    public Privilege(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null && getClass() == obj.getClass() && id != null)
                ? id.equals(((Privilege) obj).id)
                : (obj == this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
