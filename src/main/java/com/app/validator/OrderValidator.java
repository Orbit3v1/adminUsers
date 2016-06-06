package com.app.validator;

import com.app.entity.Nomenclature;
import com.app.entity.Order;
import org.springframework.context.annotation.Scope;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.ResourceBundle;

@Named("orderValidator")
@Scope("request")
public class OrderValidator extends AbstractValidator<Order> {

    protected boolean edit;
    protected String count;

    @Override
    public boolean validate(Order order, Object... args) {
        this.entity = order;
        edit = args.length > 0 ? (Boolean) args[0] : false;
        count = args.length > 1 ? (String) args[1] : "";
        return isValidName() & isValidNomenclature();
    }

    protected boolean isValidName() {
        boolean valid = true;
        if (entity.getName() == null || entity.getName().equals("")) {
            valid = false;
            addMessage.setMessage("mainForm:name", "error.notNull", FacesMessage.SEVERITY_ERROR);
        } else if (getOrderWithSameName().size() != 0) {
            valid = false;
            addMessage.setMessage("mainForm:name", "orderScreen.error.nameDuplicate", FacesMessage.SEVERITY_ERROR);

        }
        return valid;
    }

    protected List<Order> getOrderWithSameName() {
        Query query = em.createQuery("select r from Order r where r.name = :name and r.id != :id")
                .setParameter("name", entity.getName())
                .setParameter("id", edit ? entity.getId() : -1);
        return query.getResultList();
    }

    protected boolean isValidNomenclature() {
        boolean valid = true;
//        if (entity.getNomenclature() == null) {
//            valid = false;
//            addMessage.setMessage("mainForm:nomenclature", "error.notNull", FacesMessage.SEVERITY_ERROR);
//        }
        return valid;
    }
//
//    protected boolean isValidCount() {
//        boolean valid = true;
//        if (count == null || count.equals("")) {
//            valid = false;
//            addMessage.setMessage("mainForm:count", "error.notNull", FacesMessage.SEVERITY_ERROR);
//        } else if (!AppUtil.isNumeric(count)) {
//            valid = false;
//            addMessage.setMessage("mainForm:count", "error.notNumber", FacesMessage.SEVERITY_ERROR);
//        }
//        return valid;
//    }
}
