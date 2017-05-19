package com.app.data.dto;

import com.app.data.entity.CarRequest;
import com.app.data.entity.ServiceRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ServiceRequestDTO {

    private ServiceRequest serviceRequest;
    private Integer id;
    private String name;
    private String time;
    private String creator;
    private String responsible;
    private Date start;
    private Date endActual;
    private String address;
    private String warrantyNumber;
    private String counterparty;
    private String contact;
    private String payment;
    private String description;
    private String result;

    public ServiceRequestDTO(ServiceRequest serviceRequest){
        this.serviceRequest = serviceRequest;
        id = serviceRequest.getId();
        name = serviceRequest.getName();
        time = getTime(serviceRequest.getStart());
        creator = serviceRequest.getCreator() == null ? null : serviceRequest.getCreator().toString();
        responsible = serviceRequest.getResponsible() == null ? null : serviceRequest.getResponsible().toString();
        start = serviceRequest.getStart();
        endActual = serviceRequest.getEndActual();
        address = serviceRequest.getAddress();
        warrantyNumber = serviceRequest.getWarrantyNumber();
        counterparty = serviceRequest.getCounterparty();
        contact = serviceRequest.getContactName() + " " + serviceRequest.getContactPhone();
        payment = serviceRequest.getPayment();
        description = serviceRequest.getDescription();
        result = serviceRequest.getResult();
    }

    private String getTime(Date date){
        String result = "";
        if(date != null){
            DateFormat df = new SimpleDateFormat("HH:mm");
            result = df.format(date);
        }
        return result;
    }

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
