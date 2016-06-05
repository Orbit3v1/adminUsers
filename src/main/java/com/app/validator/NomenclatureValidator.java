package com.app.validator;

import com.app.entity.Nomenclature;
import com.app.entity.Person;
import com.app.utils.AppUtil;
import org.springframework.context.annotation.Scope;
import com.app.utils.SessionUtil;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.ResourceBundle;

@Named("nomenclatureValidator")
@Scope("request")
public class NomenclatureValidator extends AbstractValidator<Nomenclature> {

    protected String gib;

    @Override
    public boolean validate(Nomenclature nomenclature, Object... args) {
        this.entity = nomenclature;
        gib = args.length > 1 ? (String) args[1] : "";
        return isValidName() & isValidGib();
    }

    protected boolean isValidName() {
        boolean valid = true;
        if (entity.getName() == null || entity.getName().equals("")) {
            valid = false;
            addMessage.setMessage("mainForm:name", "error.notNull", FacesMessage.SEVERITY_ERROR);
        } else if (getNomenclaturesWithSameName().size() != 0) {
            valid = false;
            addMessage.setMessage("mainForm:name", "nomenclatureScreen.error.nameDuplicate", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected List<Nomenclature> getNomenclaturesWithSameName() {
        Query query = em.createQuery("select p from Nomenclature p where p.name = :name and p.id != :id")
                .setParameter("name", entity.getName())
                .setParameter("id", entity.getId());
        return query.getResultList();
    }

    protected boolean isValidGib() {
        boolean valid = true;
        if (gib != null && !gib.equals("") && !AppUtil.isNumeric(gib)) {
            valid = false;
            addMessage.setMessage("mainForm:gib", "error.notNumber", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }
}
