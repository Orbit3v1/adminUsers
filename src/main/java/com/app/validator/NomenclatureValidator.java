package com.app.validator;

import com.app.entity.Nomenclature;
import com.app.entity.Person;
import org.springframework.context.annotation.Scope;
import com.app.utils.SessionUtil;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ResourceBundle;

@Named("nomenclatureValidator")
@Scope("request")
public class NomenclatureValidator extends AbstractValidator<Nomenclature> {

    private boolean edit;

    @Override
    public boolean validate(Nomenclature nomenclature, Object... args) {
        this.entity = nomenclature;
        edit = args.length > 0 ? (Boolean) args[0] : false;
        return isValidName();
    }

    private boolean isValidName(){
        boolean valid = true;
        if(entity.getName().equals("")){
            valid = false;
            addMessage.setMessage("mainForm:name", "error.notNull", FacesMessage.SEVERITY_ERROR);
        } else {
            Query query = em.createQuery("select p from Nomenclature p where p.name = :name and p.id != :id")
                    .setParameter("name", entity.getName())
                    .setParameter("id", entity.getId());
            if (query.getResultList().size() != 0) {
                valid = false;
                addMessage.setMessage("mainForm:name", "nomenclatureScreen.error.nameDuplicate", FacesMessage.SEVERITY_ERROR);
            }
        }
        return valid;
    }
}
