package screen;

import entity.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import utils.Security;
import utils.SessionUtil;
import validator.PersonValidator;
import validator.Validator;

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
    @Inject
    Validator<Person> validator;

    private Person person;
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
         return  save() ? exit() : "";
    }

    public boolean save(){
        if (validate()) {
            passwordCode();
            try {
                EntityManager em = entityManagerFactory.createEntityManager();
                em.getTransaction().begin();
                person = em.merge(person);
                em.getTransaction().commit();
                em.close();

                String bundleKey = edit ? "personScreen.success.edit" : "personScreen.success.save";
                SessionUtil.setMessage("mainForm:panel", bundleKey, FacesMessage.SEVERITY_INFO);
                edit = true;
                return true;
            } catch (OptimisticLockException e){
                SessionUtil.setMessage("mainForm:panel", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
            } catch (Exception e){
                SessionUtil.setMessage("mainForm:panel", "error.exception", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            SessionUtil.setMessage("mainForm:panel", "personScreen.error.title", FacesMessage.SEVERITY_ERROR);
        }
        return false;
    }

    private void passwordCode(){
        if(!person.getPassword().equals("")) {
            oldPassword = DigestUtils.md5Hex(person.getPassword());
        }
        person.setPassword(oldPassword);
    }

    private boolean validate(){
        return validator.validate(person, edit);
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
