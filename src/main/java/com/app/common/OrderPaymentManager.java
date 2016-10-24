package com.app.common;


import com.app.data.entity.Component;
import com.app.data.entity.Nomenclature;
import com.app.data.entity.Order;
import com.app.data.entity.Payment;
import org.apache.log4j.Logger;

import java.util.Date;

public class OrderPaymentManager {
    
    private Order order;
    private Logger logger = Logger.getLogger(getClass());

    private Payment tmpPayment;
    private Payment originalPayment;
    private boolean edit;

    public OrderPaymentManager(Order order) {
        this.order = order;
        tmpPayment = new Payment();
    }

    public void add(){
        logger.info("add component");
        tmpPayment = new Payment();
        tmpPayment.setDate(new Date());
        edit = false;
    }

    public void edit(Payment payment){
        logger.info("edit component");
        originalPayment = payment;
        tmpPayment = new Payment();
        tmpPayment.copyData(payment);
        edit = true;
    }

    public void delete(Payment payment){
        order.getPayments().remove(payment);
    }

    public void save(){
        if(edit){
            logger.info("save existing component");
            originalPayment.copyData(tmpPayment);
        } else {
            logger.info("save new component");
            order.getPayments().add(tmpPayment);
            edit = true;
        }
    }

    public Payment getTmpPayment() {
        return tmpPayment;
    }

    public void setTmpPayment(Payment tmpPayment) {
        this.tmpPayment = tmpPayment;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
}
