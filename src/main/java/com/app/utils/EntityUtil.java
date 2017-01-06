package com.app.utils;

import com.app.data.dao.PersonDao;
import com.app.data.entity.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.ResourceBundle;

public class EntityUtil {

    private static List<Person> persons;

    public static List<Person> getDevelopers(){
        if(persons == null){
            refreshPersons();
        }
        return persons;
    }

    public static void refreshPersons(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PersonDao personDao = context.getBean("personDao", PersonDao.class);
        persons = personDao.getAll();
    }
}
