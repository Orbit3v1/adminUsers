package com.app.screen;

import com.app.entity.*;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import com.app.utils.SessionUtil;
import com.app.validator.Validator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import java.util.*;

@Named("roleScreen")
@Scope("view")
public class RoleScreen extends EntityScreen<Role>{

    @Inject
    Validator<Role> validator;

    private List<PrivilegeRow> privilegeRows;
    private Set<PrivilegeActionId> privilegeActions;

    private static final String READ = "READ";
    private static final String WRITE = "WRITE";
    private static final String EDIT = "EDIT";
    private static final String EXECUTE = "EXECUTE";

    @PostConstruct
    public void init() {
        logger.info("init");
        initEntity();
        Query query = em.createQuery("select r.id from PrivilegeAction r");
        privilegeActions = new HashSet<>(query.getResultList());

    }

    @Override
    public void initEntity() {
        String id = getParameter("id");
        if(id != null){
            entity = em.find(Role.class, id);
            edit = true;
        } else{
            entity = new Role();
            entity.setPrivilegeAction(new ArrayList<>());
        }

    }

    @Transactional
    public void delete(){
        logger.info("delete. id = " + entity.getId() + "; name = " + entity.getName());
        em.remove(em.merge(entity));
        saved = true;
        exit();
    }

    @Override
    protected String getScreenName() {
        return "roleScreen";
    }

    public boolean save() {
        if (validate()) {
            try {
                savePrivileges();
                saveData();

                String bundleKey = edit ? "roleScreen.success.edit" : "roleScreen.success.save";
                addMessage.setMessage("mainForm:panel", bundleKey, FacesMessage.SEVERITY_INFO);
                edit = true;
                return true;
            } catch (OptimisticLockException e){
                logger.error(e.getMessage());
                e.printStackTrace();
                addMessage.setMessage("mainForm:panel", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
            } catch (Exception e){
                logger.error(e.getMessage());
                e.printStackTrace();
                addMessage.setMessage("mainForm:panel", "error.exception", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            addMessage.setMessage("mainForm:panel", "roleScreen.error.title", FacesMessage.SEVERITY_ERROR);
        }
        return false;
    }

    @Transactional
    private void saveData(){
        entity = em.merge(entity);
    }


    private void savePrivileges(){
        if(privilegeRows != null) {
            for (PrivilegeRow row : privilegeRows) {
                setPrivilege(row.getPrivilege(), READ, row.isReadSelected());
                setPrivilege(row.getPrivilege(), WRITE, row.isWriteSelected());
                setPrivilege(row.getPrivilege(), EDIT, row.isEditSelected());
                setPrivilege(row.getPrivilege(), EXECUTE, row.isExecuteSelected());
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
            privilegeRows.add(new PrivilegeRow(privilege
                    , hasAction(privilege, READ), hasAction(privilege, WRITE)
                    , hasAction(privilege, EDIT), hasAction(privilege, EXECUTE)
                    , isAvailable(privilege, READ), isAvailable(privilege, WRITE)
                    , isAvailable(privilege, EDIT), isAvailable(privilege, EXECUTE)));
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
        private boolean readSelected, writeSelected, editSelected, executeSelected,
                readAvailable, writeAvailable, editAvailable, executeAvailable;

        public PrivilegeRow(Privilege privilege, boolean readSelected, boolean writeSelected, boolean editSelected, boolean executeSelected, boolean readAvailable, boolean writeAvailable, boolean editAvailable, boolean executeAvailable) {
            this.privilege = privilege;
            this.readSelected = readSelected;
            this.writeSelected = writeSelected;
            this.editSelected = editSelected;
            this.executeSelected = executeSelected;
            this.readAvailable = readAvailable;
            this.writeAvailable = writeAvailable;
            this.editAvailable = editAvailable;
            this.executeAvailable = executeAvailable;
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

        public boolean isEditSelected() {
            return editSelected;
        }

        public void setEditSelected(boolean editSelected) {
            this.editSelected = editSelected;
        }

        public boolean isExecuteSelected() {
            return executeSelected;
        }

        public void setExecuteSelected(boolean executeSelected) {
            this.executeSelected = executeSelected;
        }

        public boolean isEditAvailable() {
            return editAvailable;
        }

        public void setEditAvailable(boolean editAvailable) {
            this.editAvailable = editAvailable;
        }

        public boolean isExecuteAvailable() {
            return executeAvailable;
        }

        public void setExecuteAvailable(boolean executeAvailable) {
            this.executeAvailable = executeAvailable;
        }
    }

}
