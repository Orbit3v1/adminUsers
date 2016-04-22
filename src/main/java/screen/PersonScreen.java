package screen;

import entity.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Named("personScreen")
@Scope("session")
public class PersonScreen {

    @Inject
    private EntityManagerFactory entityManagerFactory;
    @Inject
    ResourceBundle resourceBundle;

    private Person person;
    private boolean isValid = true;
    private List<Role> roleSourceList;

    @PostConstruct
    public void init() {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select r from Role r order by r.name");
        roleSourceList = query.getResultList();
    }

    public String editPerson(Person person) {
        this.person = person;
        return "editPerson";
    }

    public String newPerson() {
        person = new Person();
        return "editPerson";
    }

    public String exit() {
        return "personList";
    }

    public String saveOnly() {
        save();
        return "";
    }

    public String saveAndExit() {
        return save() ? exit() : "";
    }

    public boolean save(){
        if (isValid) {
            replaceEmptyToNull();
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            person = em.merge(person);
            em.getTransaction().commit();
            em.close();
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "errorTitle", resourceBundle.getString("personScreen.error.title"));
            FacesContext.getCurrentInstance().addMessage("mainForm:panel", facesMessage);
            isValid = true;
            return false;
        }
        return true;
    }

    private void replaceEmptyToNull(){
        if(person.getEmail().equals("")){
            person.setEmail(null);
        }
    }

    public void validateEmail(FacesContext context, UIComponent toValidate, Object value) {

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select p from Person p where p.email = :email and p.id != :id")
                .setParameter("email", value)
                .setParameter("id", person.getId());
        List<Person> persons = query.getResultList();
        if (persons.size() != 0) {
            isValid = false;
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "emailDuplicate", resourceBundle.getString("personScreen.error.emailDuplicate"));
            context.addMessage(toValidate.getClientId(context), facesMessage);
        }
    }

    public void validateName(FacesContext context, UIComponent toValidate, Object value) {
        if(value.equals("1")){
            isValid = false;
            context.addMessage(toValidate.getClientId(context), new FacesMessage("Wrong name"));
        }
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Role> getRoleSourceList() {
        return roleSourceList;
    }

    public void setRoleSourceList(List<Role> roleSourceList) {
        this.roleSourceList = roleSourceList;
    }
}
