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

@Named("orderItemScreen")
@Scope("session")
public class OrderItemScreen extends EntityScreen<OrderItem> {

    private String count;
    private List<Person> developers;
    private boolean closed;
    private boolean saved;
    private OrderItem originalOrderItem;

    @Inject
    private ApplicationContext applicationContext;
    @Inject
    Validator<OrderItem> validator;

    @PostConstruct
    public void init() {
        logger.info("init");
        initEntity();

        Query query = em.createQuery("select r from Person r order by r.lastName, r.firstName");
        developers = query.getResultList();
    }

    @Override
    protected String getScreenName() {
        return "orderItemScreen";
    }

    @Override
    public void initEntity() {
        closed = false;
        saved = false;
        count = null;
        entity = new OrderItem();
    }

    @Override
    public void initEntity(OrderItem entity) {
        closed = false;
        saved = false;
        count = AppUtil.toString(entity.getCount());
        originalOrderItem = entity;
        this.entity = new OrderItem();
        this.entity.copyForm(originalOrderItem);
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

    public String saveOnly() {
        if (save()) {
            saved = true;
            closed = false;
        }
        return "";
    }

    public String saveAndExit() {
        if (save()) {
            saved = true;
            closed = true;
        }
        return "";
    }

    public String close() {
        logger.info("close popUp");
        saved = true;
        closed = true;
        return "";
    }

    @Transactional
    public void refresh(){
        logger.info("refresh");
        Nomenclature nomenclature = em.find(Nomenclature.class, entity.getNomenclature().getId());
        entity.setNomenclature(nomenclature);
    }

    private void updateOrderItems() {
        logger.info("updateOrderItems");
        OrderScreen orderScreen = applicationContext.getBean(OrderScreen.class);
        Order source = orderScreen.getEntity();
        if (edit) {
            originalOrderItem.copyForm(entity);
        } else {
            entity.setName(getItemName(source));
            entity.setOrder(source);
           source.getOrderItems().add(entity);
            editEntity(entity);
        }
    }

    public String delete(){
        logger.info("delete");
        OrderScreen orderScreen = applicationContext.getBean(OrderScreen.class);
        Order source = orderScreen.getEntity();
        source.getOrderItems().remove(originalOrderItem);
        return close();
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

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
}
