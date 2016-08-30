package com.app.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "specification")
public class Specification extends AbstractVersionedEntity implements SetNomenclature{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "subName")
    private String subName;

    @Column(name = "start")
    private Date start;

    @Column(name = "price")
    private String price;

    @Column(name = "discount")
    private String discount;

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

    @OneToMany(mappedBy = "specification", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SpecificationAttachment> specificationAttachments = new ArrayList<>();

    public Specification() {
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
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

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
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

    public List<SpecificationAttachment> getSpecificationAttachments() {
        return specificationAttachments;
    }

    public void setSpecificationAttachments(List<SpecificationAttachment> specificationAttachments) {
        this.specificationAttachments = specificationAttachments;
    }
}
