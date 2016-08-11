package com.app.screen;

import com.app.entity.*;
import com.app.utils.AppUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.StaleObjectStateException;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import com.app.utils.SessionUtil;
import com.app.validator.Validator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Named("personScreen")
@Scope("view")
public class PersonScreen extends EntityScreen<Person>{

    private List<Role> roleSourceList;
    private String oldPassword;

    @PostConstruct
    public void init() {
        logger.info("init");
        initEntity();
        Query query = em.createQuery("select r from Role r order by r.name");
        roleSourceList = query.getResultList();
    }

    @Transactional
    public void initEntity() {
        String id = SessionUtil.getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            entity = em.find(Person.class, AppUtil.toInteger(id));
            entity.getRoles().size();
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
    }

    @Transactional
    public void delete(){
        logger.info("delete. id = " + entity.getId() + "; name = " + entity.getLogin());
        if(canDelete()){
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            logger.info("delete success");
            saved = true;
            exit();
        } else {
            logger.info("delete fail");
            addMessage.setMessage("mainForm:panel", "personScreen.error.delete", FacesMessage.SEVERITY_ERROR);
        }
     }

    @Transactional
    private boolean canDelete(){
        Person person = em.find(Person.class, entity.getId());
        return person.getOrderItems().size() == 0 && person.getOrders().size() == 0;
    }

    @Transactional
    private void saveData(){
        entity = em.merge(entity);
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
