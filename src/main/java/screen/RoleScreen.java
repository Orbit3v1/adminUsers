package screen;

import entity.Person;
import entity.Role;
import org.springframework.context.annotation.Scope;
import utils.SessionUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

@Named("roleScreen")
@Scope("session")
public class RoleScreen {
    @Inject
    private EntityManagerFactory entityManagerFactory;
    @Inject
    ResourceBundle resourceBundle;

    private Role role;
    private boolean valid = true;
    private boolean edit;

    @PostConstruct
    public void init() {
        role = new Role();
    }

    public String editRole(Role role) {
        edit = true;
        this.role = role;
        return "editRole";
    }

    public String newRole() {
        return "editRole";
    }

    public String exit() {
        SessionUtil.cleanSession("roleScreen");
        return "roleList";
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
            EntityManager em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            role = em.merge(role);
            em.getTransaction().commit();
            em.close();

            String message = edit ? resourceBundle.getString("roleScreen.success.edit") : resourceBundle.getString("roleScreen.success.save");
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "infoTitle", message);
            FacesContext.getCurrentInstance().addMessage("mainForm:panel", facesMessage);
            if(!edit){
                edit = true;
            }
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "errorTitle", resourceBundle.getString("roleScreen.error.title"));
            FacesContext.getCurrentInstance().addMessage("mainForm:panel", facesMessage);
        }
    }

    private void validate(){
        valid = isValidId() & isValidName();
    }

    private boolean isValidId(){
        boolean valid = true;
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9_-]+");
        String errorMessage = "";
        if(role.getId().equals("")){
            valid = false;
            errorMessage = resourceBundle.getString("error.notNull");
        } else if(pattern.matcher(role.getId()).find()){
            valid = false;
            errorMessage = resourceBundle.getString("roleScreen.error.idPattern");
        } else if(!edit) {
            EntityManager em = entityManagerFactory.createEntityManager();
            Query query = em.createQuery("select r from Role r where r.id = :id")
                    .setParameter("id", role.getId());
            if (query.getResultList().size() != 0) {
                valid = false;
                errorMessage = resourceBundle.getString("roleScreen.error.idDuplicate");
            }
        }
        if(!valid){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", errorMessage);
            FacesContext.getCurrentInstance().addMessage("mainForm:id", facesMessage);
        }
        return valid;
    }

    private boolean isValidName(){
        boolean valid = true;
        if(role.getName().equals("")){
            valid = false;
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "notNull", resourceBundle.getString("error.notNull"));
            FacesContext.getCurrentInstance().addMessage("mainForm:name", facesMessage);
        } else {
            EntityManager em = entityManagerFactory.createEntityManager();
            Query query = em.createQuery("select r from Role r where r.name = :name and (r.id != :id or :id is null)")
                    .setParameter("name", role.getName())
                    .setParameter("id", isEdit() ? role.getId() : null);
            if (query.getResultList().size() != 0) {
                valid = false;
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "idDuplicate", resourceBundle.getString("roleScreen.error.nameDuplicate"));
                FacesContext.getCurrentInstance().addMessage("mainForm:name", facesMessage);
            }
        }
        return valid;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
