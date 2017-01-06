package com.app.data.dao.hibernate;

import com.app.data.dao.PersonDao;
import com.app.data.dao.PrivilegeDao;
import com.app.data.entity.Person;
import com.app.data.entity.Privilege;

import javax.persistence.Query;
import java.util.List;

public class HPrivilegeDao extends HGenericDao<Privilege, String> implements PrivilegeDao {
    public HPrivilegeDao(){
        super(Privilege.class);
    }

    @Override
    public List<Privilege> getAll() {
        Query query = em.createQuery("select r from Privilege r order by r.pos");
        return query.getResultList();
    }
}
