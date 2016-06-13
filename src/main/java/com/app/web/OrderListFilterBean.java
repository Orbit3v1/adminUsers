package com.app.web;

import com.app.dictionary.OrderItemState;
import com.app.entity.OrderListFilter;
import com.app.utils.Security;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.Map;

@Named("orderListFilterBean")
@Scope("session")
public class OrderListFilterBean {

    private OrderListFilter filter;
    private Map<String, Boolean> userPA;

    @PostConstruct
    public void init() {
        userPA = Security.getUserPrivilegeAction("orderList");
        if(filter == null){
            filter = new OrderListFilter();
            filter.setState(getDefaultState());
        }
    }

    public OrderItemState getDefaultState(){
        return Security.hasAccess(userPA, "accessInWork") ? OrderItemState.IN_WORK : OrderItemState.ALL;
    }

    public OrderListFilter clear(){
        filter = new OrderListFilter();
        filter.setState(getDefaultState());
        return filter;
    }

    public OrderListFilter getFilter() {
        return filter;
    }

    public void setFilter(OrderListFilter filter) {
        this.filter = filter;
    }
}
