package com.app.screen;

import com.app.entity.Order;
import com.app.entity.OrderItem;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named("orderItemScreen")
@Scope("session")
public class OrderItemScreen extends EntityScreen<OrderItem> {

    private String count;

    @Override
    protected String getScreenName() {
        return "orderItemScreen";
    }

    @Override
    protected boolean save() {
        return false;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
