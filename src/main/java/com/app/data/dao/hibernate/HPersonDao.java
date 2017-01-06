package com.app.data.dao.hibernate;

import com.app.data.dao.PersonDao;
import com.app.data.entity.Person;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public class HPersonDao extends HGenericDao<Person, Integer> implements PersonDao{

    public HPersonDao(){
        super(Person.class);
    }

    @Override
    public Person getByIdWithResources(Integer id, Collection<Resource> resources) {
        Person entity = getById(id);
        if(resources.contains(Resource.ROLES)){
            entity.getRoles().size();
        }
        return entity;
    }

    @Override
    public List<Person> getAll() {
        Query query = em.createQuery("select p from Person p order by p.lastName, p.firstName");
        return query.getResultList();
    }
}
