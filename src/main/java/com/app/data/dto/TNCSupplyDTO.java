package com.app.data.dto;

import com.app.data.entity.TNCSupply;
import com.app.data.entity.TNCSupplyItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TNCSupplyDTO {
    private Integer id;
    private String name;
    private String provider;
    private String accountNumber;
    private String deliveryType;
    private Date start;
    private Date paymentDate;
    private Date endPlan;
    private Date endActual;
    private List<TNCSupplyItemDTO> items;

    public TNCSupplyDTO(TNCSupply tncSupply){
        this.id = tncSupply.getId();
        this.name = tncSupply.getName();
        this.provider = tncSupply.getProvider();
        this.accountNumber = tncSupply.getAccountNumber();
        this.deliveryType = tncSupply.getDeliveryType();
        this.start = tncSupply.getStart();
        this.paymentDate = tncSupply.getPaymentDate();
        this.endPlan = tncSupply.getEndPlan();
        this.endActual = tncSupply.getEndActual();
        items = new ArrayList<>();
        items.addAll(tncSupply.getTncSupplyItems().stream().map(TNCSupplyItemDTO::new).collect(Collectors.toList()));
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

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
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

    public List<TNCSupplyItemDTO> getItems() {
        return items;
    }

    public void setItems(List<TNCSupplyItemDTO> items) {
        this.items = items;
    }

    public class TNCSupplyItemDTO{
        private String tncName;
        private int count;
        private String units;
        private int tncId;

        public TNCSupplyItemDTO(TNCSupplyItem tncSupplyItem){
            this.tncName = tncSupplyItem.getTnc().getNameInner();
            this.count = tncSupplyItem.getCount();
            this.units = tncSupplyItem.getTnc().getUnitsTo() == null || tncSupplyItem.getTnc().getUnitsTo().equals("") ? "-" : tncSupplyItem.getTnc().getUnitsTo();
            this.tncId = tncSupplyItem.getTnc().getId();
        }

        public String getTncName() {
            return tncName;
        }

        public void setTncName(String tncName) {
            this.tncName = tncName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getUnits() {
            return units;
        }

        public void setUnits(String units) {
            this.units = units;
        }

        public int getTncId() {
            return tncId;
        }

        public void setTncId(int tncId) {
            this.tncId = tncId;
        }
    }

}
