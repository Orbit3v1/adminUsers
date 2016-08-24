package com.app.utils;

import com.app.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Andrey on 24.08.2016.
 */
public class EntityUtil {

    static public List<Person> getDevelopers(EntityManager em){
        Query query = em.createQuery("select r from Person r order by r.lastName, r.firstName");
        List<Person> developers = query.getResultList();
        return developers;
    }
}
