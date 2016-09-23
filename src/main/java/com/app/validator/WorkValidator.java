package com.app.validator;

import com.app.data.entity.Role;
import com.app.data.entity.Work;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named("workValidator")
@Scope("request")
public class WorkValidator extends AbstractValidator<Work> {

    @Override
    public boolean validate(Work entity, Object... args) {
        this.entity = entity;
        return isValidName();
    }

    protected boolean isValidName() {
        boolean valid = true;
        if (getEntityWithSameName().size() != 0) {
            valid = false;
            addMessage.setMessage("mainForm:name", "workList.error.nameDuplicate", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected List<Role> getEntityWithSameName() {
        Query query = em.createQuery("select r from Work r where r.name = :name and r.id != :id")
                .setParameter("name", entity.getName())
                .setParameter("id", entity.getId());
        return query.getResultList();
    }
}
