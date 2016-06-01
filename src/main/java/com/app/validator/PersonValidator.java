package com.app.validator;

import com.app.entity.Person;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named("personValidator")
@Scope("request")
public class PersonValidator extends AbstractValidator<Person> {

    protected boolean edit;

    public PersonValidator() {
    }

    @Override
    public boolean validate(Person entity, Object... args) {
        this.entity = entity;
        edit = args.length > 0 ? (Boolean) args[0] : false;

        return isValidEmail() & isValidLogin() & isValidPassword();
    }

    protected boolean isValidEmail() {
        boolean valid = true;
        if (getPersonsWithSameEmail().size() != 0) {
            valid = false;
            addMessage.setMessage("mainForm:email", "personScreen.error.emailDuplicate", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected List<Person> getPersonsWithSameEmail() {
        Query query = em.createQuery("select p from Person p where p.email = :email and p.id != :id")
                .setParameter("email", entity.getEmail())
                .setParameter("id", entity.getId());
        return query.getResultList();
    }


    protected boolean isValidLogin() {
        boolean valid = true;
        if (entity.getLogin() == null || entity.getLogin().equals("")) {
            valid = false;
            addMessage.setMessage("mainForm:login", "error.notNull", FacesMessage.SEVERITY_ERROR);
        } else if (getPersonsWithSameLogin().size() != 0) {
            valid = false;
            addMessage.setMessage("mainForm:login", "personScreen.error.loginDuplicate", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected List<Person> getPersonsWithSameLogin() {
        Query query = em.createQuery("select p from Person p where p.login = :login and p.id != :id")
                .setParameter("login", entity.getLogin())
                .setParameter("id", entity.getId());
        return query.getResultList();
    }


    protected boolean isValidPassword() {
        boolean valid = true;
        if ((entity.getPassword() == null || entity.getPassword().equals("")) && !edit) {
            valid = false;
            addMessage.setMessage("mainForm:password", "error.notNull", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

}
