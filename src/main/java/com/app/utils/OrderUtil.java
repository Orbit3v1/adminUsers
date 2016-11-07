package com.app.utils;

import com.app.data.entity.Order;
import com.app.data.entity.Payment;

import java.math.BigDecimal;


public class OrderUtil {
    public static BigDecimal getPaid(Order entity){
        BigDecimal paid = null;
        if(entity.getPrice() != null) {
            BigDecimal paidAmount = BigDecimal.ZERO;
            if(entity.getPayments() != null){
                paidAmount = entity.getPayments().stream().filter(x -> x.getAmount() != null).map(Payment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            paid = paidAmount.multiply(new BigDecimal("100")).divide(entity.getPrice(), BigDecimal.ROUND_DOWN);
        }
        return paid;
    }

    public static void reCalculatePaid(Order entity){
        entity.setPaid(getPaid(entity));
    }
}
