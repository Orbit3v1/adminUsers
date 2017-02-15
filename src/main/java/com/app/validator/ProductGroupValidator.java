package com.app.validator;

import com.app.data.entity.Person;
import com.app.data.entity.Product;
import com.app.data.entity.ProductGroup;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named("productGroupValidator")
@Scope("request")
public class ProductGroupValidator extends AbstractValidator<ProductGroup>{
    @Override
    public boolean validate(ProductGroup entity, Object... args) {
        this.entity = entity;
        return isValidName();
    }

    protected boolean isValidName() {
        boolean valid = true;
        String errorMessage = "";
        if (entity.getName() == null || entity.getName().equals("")) {
            valid = false;
            errorMessage = "error.notNull";
        } else if (entity.getParent() == null && getEntityWithSameName().size() != 0) {
            valid = false;
            errorMessage = "error.nameDuplicate";
        }
        if (!valid) {
            addMessage.setMessage("mainForm:groupName", errorMessage, FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected List<ProductGroup> getEntityWithSameName() {
        Query query = em.createQuery("select p from ProductGroup p " +
                "where p.parent = null  " +
                "and p.id != :id " +
                "and p.name = :name" )
                .setParameter("name", entity.getName())
                .setParameter("id", entity.getId());
        return query.getResultList();
    }
}
