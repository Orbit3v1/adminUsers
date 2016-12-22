package com.app.validator;

import com.app.data.entity.Order;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named("orderValidator")
@Scope("request")
public class OrderValidator extends AbstractValidator<Order> {

    protected boolean edit;

    @Override
    public boolean validate(Order order, Object... args) {
        this.entity = order;
        edit = args.length > 0 ? (Boolean) args[0] : false;
        return isValidName() & isValidOrderItems();
    }

    protected boolean isValidName() {
        boolean valid = true;
        if (entity.getName() == null || entity.getName().equals("")) {
            valid = false;
            addMessage.setMessage("mainForm:panel:name", "error.notNull", FacesMessage.SEVERITY_ERROR);
        } else if (getOrderWithSameName().size() != 0) {
            valid = false;
            addMessage.setMessage("mainForm:panel:name", "orderScreen.error.nameDuplicate", FacesMessage.SEVERITY_ERROR);

        }
        return valid;
    }

    protected List<Order> getOrderWithSameName() {
        Query query = em.createQuery("select r from Order r where r.name = :name and r.id != :id")
                .setParameter("name", entity.getName())
                .setParameter("id", edit ? entity.getId() : -1);
        return query.getResultList();
    }

    protected boolean isValidOrderItems() {
        boolean valid = true;
        if (entity.getOrderItems() == null || entity.getOrderItems().size() == 0) {
            valid = false;
            addMessage.setMessage("mainForm:panel:orderItems", "error.emptyItems", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

}
