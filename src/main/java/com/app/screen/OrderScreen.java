package com.app.screen;

import com.app.entity.Nomenclature;
import com.app.entity.Order;
import com.app.entity.OrderItem;
import com.app.entity.Person;
import com.app.utils.AppUtil;
import com.app.utils.Security;
import org.richfaces.model.Filter;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import com.app.utils.SessionUtil;
import com.app.validator.Validator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Named("orderScreen")
@Scope("view")
public class OrderScreen extends EntityScreen<Order>  {

    @Inject
    Validator<Order> validator;

    private List<Person> developers;

    @PostConstruct
    public void init() {
        logger.info("init");
        initEntity();

        Query query = em.createQuery("select r from Person r order by r.lastName, r.firstName");
        developers = query.getResultList();
    }

    @Override
    protected String getScreenName() {
        return "orderScreen";
    }

    @Override
    @Transactional
    public void initEntity() {
        String id = getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            entity = em.find(Order.class, AppUtil.toInteger(id));
            entity.getOrderItems().size();
            edit = true;
        } else {
            entity = new Order();
            entity.setResponsible(Security.getCurrentUser());
            entity.setStart(new Date());
            entity.setOrderItems(new ArrayList<>());
        }

    }

    @Override
    @Transactional
    public void initEntity(Order entity) {
        this.entity = em.find(Order.class, entity.getId());
        this.entity.getOrderItems().size();
    }

    @Override
    public boolean save() {
        if (validate()) {
            try {
                saveData();

                String bundleKey = edit ? "orderScreen.success.edit" : "orderScreen.success.save";
                SessionUtil.setMessage("mainForm:panel", bundleKey, FacesMessage.SEVERITY_INFO);
                edit = true;
                return true;
            } catch (OptimisticLockException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
                SessionUtil.setMessage("mainForm:panel", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
                SessionUtil.setMessage("mainForm:panel", "error.exception", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            SessionUtil.setMessage("mainForm:panel", "orderScreen.error.title", FacesMessage.SEVERITY_ERROR);
        }
        return false;
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

    @Transactional
    public String delete() {
        logger.info("delete");
        em.remove(em.merge(entity));
        return exit();
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
    public String exit() {
        SessionUtil.removeSessionVariable("Order" + entity.getId());
        return super.exit();
    }

    private boolean validate() {
        return validator.validate(entity, edit);
    }

    public List<Person> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Person> developers) {
        this.developers = developers;
    }

}
