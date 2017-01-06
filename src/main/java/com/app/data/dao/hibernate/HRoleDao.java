package com.app.data.dao.hibernate;

import com.app.data.dao.PersonDao;
import com.app.data.dao.RoleDao;
import com.app.data.entity.Person;
import com.app.data.entity.Role;

import javax.persistence.Query;
import java.util.List;

public class HRoleDao extends HGenericDao<Role, String> implements RoleDao {
    public HRoleDao(){
        super(Role.class);
    }

    @Override
    public List<Role> getAll() {
        Query query = em.createQuery("select r from Role r order by r.id");
        return query.getResultList();
    }
}
