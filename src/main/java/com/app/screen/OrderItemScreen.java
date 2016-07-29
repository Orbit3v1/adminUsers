package com.app.screen;

import com.app.entity.Nomenclature;
import com.app.entity.Order;
import com.app.entity.OrderItem;
import com.app.entity.Person;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;
import com.app.validator.Validator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Named("orderItemScreen")
@Scope("view")
public class OrderItemScreen extends EntityScreen<OrderItem> {

    private String count;
    private List<Person> developers;
    private OrderItem originalOrderItem;
    private Order source;

    @Inject
    private ApplicationContext applicationContext;
    @Inject
    Validator<OrderItem> validator;

    @PostConstruct
    public void init() {
        logger.info("init");
        initSource();
        initEntity();

        Query query = em.createQuery("select r from Person r order by r.lastName, r.firstName");
        developers = query.getResultList();
    }

    @Override
    protected String getScreenName() {
        return "orderItemScreen";
    }

    public void initSource(){
        String id = getParameter("orderId");
        if(id != null && AppUtil.isNumeric(id)){
            source = (Order) SessionUtil.getSessionVariable("Order" + id);
        }
    }

    @Override
    public void initEntity() {
        String id = getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            entity = getOrderItemById(source, AppUtil.toInteger(id));
            originalOrderItem = entity;
            count = AppUtil.toString(entity.getCount());
            entity = new OrderItem();
            entity.copyForm(originalOrderItem);
            edit = true;
        } else {
            count = null;

            entity = new OrderItem();
            entity.setOrder(source);
            entity.setEndPlan(source.getEndPlan());
        }
    }

    private OrderItem getOrderItemById(Order order, Integer orderItemId){
        OrderItem orderItem = null;
        for(OrderItem oi : order.getOrderItems()){
            if(oi.getId().equals(orderItemId)){
                orderItem = oi;
                break;
            }
        }
        return orderItem;
    }

    @Override
    public void initEntity(OrderItem entity) {


    }

    public void shareOrderItem(){
        SessionUtil.addSessionVariable("OrderItem", entity);
    }

    @Override
    public String exit() {
        SessionUtil.removeSessionVariable("OrderItem" + entity.getId());
        return super.exit();
    }

    @Override
    protected boolean save() {
        if (validate()) {
            entity.setCount(AppUtil.toInteger(count));
            updateOrderItems();
            return true;
        } else {
            SessionUtil.setMessage("mainForm:panel", "orderItemScreen.error.title", FacesMessage.SEVERITY_ERROR);
        }
        return false;
    }

    @Transactional
    public void refresh(){
        logger.info("refresh");
        Nomenclature nomenclature = em.find(Nomenclature.class, entity.getNomenclature().getId());
        entity.setNomenclature(nomenclature);
    }

    private void updateOrderItems() {
        logger.info("updateOrderItems");
        if (edit) {
            originalOrderItem.copyForm(entity);
        } else {
            entity.setName(getItemName(source));

            source.getOrderItems().add(entity);
            editEntity(entity);
        }
    }



    public String delete(){
        logger.info("delete");
        OrderScreen orderScreen = applicationContext.getBean(OrderScreen.class);
        source.getOrderItems().remove(originalOrderItem);
        return exit();
    }

    public void cancelEndActual(){
        logger.info("cancel end actual");
        entity.setEndActual(null);
    }

    private String getItemName(Order order){
        if(order.getOrderItems() == null || order.getOrderItems().size() == 0){
            return "1";
        }
        OrderItem itemWithMaxName = Collections.max(order.getOrderItems(), (i1, i2) -> (i1.getName().compareTo(i2.getName())));
        String maxName = itemWithMaxName.getName();
        Integer max = AppUtil.toInteger(maxName);
        return AppUtil.toString(max + 1);
    }

    private boolean validate() {
        return validator.validate(entity, edit, count);
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Person> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Person> developers) {
        this.developers = developers;
    }

}
