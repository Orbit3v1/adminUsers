package com.app.data.entity;

import com.app.data.dictionary.TNCRequestState;
import com.app.data.entity.interfaces.TNCOwner;
import com.app.utils.AppUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tnc_request_item")
public class TNCRequestItem extends AbstractVersionedEntity implements TNCOwner {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "cnt")
    private int count;

    @Column(name = "reason")
    private String reason;

    @Column(name = "endPlan")
    private Date endPlan;

    @Column(name = "endActual")
    private Date endActual;

    @ManyToOne
    @JoinColumn(name = "calc_tnc")
    private TNC tnc;

    @ManyToOne
    @JoinColumn(name = "tnc_request")
    private TNCRequest tncRequest;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TNCRequestState state;

    public void copyForm(TNCRequestItem item){
        this.name = item.name;
        this.count = item.count;
        this.tnc = item.tnc;
        this.endPlan = item.endPlan;
        this.endActual = item.endActual;
        this.reason = item.reason;
        this.tncRequest = item.tncRequest;
        this.state = item.state;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public TNC getTnc() {
        return tnc;
    }

    public void setTnc(TNC tnc) {
        this.tnc = tnc;
    }

    public TNCRequest getTncRequest() {
        return tncRequest;
    }

    public void setTncRequest(TNCRequest tncRequest) {
        this.tncRequest = tncRequest;
    }

    public TNCRequestState getState() {
        return state;
    }

    public void setState(TNCRequestState status) {
        this.state = status;
    }

    public Integer getNameAsInt(){
        return AppUtil.isNumeric(name) ? AppUtil.toInteger(name) : 0;
    }
}
