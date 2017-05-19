package com.app.data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "service_request")
public class ServiceRequest extends AbstractVersionedEntity<Integer> {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "creator")
    private Person creator;

    @ManyToOne
    @JoinColumn(name = "responsible")
    private Person responsible;

    @Column(name = "start")
    private Date start;

    @Column(name = "endActual")
    private Date endActual;

    @Column(name = "warranty_number")
    private String warrantyNumber;

    @Column(name = "counterparty")
    private String counterparty;

    @Column(name = "contactName")
    private String contactName;

    @Column(name = "contactPhone")
    private String contactPhone;

    @Column(name = "payment")
    private String payment;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "result")
    private String result;

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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEndActual() {
        return endActual;
    }

    public void setEndActual(Date endActual) {
        this.endActual = endActual;
    }

    public String getWarrantyNumber() {
        return warrantyNumber;
    }

    public void setWarrantyNumber(String warranty_number) {
        this.warrantyNumber = warranty_number;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
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
}
