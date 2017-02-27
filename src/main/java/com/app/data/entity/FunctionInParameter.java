package com.app.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "calc_functionInParams")
public class FunctionInParameter extends AbstractVersionedEntity<Integer> {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "pos")
    private int position;

    @ManyToOne
    @JoinColumn(name = "calc_function")
    private Function function;

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

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public FunctionInParameter copy(){
        FunctionInParameter copy = new FunctionInParameter();
        copy.name = this.name;
        copy.description = this.description;
        copy.id = this.id;
        copy.position = this.position;
        copy.setVersion(this.getVersion());
        return copy;
    }
}
