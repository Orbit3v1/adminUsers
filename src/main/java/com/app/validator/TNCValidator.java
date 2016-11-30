package com.app.validator;

import com.app.data.entity.Role;
import com.app.data.entity.TNC;
import com.app.data.entity.Work;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named("TNCValidator")
@Scope("request")
public class TNCValidator extends AbstractValidator<TNC> {

    @Override
    public boolean validate(TNC entity, Object... args) {
        this.entity = entity;
        return isValidNameInner();
    }

    protected boolean isValidName() {
        boolean valid = true;
        if (entity.getName() == null || entity.getName().equals("")) {
            valid = false;
            addMessage.setMessage("mainForm:name", "error.notNull", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected boolean isValidNameInner() {
        boolean valid = true;
        if (entity.getNameInner() == null || entity.getNameInner().equals("")) {
            valid = false;
            addMessage.setMessage("mainForm:nameInner", "error.notNull", FacesMessage.SEVERITY_ERROR);
        } else if (getTNCWithSameNameInner().size() != 0) {
            valid = false;
            addMessage.setMessage("mainForm:nameInner", "TNCList.error.nameInnerDuplicate", FacesMessage.SEVERITY_ERROR);

        }
        return valid;
    }

    protected List<TNC> getTNCWithSameNameInner() {
        Query query = em.createQuery("select r from TNC r where r.nameInner = :nameInner and r.id != :id")
                .setParameter("nameInner", entity.getNameInner())
                .setParameter("id", entity.getId());
        return query.getResultList();
    }

}
