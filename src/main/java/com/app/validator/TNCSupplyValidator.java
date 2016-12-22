package com.app.validator;

import com.app.data.entity.Order;
import com.app.data.entity.TNCRequest;
import com.app.data.entity.TNCSupply;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named("TNCSupplyValidator")
@Scope("request")
public class TNCSupplyValidator extends AbstractValidator<TNCSupply>  {
    @Override
    public boolean validate(TNCSupply entity, Object... args) {
        this.entity = entity;
        return isValidName() & isValidItems();
    }

    protected boolean isValidName() {
        boolean valid = true;
        if (entity.getName() == null || entity.getName().equals("")) {
            valid = false;
            addMessage.setMessage("mainForm:panel:name", "error.notNull", FacesMessage.SEVERITY_ERROR);
        } else if (getEntityWithSameName().size() != 0) {
            valid = false;
            addMessage.setMessage("mainForm:panel:name", "error.nameDuplicate", FacesMessage.SEVERITY_ERROR);

        }
        return valid;
    }

    protected List<Order> getEntityWithSameName() {
        Query query = em.createQuery("select r from TNCSupply r where r.name = :name and r.id != :id")
                .setParameter("name", entity.getName())
                .setParameter("id", entity.getId());
        return query.getResultList();
    }

    protected boolean isValidItems() {
        boolean valid = true;
        if (entity.getTncSupplyItems() == null || entity.getTncSupplyItems().size() == 0) {
            valid = false;
            addMessage.setMessage("mainForm:panel:items", "error.emptyItems", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }
}
