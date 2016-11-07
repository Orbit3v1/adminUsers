package com.app.common;


import com.app.data.entity.Component;
import com.app.data.entity.Nomenclature;
import com.app.data.entity.Order;
import com.app.data.entity.Payment;
import com.app.utils.OrderUtil;
import com.app.web.Loggable;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
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

    @Loggable
    public void add(){
        tmpPayment = new Payment();
        tmpPayment.setDate(new Date());
        edit = false;
    }

    @Loggable
    public void edit(Payment payment){
        originalPayment = payment;
        tmpPayment = new Payment();
        tmpPayment.copyData(payment);
        edit = true;
    }

    @Loggable
    public void delete(Payment payment){
        order.getPayments().remove(payment);
        OrderUtil.reCalculatePaid(order);
    }

    @Loggable
    public void save(){
        if(edit){
            logger.info("save existing component");
            originalPayment.copyData(tmpPayment);
        } else {
            logger.info("save new component");
            order.getPayments().add(tmpPayment);
            edit = true;
        }
        OrderUtil.reCalculatePaid(order);
        closeDialog();
    }

    @Loggable
    private void closeDialog(){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('popupPayments').hide();");
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
