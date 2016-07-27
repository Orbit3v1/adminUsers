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

    @Inject
    Validator<Person> validator;

    private List<Role> roleSourceList;
    private String oldPassword;

    @PostConstruct
    public void init() {
        logger.info("init");
        initEntity();
        Query query = em.createQuery("select r from Role r order by r.name");
        roleSourceList = query.getResultList();
    }

    @Override
    @Transactional
    public void initEntity() {
        String id = getParameter("id");
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

    public boolean save(){
        if (validate()) {
            passwordCode();
            try {
                saveData();

                String bundleKey = edit ? "personScreen.success.edit" : "personScreen.success.save";
                addMessage.setMessage("mainForm:panel", bundleKey, FacesMessage.SEVERITY_INFO);
                edit = true;
                return true;
            } catch (OptimisticLockException | StaleObjectStateException e){
                logger.error(e.getMessage());
                addMessage.setMessage("mainForm:panel", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
            } catch (Exception e){
                logger.error(e.getMessage());
                e.printStackTrace();
                addMessage.setMessage("mainForm:panel", "error.exception", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            addMessage.setMessage("mainForm:panel", "personScreen.error.title", FacesMessage.SEVERITY_ERROR);
        }
        return false;
    }

    @Transactional
    public String delete(){
        logger.info("delete. id = " + entity.getId() + "; name = " + entity.getLogin());
        if(canDelete()){
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            logger.info("delete success");
            return exit();
        } else {
            logger.info("delete fail");
            addMessage.setMessage("mainForm:panel", "personScreen.error.delete", FacesMessage.SEVERITY_ERROR);
        }
        return "";

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
