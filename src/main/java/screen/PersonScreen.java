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
public class PersonScreen extends EntityScreen<Person>{

    @Inject
    Validator<Person> validator;

    private List<Role> roleSourceList;
    private String oldPassword;

    @PostConstruct
    public void init() {
        initSecurity();
        entity = new Person();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select r from Role r order by r.name");
        roleSourceList = query.getResultList();
    }

    @Override
    public String editEntity(Person person) {
        oldPassword = person.getPassword();
        return super.editEntity(person);
    }

    @Override
    protected String getScreenName() {
        return "personScreen";
    }

    public boolean save(){
        if (validate()) {
            passwordCode();
            try {
                EntityManager em = entityManagerFactory.createEntityManager();
                em.getTransaction().begin();
                entity = em.merge(entity);
                em.getTransaction().commit();
                em.close();

                String bundleKey = edit ? "personScreen.success.edit" : "personScreen.success.save";
                SessionUtil.setMessage("mainForm:panel", bundleKey, FacesMessage.SEVERITY_INFO);
                edit = true;
                return true;
            } catch (OptimisticLockException e){
                e.printStackTrace();
                SessionUtil.setMessage("mainForm:panel", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
            } catch (Exception e){
                e.printStackTrace();
                SessionUtil.setMessage("mainForm:panel", "error.exception", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            SessionUtil.setMessage("mainForm:panel", "personScreen.error.title", FacesMessage.SEVERITY_ERROR);
        }
        return false;
    }

    private void passwordCode(){
        if(!entity.getPassword().equals("")) {
            oldPassword = DigestUtils.md5Hex(entity.getPassword());
        }
        entity.setPassword(oldPassword);
    }

    private boolean validate(){
        return validator.validate(entity, edit);
    }

    public List<Role> getRoleSourceList() {
        return roleSourceList;
    }

    public void setRoleSourceList(List<Role> roleSourceList) {
        this.roleSourceList = roleSourceList;
    }
}
