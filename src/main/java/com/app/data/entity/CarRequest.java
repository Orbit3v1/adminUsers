package com.app.data.entity;

import com.app.data.entity.interfaces.Copy;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "car_request")
public class CarRequest extends AbstractVersionedEntity<Integer> {
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

    @Column(name = "addressFrom")
    private String addressFrom;

    @Column(name = "addressTo")
    private String addressTo;

    @Column(name = "receiverName")
    private String receiverName;

    @Column(name = "receiverPhone")
    private String receiverPhone;

    @Column(name = "payment")
    private String payment;

    @Column(name = "description")
    private String description;

    @Column(name = "priority")
    private Integer priority;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "car_request_attachment",
            joinColumns = @JoinColumn(name = "car_request"),
            inverseJoinColumns = {
                    @JoinColumn(name = "attachment")
            })
    private List<Attachment> attachments = new ArrayList<>();

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

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
