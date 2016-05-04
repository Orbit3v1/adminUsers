package validator;

import entity.Person;
import org.springframework.context.annotation.Scope;

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
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "emailDuplicate", resourceBundle.getString("personScreen.error.emailDuplicate"));
            FacesContext.getCurrentInstance().addMessage("mainForm:email", facesMessage);
        }
        return valid;
    }

    private boolean isValidLogin(){
        boolean valid = true;
        if(person.getLogin().equals("")){
            valid = false;
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "notNull", resourceBundle.getString("error.notNull"));
            FacesContext.getCurrentInstance().addMessage("mainForm:login", facesMessage);
        } else {
            EntityManager em = entityManagerFactory.createEntityManager();
            Query query = em.createQuery("select p from Person p where p.email = :login and p.id != :id")
                    .setParameter("login", person.getLogin())
                    .setParameter("id", person.getId());
            if (query.getResultList().size() != 0) {
                valid = false;
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "loginDuplicate", resourceBundle.getString("personScreen.error.loginDuplicate"));
                FacesContext.getCurrentInstance().addMessage("mainForm:login", facesMessage);
            }
        }
        return valid;
    }

    private boolean isValidPassword(){
        boolean valid = true;
        if(person.getPassword().equals("") && !edit){
            valid = false;
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "notNull", resourceBundle.getString("error.notNull"));
            FacesContext.getCurrentInstance().addMessage("mainForm:password", facesMessage);
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
