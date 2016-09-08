package com.app.web.list;

import com.app.data.entity.Role;
import org.springframework.context.annotation.Scope;
import com.app.utils.Security;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Named("roleList")
@Scope("request")
public class RoleList {
    @PersistenceContext
    protected EntityManager em;

    private List<Role> roles;
    private Map<String, Boolean> userPA;


    @PostConstruct
    public void init(){
        Query query = em.createQuery("select r from Role r order by r.id");
        roles = query.getResultList();
        userPA = Security.getUserPrivilegeAction("roleList");
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }
}