package com.app.screen;

import com.app.entity.*;
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
import java.util.List;

@Named("personScreen")
@Scope("session")
public class PersonScreen extends EntityScreen<Person>{

    @Inject
    Validator<Person> validator;

    private List<Role> roleSourceList;
    private String oldPassword;

    @PostConstruct
    public void init() {
        initEntity();
        Query query = em.createQuery("select r from Role r order by r.name");
        roleSourceList = query.getResultList();
    }

    @Override
    public void initEntity(Person entity) {
        oldPassword = entity.getPassword();
        super.initEntity(entity);
    }

    @Override
    public void initEntity() {
        entity = new Person();
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
                SessionUtil.setMessage("mainForm:panel", bundleKey, FacesMessage.SEVERITY_INFO);
                edit = true;
                return true;
            } catch (OptimisticLockException | StaleObjectStateException e){
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

    @Transactional
    private void saveData(){
        entity = em.merge(entity);
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
