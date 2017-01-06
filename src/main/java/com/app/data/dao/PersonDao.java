package com.app.data.dao;

import com.app.data.entity.Person;

import java.util.Collection;
import java.util.List;

public interface PersonDao extends GenericDao<Person, Integer> {

    public Person getByIdWithResources(Integer id, Collection<Resource> resources);

    public enum Resource{
        ROLES
    }

}
