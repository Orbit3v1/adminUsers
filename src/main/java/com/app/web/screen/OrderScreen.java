package com.app.web.screen;

import com.app.common.OrderPaymentManager;
import com.app.data.entity.*;
import com.app.security.Security;
import com.app.utils.*;
import com.app.web.Loggable;
import org.richfaces.model.Filter;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Named("orderScreen")
@Scope("view")
public class OrderScreen extends EntityScreen<Order>  {

    private List<Person> developers;
    private OrderPaymentManager OPM;

    @PostConstruct
    public void init() {
        logger.info("init");
        initEntity();

        developers = entityUtil.getDevelopers();
        OPM = new OrderPaymentManager(entity);
    }

    @Override
    protected String getScreenName() {
        return "orderScreen";
    }

    @Transactional
    public void initEntity() {
        String id = SessionUtil.getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            entity = em.find(Order.class, AppUtil.toInteger(id));
            entity.getOrderItems().size();
            entity.getPayments().size();
            edit = true;
        } else {
            entity = new Order();
            entity.setResponsible(Security.getCurrentUser());
            entity.setStart(new Date());
        }
        OrderUtil.reCalculatePaid(entity);
    }

    @Override
    public void save() {
        OrderUtil.reCalculatePaid(entity);
        saveData();
        OPM = new OrderPaymentManager(entity);
    }

    @Transactional
    private void saveData() {
        entity = em.merge(entity);
    }

    public void setEndActual(OrderItem orderItem) {
        logger.info("set actual end. OrderItem.id = " + orderItem.getId());
        Date date = new Date();
        orderItem.setEndActual(date);
    }

    @Loggable
    @Transactional
    public void refresh() {
        for (OrderItem orderItem : entity.getOrderItems()) {
            orderItem.setNomenclature(em.find(Nomenclature.class, orderItem.getNomenclature().getId()));
        }
    }

    public void delete(OrderItem orderItem) {
        entity.getOrderItems().remove(orderItem);
    }

    public Filter<?> getOrderItemAccess() {
        return new Filter<OrderItem>() {
            public boolean accept(OrderItem item) {
                boolean result = false;
                if(item.getEndActual() == null){
                    result = Security.hasAccess(getUserPA(), "accessInWork");
                } else {
                    result = Security.hasAccess(getUserPA(), "accessFinished");
                }
                return result;
            }
        };
    }

    @Loggable
    public void onPriceChange(ValueChangeEvent e){
        entity.setPrice((BigDecimal) e.getNewValue());
        OrderUtil.reCalculatePaid(entity);
    }

    public void shareOrder(){
        SessionUtil.addSessionVariable("Order" + entity.getId(), entity);
    }

    @Override
    protected void clearCash(){
        SessionUtil.removeSessionVariable("Order" + entity.getId());
    }

    public List<Person> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Person> developers) {
        this.developers = developers;
    }

    public OrderPaymentManager getOPM() {
        return OPM;
    }

    public void setOPM(OrderPaymentManager OPM) {
        this.OPM = OPM;
    }
}
