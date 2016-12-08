package com.app.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tnc_request")
public class TNCRequest extends AbstractVersionedEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "start")
    private Date start;

    @Column(name = "endPlan")
    private Date endPlan;

    @ManyToOne
    @JoinColumn(name = "creator")
    private Person creator;

    @ManyToOne
    @JoinColumn(name = "responsible")
    private Person responsible;

    @OneToMany(mappedBy = "tncRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TNCRequestItem> tncRequestItems = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEndPlan() {
        return endPlan;
    }

    public void setEndPlan(Date endPlan) {
        this.endPlan = endPlan;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    public Person getResponsible() {
        return responsible;
    }

    public void setResponsible(Person responsible) {
        this.responsible = responsible;
    }

    public List<TNCRequestItem> getTncRequestItems() {
        return tncRequestItems;
    }

    public void setTncRequestItems(List<TNCRequestItem> tncRequestItems) {
        this.tncRequestItems = tncRequestItems;
    }
}
