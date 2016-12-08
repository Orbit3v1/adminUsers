package com.app.data.entity.filter;

import com.app.data.dictionary.*;
import com.app.data.entity.AbstractVersionedEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tncRequestListFilter")
public class TNCRequestListFilter extends AbstractVersionedEntity implements Filter<TNCRequestListFilter>{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "tnc")
    private String tnc;

    @Column(name = "creator")
    private String creator;

    @Column(name = "responsible")
    private String responsible;

    @Column(name = "startL")
    private Date startL;

    @Column(name = "startH")
    private Date startH;

    @Column(name = "endPlanL")
    private Date endPlanL;

    @Column(name = "endPlanH")
    private Date endPlanH;

    @Column(name = "endActualL")
    private Date endActualL;

    @Column(name = "endActualH")
    private Date endActualH;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private TNCRequestState state;

    @Column(name = "sort")
    @Enumerated(EnumType.STRING)
    private TNCRequestSort sort;

    public void clear() {
        name = null;
        tnc = null;
        creator = null;
        responsible = null;
        startL = null;
        startH = null;
        endPlanL = null;
        endPlanH = null;
        endActualL = null;
        endActualH = null;
        state = null;
        sort = null;
    }

    public void copyFrom(TNCRequestListFilter filter){
        name = filter.name;
        tnc = filter.tnc;
        creator = filter.creator;
        responsible = filter.responsible;
        startL = filter.startL;
        startH = filter.startH;
        endPlanL = filter.endPlanL;
        endPlanH = filter.endPlanH;
        endActualL = filter.endActualL;
        endActualH = filter.endActualH;
        state = filter.state;
        sort = filter.sort;
    }

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

    public String getTnc() {
        return tnc;
    }

    public void setTnc(String tnc) {
        this.tnc = tnc;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String developer) {
        this.responsible = developer;
    }

    public Date getStartL() {
        return startL;
    }

    public void setStartL(Date startL) {
        this.startL = startL;
    }

    public Date getStartH() {
        return startH;
    }

    public void setStartH(Date startH) {
        this.startH = startH;
    }

    public Date getEndPlanL() {
        return endPlanL;
    }

    public void setEndPlanL(Date endPlanL) {
        this.endPlanL = endPlanL;
    }

    public Date getEndPlanH() {
        return endPlanH;
    }

    public void setEndPlanH(Date endPlanH) {
        this.endPlanH = endPlanH;
    }

    public Date getEndActualL() {
        return endActualL;
    }

    public void setEndActualL(Date endActualL) {
        this.endActualL = endActualL;
    }

    public Date getEndActualH() {
        return endActualH;
    }

    public void setEndActualH(Date endActualH) {
        this.endActualH = endActualH;
    }

    public TNCRequestState getState() {
        return state;
    }

    public void setState(TNCRequestState state) {
        this.state = state;
    }

    @Override
    public TNCRequestSort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = (TNCRequestSort) sort;
    }
}
