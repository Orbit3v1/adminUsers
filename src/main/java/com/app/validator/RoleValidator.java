package com.app.validator;

import com.app.entity.Role;
import org.springframework.context.annotation.Scope;
import com.app.utils.SessionUtil;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

@Named("roleValidator")
@Scope("request")
public class RoleValidator extends AbstractValidator<Role>{

    private boolean edit;

    public RoleValidator() {
    }

    @Override
    public boolean validate(Role role, Object ... args) {
        this.entity = role;
        edit = args.length > 0 ? (Boolean) args[0] : false;
        return isValidId() & isValidName();
    }

    private boolean isValidId() {
        boolean valid = true;
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9_-]+");
        String errorMessage = "";
        if (entity.getId().equals("")) {
            valid = false;
            errorMessage = "error.notNull";
        } else if (pattern.matcher(entity.getId()).find()) {
            valid = false;
            errorMessage = "roleScreen.error.idPattern";
        } else if (!edit) {
            Query query = em.createQuery("select r from Role r where r.id = :id")
                    .setParameter("id", entity.getId());
            if (query.getResultList().size() != 0) {
                valid = false;
                errorMessage = "roleScreen.error.idDuplicate";
            }
        }
        if (!valid) {
            addMessage.setMessage("mainForm:id", errorMessage, FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    private boolean isValidName() {
        boolean valid = true;
        if (entity.getName().equals("")) {
            valid = false;
            addMessage.setMessage("mainForm:name", "error.notNull", FacesMessage.SEVERITY_ERROR);
        } else {
            Query query = em.createQuery("select r from Role r where r.name = :name and r.id != :id")
                    .setParameter("name", entity.getName())
                    .setParameter("id", edit ? entity.getId() : "");
            if (query.getResultList().size() != 0) {
                valid = false;
                addMessage.setMessage("mainForm:name", "roleScreen.error.nameDuplicate", FacesMessage.SEVERITY_ERROR);
            }
        }
        return valid;
    }

}
