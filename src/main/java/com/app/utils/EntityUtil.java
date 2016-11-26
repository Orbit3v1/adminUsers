package com.app.utils;

import com.app.data.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Andrey on 24.08.2016.
 */
public class EntityUtil {

    private static List<Person> persons;

    static public List<Person> getDevelopers(EntityManager em){
        if(persons == null){
            refreshPersons(em);
        }
        return persons;
    }

    public static void refreshPersons(EntityManager em){
        Query query = em.createQuery("select r from Person r order by r.lastName, r.firstName");
        persons = query.getResultList();
    }
}
