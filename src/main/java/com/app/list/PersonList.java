package com.app.list;

import com.app.entity.Person;
import com.app.web.Loggable;
import org.springframework.context.annotation.Scope;
import com.app.utils.Security;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Named("personList")
@Scope("request")
public class PersonList {

    @PersistenceContext
    private EntityManager em;

    private List<Person> persons;
    private Map<String, Boolean> userPA;

    @Loggable
    @PostConstruct
    public void init(){
        Query query = em.createQuery("select p from Person p order by p.lastName, p.firstName");
        persons = query.getResultList();
        userPA = Security.getUserPrivilegeAction("personList");
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }

}
