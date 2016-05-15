package screen;

import entity.*;
import org.springframework.context.annotation.Scope;
import utils.Security;
import utils.SessionUtil;
import validator.RoleValidator;
import validator.Validator;

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
public class RoleScreen extends EntityScreen<Role>{

    @Inject
    Validator<Role> validator;

    private List<PrivilegeRow> privilegeRows;
    private Set<PrivilegeActionId> privilegeActions;

    private static final String READ = "READ";
    private static final String WRITE = "WRITE";

    @PostConstruct
    public void init() {
        entity = new Role();
        entity.setPrivilegeAction(new ArrayList<>());

        Query query = em.createQuery("select r.id from PrivilegeAction r");
        privilegeActions = new HashSet<>(query.getResultList());
    }

    public String delete(){
        em.remove(em.merge(entity));
        return exit();
    }

    @Override
    protected String getScreenName() {
        return "roleScreen";
    }

    public boolean save() {
        if (validate()) {
            try {
                savePrivileges();
                entity = em.merge(entity);

                String bundleKey = edit ? "roleScreen.success.edit" : "roleScreen.success.save";
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
            SessionUtil.setMessage("mainForm:panel", "roleScreen.error.title", FacesMessage.SEVERITY_ERROR);
        }
        return false;
    }

    private void savePrivileges(){
        if(privilegeRows != null) {
            for (PrivilegeRow row : privilegeRows) {
                setPrivilege(row.getPrivilege(), READ, row.isReadSelected());
                setPrivilege(row.getPrivilege(), WRITE, row.isWriteSelected());
            }
        }
    }

    private void setPrivilege(Privilege privilege, String actionId, boolean state){
        PrivilegeActionId privilegeActionId = new PrivilegeActionId(privilege.getId(), actionId);
        PrivilegeAction privilegeAction = new PrivilegeAction(privilegeActionId);
        if(!state && entity.getPrivilegeAction().contains(privilegeAction)) {
            entity.getPrivilegeAction().remove(privilegeAction);
        } else if(state && !entity.getPrivilegeAction().contains(privilegeAction)){
            entity.getPrivilegeAction().add(privilegeAction);
        }
    }

    private boolean validate() {
        return validator.validate(entity, edit);
    }

    private void initPrivilegeRows() {
        Query query = em.createQuery("select r from Privilege r order by r.pos");
        List<Privilege> privileges = query.getResultList();

        privilegeRows = new ArrayList<>();
        for (Privilege privilege : privileges) {
            privilegeRows.add(new PrivilegeRow(privilege, hasAction(privilege, READ), hasAction(privilege, WRITE)
                    , isAvailable(privilege, READ), isAvailable(privilege, WRITE)));
        }

    }

    private boolean hasAction(Privilege privilege, String actionId) {
        for (PrivilegeAction privilegeAction : entity.getPrivilegeAction()) {
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
