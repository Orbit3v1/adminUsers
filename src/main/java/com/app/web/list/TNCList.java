package com.app.web.list;

import com.app.data.entity.Person;
import com.app.data.entity.TNC;
import com.app.utils.Security;
import com.app.web.Loggable;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Named("TNCList")
@Scope("request")
public class TNCList {

    @PersistenceContext
    private EntityManager em;

    private List<TNC> TNCs;
    private Map<String, Boolean> userPA;

    @Loggable
    @PostConstruct
    public void init(){
        Query query = em.createQuery("select p from TNC p order by p.name");
        TNCs = query.getResultList();
        userPA = Security.getUserPrivilegeAction("personList");
    }

    public List<TNC> getTNCs() {
        return TNCs;
    }

    public void setTNCs(List<TNC> TNCs) {
        this.TNCs = TNCs;
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }
}
