package com.app.data.entity;

import com.app.data.entity.interfaces.Copy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "calc_function")
public class Function extends AbstractVersionedEntity<Integer> implements Copy<Function> {
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

    @OneToMany(mappedBy="function", fetch = FetchType.EAGER, cascade={CascadeType.ALL}, orphanRemoval = true)
    private List<FunctionInParameter> inParameters = new ArrayList<>();

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

    public List<FunctionInParameter> getInParameters() {
        return inParameters;
    }

    public void setInParameters(List<FunctionInParameter> inParameters) {
        this.inParameters = inParameters;
    }

    public Function copy(){
        Function copy = new Function();
        copy.name = this.name;
        copy.description = this.description;
        copy.code = this.code;
        copy.id = this.id;
        copy.setVersion(this.getVersion());
        List<FunctionInParameter> inParameters = new ArrayList<>();
        for(FunctionInParameter p : this.inParameters){
            FunctionInParameter inParameter = p.copy();
            inParameter.setFunction(copy);
            inParameters.add(inParameter);
        }
        copy.inParameters = inParameters;
        return copy;
    }

    public void copyData(Function copy){
        this.id = copy.id;
        this.setVersion(copy.getVersion());
        this.name = copy.name;
        this.description = copy.description;
        this.code = copy.code;
        this.inParameters = copy.inParameters;
    }
}
