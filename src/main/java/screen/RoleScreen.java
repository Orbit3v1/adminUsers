package screen;

import entity.Privilege;
import entity.PrivilegeAction;
import entity.PrivilegeActionId;
import entity.Role;
import org.springframework.context.annotation.Scope;
import utils.Security;
import utils.SessionUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import java.util.*;
import java.util.regex.Pattern;

@Named("roleScreen")
@Scope("session")
public class RoleScreen {
    @Inject
    private EntityManagerFactory entityManagerFactory;
    @Inject
    ResourceBundle resourceBundle;

    private Map<String, Boolean> userPA;

    private Role role;
    private boolean valid = true;
    private boolean edit;
    private List<PrivilegeRow> privilegeRows;
    private Set<PrivilegeActionId> privilegeActions;

    private static final String READ = "READ";
    private static final String WRITE = "WRITE";

    @PostConstruct
    public void init() {
        role = new Role();
        role.setPrivilegeAction(new ArrayList<>());

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select r.id from PrivilegeAction r");
        privilegeActions = new HashSet<>(query.getResultList());
        userPA = Security.getUserPrivilegeAction("roleScreen");
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

    public String delete(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(role));
        em.getTransaction().commit();
        em.close();
        return exit();
    }

    public void save() {
        validate();
        if (valid) {
            try {
                savePrivileges();
                EntityManager em = entityManagerFactory.createEntityManager();
                em.getTransaction().begin();
                role = em.merge(role);
                em.getTransaction().commit();
                em.close();

                String message = edit ? resourceBundle.getString("roleScreen.success.edit") : resourceBundle.getString("roleScreen.success.save");
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
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "errorTitle", resourceBundle.getString("roleScreen.error.title"));
            FacesContext.getCurrentInstance().addMessage("mainForm:panel", facesMessage);
        }
    }

    private void savePrivileges(){
        for(PrivilegeRow row : privilegeRows){
            setPrivilege(row.getPrivilege(), READ, row.isReadSelected());
            setPrivilege(row.getPrivilege(), WRITE, row.isWriteSelected());
        }
    }

    private void setPrivilege(Privilege privilege, String actionId, boolean state){
        PrivilegeActionId privilegeActionId = new PrivilegeActionId(privilege.getId(), actionId);
        PrivilegeAction privilegeAction = new PrivilegeAction(privilegeActionId);
        if(!state && role.getPrivilegeAction().contains(privilegeAction)) {
            role.getPrivilegeAction().remove(privilegeAction);
        } else if(state && !role.getPrivilegeAction().contains(privilegeAction)){
            role.getPrivilegeAction().add(privilegeAction);
        }
    }

    private void validate() {
        valid = isValidId() & isValidName();
    }

    private boolean isValidId() {
        boolean valid = true;
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9_-]+");
        String errorMessage = "";
        if (role.getId().equals("")) {
            valid = false;
            errorMessage = resourceBundle.getString("error.notNull");
        } else if (pattern.matcher(role.getId()).find()) {
            valid = false;
            errorMessage = resourceBundle.getString("roleScreen.error.idPattern");
        } else if (!edit) {
            EntityManager em = entityManagerFactory.createEntityManager();
            Query query = em.createQuery("select r from Role r where r.id = :id")
                    .setParameter("id", role.getId());
            if (query.getResultList().size() != 0) {
                valid = false;
                errorMessage = resourceBundle.getString("roleScreen.error.idDuplicate");
            }
        }
        if (!valid) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "error", errorMessage);
            FacesContext.getCurrentInstance().addMessage("mainForm:id", facesMessage);
        }
        return valid;
    }

    private boolean isValidName() {
        boolean valid = true;
        if (role.getName().equals("")) {
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

    private void initPrivilegeRows() {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select r from Privilege r order by r.pos");
        List<Privilege> privileges = query.getResultList();

        privilegeRows = new ArrayList<>();
        for (Privilege privilege : privileges) {
            privilegeRows.add(new PrivilegeRow(privilege, hasAction(privilege, READ), hasAction(privilege, WRITE)
                    , isAvailable(privilege, READ), isAvailable(privilege, WRITE)));
        }

    }

    private boolean hasAction(Privilege privilege, String actionId) {
        for (PrivilegeAction privilegeAction : role.getPrivilegeAction()) {
            if (privilegeAction.getPrivilege().equals(privilege) && privilegeAction.getAction().getId().equals(actionId)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAvailable(Privilege privilege, String actionId) {
        PrivilegeActionId privilegeActionId = new PrivilegeActionId(privilege.getId(), actionId);
        return privilegeActions.contains(privilegeActionId);
    }

    public List<PrivilegeRow> getPrivilegeRows() {
        if (privilegeRows == null) {
            initPrivilegeRows();
        }
        return privilegeRows;
    }

    public void setPrivilegeRows(List<PrivilegeRow> privilegeRows) {
        this.privilegeRows = privilegeRows;
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

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }

    public class PrivilegeRow {

        private Privilege privilege;
        private boolean readSelected, writeSelected, readAvailable, writeAvailable;

        public PrivilegeRow(Privilege privilege, boolean readSelected, boolean writeSelected, boolean readAvailable, boolean writeAvailable) {
            this.privilege = privilege;
            this.readSelected = readSelected;
            this.writeSelected = writeSelected;
            this.readAvailable = readAvailable;
            this.writeAvailable = writeAvailable;
        }

        public Privilege getPrivilege() {
            return privilege;
        }

        public void setPrivilege(Privilege privilege) {
            this.privilege = privilege;
        }

        public boolean isReadSelected() {
            return readSelected;
        }

        public void setReadSelected(boolean readSelected) {
            this.readSelected = readSelected;
        }

        public boolean isWriteSelected() {
            return writeSelected;
        }

        public void setWriteSelected(boolean writeSelected) {
            this.writeSelected = writeSelected;
        }

        public boolean isReadAvailable() {
            return readAvailable;
        }

        public void setReadAvailable(boolean readAvailable) {
            this.readAvailable = readAvailable;
        }

        public boolean isWriteAvailable() {
            return writeAvailable;
        }

        public void setWriteAvailable(boolean writeAvailable) {
            this.writeAvailable = writeAvailable;
        }

    }

}
