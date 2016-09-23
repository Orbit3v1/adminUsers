package com.app.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "calc_function")
public class Function extends AbstractVersionedEntity implements Copy<Function>{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "code")
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Function copy(){
        Function copy = new Function();
        copy.name = this.name;
        copy.description = this.description;
        copy.code = this.code;
        copy.id = this.id;
        copy.setVersion(this.getVersion());
        return copy;
    }

    public void copyData(Function copy){
        this.id = copy.id;
        this.setVersion(copy.getVersion());
        this.name = copy.name;
        this.description = copy.description;
        this.code = copy.code;
    }
}
