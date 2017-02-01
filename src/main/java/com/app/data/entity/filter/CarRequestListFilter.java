package com.app.data.entity.filter;

import com.app.data.dictionary.CarRequestSort;
import com.app.data.dictionary.Sort;
import com.app.data.entity.AbstractVersionedEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "carRequestListFilter")
public class CarRequestListFilter extends AbstractVersionedEntity<Integer> implements Filter<CarRequestListFilter> {
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
    private String priority;

    @Column(name = "sort")
    @Enumerated(EnumType.STRING)
    private CarRequestSort sort;

    public void clear() {
        name = null;
        creator = null;
        responsible = null;
        startL = null;
        startH = null;
        endActualL = null;
        endActualH = null;
        addressFrom = null;
        addressTo = null;
        receiverName = null;
        receiverPhone = null;
        payment = null;
        description = null;
        priority = null;
        sort = null;
    }

    public void copyFrom(CarRequestListFilter filter){
        name = filter.name;
        creator = filter.creator;
        responsible = filter.responsible;
        startL = filter.startL;
        startH = filter.startH;
        endActualL = filter.endActualL;
        endActualH = filter.endActualH;
        addressFrom = filter.addressFrom;
        addressTo = filter.addressTo;
        receiverName = filter.receiverName;
        receiverPhone = filter.receiverPhone;
        payment = filter.payment;
        description = filter.description;
        priority = filter.priority;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public CarRequestSort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = (CarRequestSort) sort;
    }
}


