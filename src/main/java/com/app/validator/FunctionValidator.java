package com.app.validator;

import com.app.data.entity.Function;
import com.app.data.entity.Role;
import com.app.utils.JSEngine;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named("functionValidator")
@Scope("request")
public class FunctionValidator extends AbstractValidator<Function> {

    @Inject
    JSEngine jsEngine;

    @Override
    public boolean validate(Function entity, Object... args) {
        this.entity = entity;
        return isValidCode() & isValidName();
    }

    private boolean isValidCode() {
        boolean valid = true;
        String err = jsEngine.validate(entity.getCode());
        if (err != null) {
            valid = false;
            addMessage.setMessage("mainForm:code", err, FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected boolean isValidName() {
        boolean valid = true;
        if (getEntityWithSameName().size() != 0) {
            valid = false;
            addMessage.setMessage("mainForm:name", "functionList.error.nameDuplicate", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected List<Role> getEntityWithSameName() {
        Query query = em.createQuery("select r from Function r where r.name = :name and r.id != :id")
                .setParameter("name", entity.getName())
                .setParameter("id", entity.getId());
        return query.getResultList();
    }

}
