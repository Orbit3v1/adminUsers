package com.app.utils;

import com.app.data.entity.Order;
import com.app.data.entity.Payment;

import java.math.BigDecimal;


public class OrderUtil {
    public static BigDecimal getPaid(Order entity){
        BigDecimal paid = null;
        BigDecimal price = entity.getPrice();
        if(price != null && price.compareTo(BigDecimal.ZERO) == 1) {
            BigDecimal paidAmount = BigDecimal.ZERO;
            if(entity.getPayments() != null){
                paidAmount = entity.getPayments().stream().filter(x -> x.getAmount() != null).map(Payment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            paid = paidAmount.multiply(new BigDecimal("100")).divide(price, BigDecimal.ROUND_DOWN);
        }
        return paid;
    }

    public static void reCalculatePaid(Order entity){
        entity.setPaid(getPaid(entity));
    }
}
