package com.app.web.list;

import com.app.data.dao.PersonDao;
import com.app.data.entity.Person;
import com.app.web.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import com.app.security.Security;

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

    @Autowired
    private PersonDao personDao;

    private List<Person> persons;
    private Map<String, Boolean> userPA;

    @Loggable
    @PostConstruct
    public void init(){
        persons = personDao.getAll();
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
