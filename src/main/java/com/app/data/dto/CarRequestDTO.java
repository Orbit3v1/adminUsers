package com.app.data.dto;

import com.app.data.entity.CarRequest;
import com.app.data.entity.Person;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ayaroslavtsev on 27.02.2017.
 */
public class CarRequestDTO {

    private CarRequest carRequest;
    private Integer id;
    private String name;
    private String time;
    private String creator;
    private String responsible;
    private Date start;
    private Date endActual;
    private String addressFrom;
    private String addressTo;
    private String receiver;
    private String payment;
    private String description;
    private Integer priority;
    private String declaration;

    public CarRequestDTO(CarRequest carRequest){
        this.carRequest = carRequest;
        id = carRequest.getId();
        name = carRequest.getName();
        time = getTime(carRequest.getStart());
        creator = carRequest.getCreator() == null ? null : carRequest.getCreator().toString();
        responsible = carRequest.getResponsible() == null ? null : carRequest.getResponsible().toString();
        start = carRequest.getStart();
        endActual = carRequest.getEndActual();
        addressFrom = carRequest.getAddressFrom();
        addressTo = carRequest.getAddressTo();
        receiver = carRequest.getReceiverName() + " " + carRequest.getReceiverPhone();
        payment = carRequest.getPayment();
        description = carRequest.getDescription();
        priority = carRequest.getPriority();
        declaration = carRequest.getDeclaration();
    }

    private String getTime(Date date){
        String result = "";
        if(date != null){
            DateFormat df = new SimpleDateFormat("HH:mm");
            result = df.format(date);
        }
        return result;
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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

    public CarRequest getCarRequest() {
        return carRequest;
    }

    public void setCarRequest(CarRequest carRequest) {
        this.carRequest = carRequest;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }
}
