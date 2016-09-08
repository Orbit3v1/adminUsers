package com.app.web.screen;

import com.app.data.entity.Nomenclature;
import com.app.data.entity.Order;
import com.app.data.entity.OrderItem;
import com.app.data.entity.Person;
import com.app.utils.AppUtil;
import com.app.utils.EntityUtil;
import com.app.utils.Security;
import org.richfaces.model.Filter;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import com.app.utils.SessionUtil;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named("orderScreen")
@Scope("view")
public class OrderScreen extends EntityScreen<Order>  {

    private List<Person> developers;

    @PostConstruct
    public void init() {
        logger.info("init");
        initEntity();

        developers = EntityUtil.getDevelopers(em);
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
            edit = true;
        } else {
            entity = new Order();
            entity.setResponsible(Security.getCurrentUser());
            entity.setStart(new Date());
        }

    }

    @Override
    public void save() {
        saveData();
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

    @Transactional
    public void refresh() {
        logger.info("refresh");
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

    public void shareOrder(){
        SessionUtil.addSessionVariable("Order" + entity.getId(), entity);
    }

    @Override
    public void exit() {
        SessionUtil.removeSessionVariable("Order" + entity.getId());
        super.exit();
    }


    public List<Person> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Person> developers) {
        this.developers = developers;
    }

}
