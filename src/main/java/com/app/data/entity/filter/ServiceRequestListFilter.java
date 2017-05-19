package com.app.data.entity.filter;

import com.app.data.dictionary.*;
import com.app.data.entity.AbstractVersionedEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "serviceRequestListFilter")
public class ServiceRequestListFilter extends AbstractVersionedEntity<Integer> implements Filter<ServiceRequestListFilter> {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "creator")
    private String creator;

    @Column(name = "responsible")
    private String responsible;

    @Column(name = "startL")
    private Date startL;

    @Column(name = "startH")
    private Date startH;

    @Column(name = "endActualL")
    private Date endActualL;

    @Column(name = "endActualH")
    private Date endActualH;

    @Column(name = "warranty_number")
    private String warrantyNumber;

    @Column(name = "counterparty")
    private String counterparty;

    @Column(name = "contact")
    private String contact;

    @Column(name = "payment")
    private String payment;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "result")
    private String result;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private ServiceRequestState state;

    @Column(name = "sort")
    @Enumerated(EnumType.STRING)
    private ServiceRequestSort sort;

    public void clear() {
        name = null;
        creator = null;
        responsible = null;
        startL = null;
        startH = null;
        endActualL = null;
        endActualH = null;
        warrantyNumber = null;
        counterparty = null;
        contact = null;
        payment = null;
        description = null;
        address = null;
        result = null;
        sort = null;
        state = ServiceRequestState.ALL;
    }

    public void copyFrom(ServiceRequestListFilter filter){
        name = filter.name;
        creator = filter.creator;
        responsible = filter.responsible;
        startL = filter.startL;
        startH = filter.startH;
        endActualL = filter.endActualL;
        endActualH = filter.endActualH;
        warrantyNumber = filter.warrantyNumber;
        counterparty = filter.counterparty;
        contact = filter.contact;
        payment = filter.payment;
        description = filter.description;
        address = filter.address;
        result = filter.result;
        sort = filter.sort;
        state = filter.state;
    }

    @Override
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
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

    public String getWarrantyNumber() {
        return warrantyNumber;
    }

    public void setWarrantyNumber(String warrantyNumber) {
        this.warrantyNumber = warrantyNumber;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ServiceRequestState getState() {
        return state;
    }

    public void setState(ServiceRequestState state) {
        this.state = state;
    }

    @Override
    public ServiceRequestSort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = (ServiceRequestSort) sort;
    }
}
