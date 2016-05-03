package screen;

import entity.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import utils.Security;
import utils.SessionUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
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
    private String oldPassword;
    private Map<String, Boolean> userPA;

    @PostConstruct
    public void init() {
        person = new Person();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select r from Role r order by r.name");
        roleSourceList = query.getResultList();
        userPA = Security.getUserPrivilegeAction("personScreen");
    }

    public String editPerson(Person person) {
        edit = true;
        this.person = person;
        oldPassword = person.getPassword();
        return "editPerson";
    }

    public String newPerson() {

        return "editPerson";
    }

    public String exit() {
        SessionUtil.cleanSession("personScreen");
        return "personList";
    }

    public String saveOnly() {
        save();
        return "";
    }

    public String saveAndExit() {
        save();
        return valid ? exit() : "";
    }

    public void save(){
        validate();
        if (valid) {
            passwordCode();
            try {
                EntityManager em = entityManagerFactory.createEntityManager();
                em.getTransaction().begin();
                person = em.merge(person);
                em.getTransaction().commit();
                em.close();

                String message = edit ? resourceBundle.getString("personScreen.success.edit") : resourceBundle.getString("personScreen.success.save");
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "infoTitle", message);
                FacesContext.getCurrentInstance().addMessage("mainForm:panel", facesMessage);
                edit = true;
            } catch (OptimisticLockException e){
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "errorTitle", resourceBundle.getString("error.entityWasChanged"));
                FacesContext.getCurrentInstance().addMessage("mainForm:panel", facesMessage);
                valid = false;
            } catch (Exception e){
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "errorTitle", resourceBundle.getString("error.exception"));
                FacesContext.getCurrentInstance().addMessage("mainForm:panel", facesMessage);
                valid = false;
            }
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "errorTitle", resourceBundle.getString("personScreen.error.title"));
            FacesContext.getCurrentInstance().addMessage("mainForm:panel", facesMessage);
        }
    }

    private void passwordCode(){
        if(!person.getPassword().equals("")) {
            oldPassword = DigestUtils.md5Hex(person.getPassword());
        }
        person.setPassword(oldPassword);
    }

    private void validate(){
        valid = isValidEmail() & isValidLogin() & isValidPassword();
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

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }
}
