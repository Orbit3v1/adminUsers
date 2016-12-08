package com.app.data.dto;

import com.app.data.dictionary.TNCRequestState;
import com.app.data.entity.*;

import java.util.Date;


public class TNCRequestDTO {
    private boolean fromNewRequest;
    private String name;
    private String tncName;
    private Integer count;
    private Integer limitLow;
    private Integer limitHigh;
    private String creator;
    private Date start;
    private Date endPlan;
    private Date endActual;
    private TNCRequestState state;

    private TNCRequest tncRequest;
    private TNCRequestItem tncRequestItem;
    private TNC tnc;
    private Person creatorEntity;

    public TNCRequestDTO(TNCRequestItem tncRequestItem, boolean fromNewRequest) {
        this.fromNewRequest = fromNewRequest;
        this.name = tncRequestItem.getTncRequest().getName() + "_" + tncRequestItem.getName();
        this.tncName = tncRequestItem.getTnc().getNameInner();
        this.count = tncRequestItem.getCount();
        this.limitLow = tncRequestItem.getTnc().getLimitLow();
        this.limitHigh = tncRequestItem.getTnc().getLimitHigh();
        this.creator = tncRequestItem.getTncRequest().getCreator() == null ? null : tncRequestItem.getTncRequest().getCreator().toString();
        this.start = tncRequestItem.getTncRequest().getStart();
        this.endPlan = tncRequestItem.getEndPlan();
        this.endActual = tncRequestItem.getEndActual();
        this.state = tncRequestItem.getState();
        this.tncRequest = tncRequestItem.getTncRequest();
        this.tncRequestItem = tncRequestItem;
        this.tnc = tncRequestItem.getTnc();
        this.creatorEntity = tncRequestItem.getTncRequest().getCreator();
    }

    public boolean isFromNewRequest() {
        return fromNewRequest;
    }

    public void setFromNewRequest(boolean fromNewRequest) {
        this.fromNewRequest = fromNewRequest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTncName() {
        return tncName;
    }

    public void setTncName(String tncName) {
        this.tncName = tncName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLimitLow() {
        return limitLow;
    }

    public void setLimitLow(Integer limitLow) {
        this.limitLow = limitLow;
    }

    public Integer getLimitHigh() {
        return limitHigh;
    }

    public void setLimitHigh(Integer limitHigh) {
        this.limitHigh = limitHigh;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public Date getEndActual() {
        return endActual;
    }

    public void setEndActual(Date endActual) {
        this.endActual = endActual;
    }

    public TNCRequestState getState() {
        return state;
    }

    public void setState(TNCRequestState state) {
        this.state = state;
    }

    public TNCRequest getTncRequest() {
        return tncRequest;
    }

    public void setTncRequest(TNCRequest tncRequest) {
        this.tncRequest = tncRequest;
    }

    public TNCRequestItem getTncRequestItem() {
        return tncRequestItem;
    }

    public void setTncRequestItem(TNCRequestItem tncRequestItem) {
        this.tncRequestItem = tncRequestItem;
    }

    public TNC getTnc() {
        return tnc;
    }

    public void setTnc(TNC tnc) {
        this.tnc = tnc;
    }

    public Person getCreatorEntity() {
        return creatorEntity;
    }

    public void setCreatorEntity(Person creatorEntity) {
        this.creatorEntity = creatorEntity;
    }
}
