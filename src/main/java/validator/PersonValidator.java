package validator;

import entity.Person;
import org.springframework.context.annotation.Scope;
import utils.SessionUtil;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ResourceBundle;

@Named("personValidator")
@Scope("request")
public class PersonValidator implements Validator{

    @Inject
    private EntityManagerFactory entityManagerFactory;
    @Inject
    ResourceBundle resourceBundle;

    private Person person;
    private boolean edit;

    public PersonValidator() {
    }

    @Override
    public boolean validate() {
        return isValidEmail() & isValidLogin() & isValidPassword();
    }

    private boolean isValidEmail(){
        boolean valid = true;
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select p from Person p where p.email = :email and p.id != :id")
                .setParameter("email", person.getEmail())
                .setParameter("id", person.getId());
        if (query.getResultList().size() != 0) {
            valid = false;
            SessionUtil.setMessage("mainForm:email", "personScreen.error.emailDuplicate", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    private boolean isValidLogin(){
        boolean valid = true;
        if(person.getLogin().equals("")){
            valid = false;
            SessionUtil.setMessage("mainForm:login", "error.notNull", FacesMessage.SEVERITY_ERROR);
        } else {
            EntityManager em = entityManagerFactory.createEntityManager();
            Query query = em.createQuery("select p from Person p where p.email = :login and p.id != :id")
                    .setParameter("login", person.getLogin())
                    .setParameter("id", person.getId());
            if (query.getResultList().size() != 0) {
                valid = false;
                SessionUtil.setMessage("mainForm:login", "personScreen.error.loginDuplicate", FacesMessage.SEVERITY_ERROR);
            }
        }
        return valid;
    }

    private boolean isValidPassword(){
        boolean valid = true;
        if(person.getPassword().equals("") && !edit){
            valid = false;
            SessionUtil.setMessage("mainForm:password", "error.notNull", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
