package com.app.web.screen;

import com.app.data.dao.PrivilegeActionDao;
import com.app.data.dao.PrivilegeDao;
import com.app.data.dao.RoleDao;
import com.app.data.entity.Privilege;
import com.app.data.entity.PrivilegeAction;
import com.app.data.entity.PrivilegeActionId;
import com.app.data.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import com.app.utils.SessionUtil;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Named("roleScreen")
@Scope("view")
public class RoleScreen extends EntityScreen<Role>{

    @Inject
    private RoleDao roleDao;
    @Inject
    private PrivilegeDao privilegeDao;
    @Inject
    private PrivilegeActionDao privilegeActionDao;

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
        privilegeActions = privilegeActionDao.getAll().stream().map(PrivilegeAction::getId).collect(Collectors.toSet());
    }

    public void initEntity() {
        String id = SessionUtil.getParameter("id");
        if(id != null){
            entity = roleDao.getByIdWithResources(id, EnumSet.of(RoleDao.Resource.PRIVILEGES));
            edit = true;
        } else{
            entity = new Role();
        }

    }

    @Override
    protected String getScreenName() {
        return "roleScreen";
    }

    public void save() {
        savePrivileges();
        saveData();
    }

    @Transactional
    private void saveData(){
        entity = roleDao.save(entity);
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



    private void initPrivilegeRows() {
        List<Privilege> privileges = privilegeDao.getAll();

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
