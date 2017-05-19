package com.app.data.entity.filter;

import com.app.data.dictionary.Sort;
import com.app.data.dictionary.TNCRequestSort;
import com.app.data.dictionary.TNCRequestState;
import com.app.data.dictionary.TNCSupplySort;
import com.app.data.entity.AbstractVersionedEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tncSupplyListFilter")
public class TNCSupplyListFilter extends AbstractVersionedEntity implements Filter<TNCSupplyListFilter> {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "provider")
    private String provider;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "tnc")
    private String tnc;

    @Column(name = "deliveryType")
    private String deliveryType;

    @Column(name = "startL")
    private Date startL;

    @Column(name = "startH")
    private Date startH;

    @Column(name = "endPlanL")
    private Date endPlanL;

    @Column(name = "endPlanH")
    private Date endPlanH;

    @Column(name = "endActualL")
    private Date endActualL;

    @Column(name = "endActualH")
    private Date endActualH;

    @Column(name = "paymentDateL")
    private Date paymentDateL;

    @Column(name = "paymentDateH")
    private Date paymentDateH;

    @Column(name = "sort")
    @Enumerated(EnumType.STRING)
    private TNCSupplySort sort;

    public void clear() {
        name = null;
        tnc = null;
        provider = null;
        accountNumber = null;
        startL = null;
        startH = null;
        endPlanL = null;
        endPlanH = null;
        endActualL = null;
        endActualH = null;
        paymentDateL = null;
        paymentDateH = null;
        sort = null;
    }

    public void copyFrom(TNCSupplyListFilter filter){
        name = filter.name;
        tnc = filter.tnc;
        provider = filter.provider;
        accountNumber = filter.accountNumber;
        startL = filter.startL;
        startH = filter.startH;
        endPlanL = filter.endPlanL;
        endPlanH = filter.endPlanH;
        endActualL = filter.endActualL;
        endActualH = filter.endActualH;
        paymentDateL = filter.paymentDateL;
        paymentDateH = filter.paymentDateH;
        sort = filter.sort;
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

    public String getTnc() {
        return tnc;
    }

    public void setTnc(String tnc) {
        this.tnc = tnc;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
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

    public Date getEndPlanL() {
        return endPlanL;
    }

    public void setEndPlanL(Date endPlanL) {
        this.endPlanL = endPlanL;
    }

    public Date getEndPlanH() {
        return endPlanH;
    }

    public void setEndPlanH(Date endPlanH) {
        this.endPlanH = endPlanH;
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

    public Date getPaymentDateL() {
        return paymentDateL;
    }

    public void setPaymentDateL(Date paymentDateL) {
        this.paymentDateL = paymentDateL;
    }

    public Date getPaymentDateH() {
        return paymentDateH;
    }

    public void setPaymentDateH(Date paymentDateH) {
        this.paymentDateH = paymentDateH;
    }

    @Override
    public TNCSupplySort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = (TNCSupplySort) sort;
    }
}
