package com.app.web.screen;

import com.app.data.entity.Nomenclature;
import com.app.data.entity.Order;
import com.app.data.entity.OrderItem;
import com.app.data.entity.Person;
import com.app.utils.AppUtil;
import com.app.utils.EntityUtil;
import com.app.utils.SessionUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;

@Named("orderItemScreen")
@Scope("view")
public class OrderItemScreen extends EntityScreen<OrderItem> {

    private String count;
    private List<Person> developers;
    private OrderItem originalOrderItem;
    private Order source;

    @PostConstruct
    public void init() {
        logger.info("init");
        initSource();
        initEntity();

        developers = EntityUtil.getDevelopers(em);
    }

    @Override
    protected String getScreenName() {
        return "orderItemScreen";
    }

    public void initSource(){
        String id = SessionUtil.getParameter("orderId");
        if(id != null && AppUtil.isNumeric(id)){
            source = (Order) SessionUtil.getSessionVariable("Order" + id);
        }
    }

    public void initEntity() {
        String hash = SessionUtil.getParameter("hash");
        if(hash != null && AppUtil.isNumeric(hash)){
            originalOrderItem = getOrderItemByHash(source, AppUtil.toInteger(hash));
            count = AppUtil.toString(originalOrderItem.getCount());
            entity = new OrderItem();
            entity.copyForm(originalOrderItem);
            edit = true;
        } else {
            count = null;

            originalOrderItem = new OrderItem();
            entity = new OrderItem();
            entity.setOrder(source);
            entity.setEndPlan(source.getEndPlan());
        }
    }

    private OrderItem getOrderItemByHash(Order order, Integer orderItemHash){
        OrderItem orderItem = null;
        for(OrderItem oi : order.getOrderItems()){
            if(oi.hashCode() == orderItemHash){
                orderItem = oi;
                break;
            }
        }
        return orderItem;
    }


    public void shareOrderItem(){
        SessionUtil.addSessionVariable("EntityNomenclature", entity);
    }

    @Override
    protected void clearCash(){
        SessionUtil.removeSessionVariable("EntityNomenclature");
    }

    @Override
    protected void save() {
        entity.setCount(AppUtil.toInteger(count));
        updateOrderItems();
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
            originalOrderItem.copyForm(entity);
            source.getOrderItems().add(originalOrderItem);
            edit = true;
        }
    }

    @Override
    public void delete(){
        logger.info("delete");
        source.getOrderItems().remove(originalOrderItem);
        executeJS("save();");
        exit();
    }

    public void cancelEndActual(){
        logger.info("cancel end actual");
        entity.setEndActual(null);
    }

    private String getItemName(Order order){
        if(order.getOrderItems() == null || order.getOrderItems().size() == 0){
            return "1";
        }
        OrderItem itemWithMaxName = Collections.max(order.getOrderItems(), (i1, i2) -> (AppUtil.toInteger(i1.getName()).compareTo(AppUtil.toInteger(i2.getName()))));
        String maxName = itemWithMaxName.getName();
        Integer max = AppUtil.toInteger(maxName);
        return AppUtil.toString(max + 1);
    }

    @Override
    protected boolean validate() {
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
