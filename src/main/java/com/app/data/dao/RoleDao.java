package com.app.data.dao;

import com.app.data.entity.Person;
import com.app.data.entity.Role;

import java.util.Collection;

public interface RoleDao extends GenericDao<Role, String>{

    public Role getByIdWithResources(String id, Collection<Resource> resources);

    public enum Resource{
        PRIVILEGES
    }
}

