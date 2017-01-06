package com.app.web.screen;

import com.app.data.dao.PersonDao;
import com.app.data.dao.RoleDao;
import com.app.data.dao.hibernate.HPersonDao;
import com.app.data.entity.Person;
import com.app.data.entity.Role;
import com.app.utils.AppUtil;
import com.app.utils.EntityUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import com.app.utils.SessionUtil;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.*;
import java.util.EnumSet;
import java.util.List;

@Named("personScreen")
@Scope("view")
public class PersonScreen extends EntityScreen<Person>{

    private List<Role> roleSourceList;
    private String oldPassword;

    @Autowired
    private PersonDao personDao;
    @Autowired
    private RoleDao roleDao;

    @PostConstruct
    public void init() {
        logger.info("init");
        initEntity();
        roleSourceList = roleDao.getAll();
    }

    @Transactional
    public void initEntity() {
        String id = SessionUtil.getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            entity = personDao.getByIdWithResources(AppUtil.toInteger(id), EnumSet.of(PersonDao.Resource.ROLES));
            oldPassword = entity.getPassword();
            edit = true;
        } else{
            entity = new Person();
        }

    }

    @Override
    protected String getScreenName() {
        return "personScreen";
    }

    public void save(){
        passwordCode();
        saveData();
        EntityUtil.refreshPersons();
    }

    @Transactional
    protected boolean canDelete(){
        Person person = personDao.getById(entity.getId());
        return super.canDelete()
                && person.getOrderItems().size() == 0
                && person.getOrders().size() == 0
                && person.getSpResponsible().size() == 0
                && person.getSpDeveloped().size() == 0
                && person.getSpApproved().size() == 0;
    }

    protected void postDelete(){
        EntityUtil.refreshPersons();
    }

    @Transactional
    private void saveData(){
        entity = personDao.save(entity);
    }

    private void passwordCode(){
        if(!entity.getPassword().equals("")) {
            logger.info("password was changed");
            oldPassword = DigestUtils.md5Hex(entity.getPassword());
        }
        entity.setPassword(oldPassword);
    }

    public List<Role> getRoleSourceList() {
        return roleSourceList;
    }

    public void setRoleSourceList(List<Role> roleSourceList) {
        this.roleSourceList = roleSourceList;
    }
}
