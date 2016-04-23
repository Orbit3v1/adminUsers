package screen;

import entity.*;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ResourceBundle;

@Named("personScreen")
@Scope("session")
public class PersonScreen {

    @Inject
    private EntityManagerFactory entityManagerFactory;
    @Inject
    ResourceBundle resourceBundle;

    private Person person;
    private boolean valid = true;
    private boolean edit;
    private List<Role> roleSourceList;

    @PostConstruct
    public void init() {
        person = new Person();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select r from Role r order by r.name");
        roleSourceList = query.getResultList();
    }

    public String editPerson(Person person) {
        edit = true;
        this.person = person;
        return "editPerson";
    }

    public String newPerson() {

        return "editPerson";
    }

    public void cleanSession(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.removeAttribute("personScreen");
    }

    public String exit() {
        cleanSession();
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
        if (valid) {
            replaceEmptyToNull();
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            person = em.merge(person);
            em.getTransaction().commit();
            em.close();
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "errorTitle", resourceBundle.getString("personScreen.error.title"));
            FacesContext.getCurrentInstance().addMessage("mainForm:panel", facesMessage);
            valid = true;
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
            valid = false;
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "emailDuplicate", resourceBundle.getString("personScreen.error.emailDuplicate"));
            context.addMessage(toValidate.getClientId(context), facesMessage);
        }
    }

    public void validateName(FacesContext context, UIComponent toValidate, Object value) {
        if(value.equals("1")){
            valid = false;
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

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
