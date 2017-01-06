package com.app.utils;

import com.app.data.dao.PersonDao;
import com.app.data.dao.RoleDao;
import com.app.data.entity.Person;
import com.app.security.Security;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.ResourceBundle;

@Named("entityUtil")
@Scope("singleton")
public class EntityUtil {
    @Inject
    private PersonDao personDao;

    private List<Person> persons;

    @PostConstruct
    public void init(){
        refreshPersons();
    }

    public List<Person> getDevelopers(){
        return persons;
    }

    public void refreshPersons(){
        persons = personDao.getAll();
    }
}
