package com.app.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "specification")
public class Specification extends AbstractVersionedEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "subName")
    private Integer subName;

    @Column(name = "start")
    private Date start;

    @Column(name = "price")
    private String price;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "type")
    private String type;

    @Column(name = "responseDate")
    private Date responseDate;

    @Column(name = "mainSize")
    private String mainSize;

    @Column(name = "detailSize")
    private String detailSize;

    @Column(name = "pressure")
    private String pressure;

    @Column(name = "additional")
    private String additional;

    @Column(name = "workDays")
    private Integer workDays;

    @Column(name = "approved")
    private Date approved;

    @Column(name = "checked")
    @Type(type = "yes_no")
    private Boolean checked;

    @ManyToOne
    @JoinColumn(name="nomenclature")
    private Nomenclature nomenclature;

    @ManyToOne
    @JoinColumn(name = "responsible")
    private Person responsible;

    @ManyToOne
    @JoinColumn(name = "developer")
    private Person developer;

    @ManyToOne
    @JoinColumn(name = "approvedBy")
    private Person approvedBy;

    public Specification() {
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public int getSubName() {
        return subName;
    }

    public void setSubName(int subName) {
        this.subName = subName;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public String getMainSize() {
        return mainSize;
    }

    public void setMainSize(String mainSize) {
        this.mainSize = mainSize;
    }

    public String getDetailSize() {
        return detailSize;
    }

    public void setDetailSize(String detailSize) {
        this.detailSize = detailSize;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public Integer getWorkDays() {
        return workDays;
    }

    public void setWorkDays(Integer workDays) {
        this.workDays = workDays;
    }

    public Date getApproved() {
        return approved;
    }

    public void setApproved(Date approved) {
        this.approved = approved;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public Person getResponsible() {
        return responsible;
    }

    public void setResponsible(Person responsible) {
        this.responsible = responsible;
    }

    public Person getDeveloper() {
        return developer;
    }

    public void setDeveloper(Person developer) {
        this.developer = developer;
    }

    public Person getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Person approvedBy) {
        this.approvedBy = approvedBy;
    }
}
