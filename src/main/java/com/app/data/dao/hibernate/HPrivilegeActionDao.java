package com.app.data.dao.hibernate;

import com.app.data.dao.PersonDao;
import com.app.data.dao.PrivilegeActionDao;
import com.app.data.entity.Person;
import com.app.data.entity.PrivilegeAction;
import com.app.data.entity.PrivilegeActionId;

import javax.persistence.Query;
import java.util.List;

public class HPrivilegeActionDao extends HGenericDao<PrivilegeAction, PrivilegeActionId> implements PrivilegeActionDao {
    public HPrivilegeActionDao(){
        super(PrivilegeAction.class);
    }

    @Override
    public List<PrivilegeAction> getAll() {
        Query query = em.createQuery(
                "select r " +
                        "from PrivilegeAction r " +
                        "left join fetch r.privilege p " +
                        "left join fetch r.action a ");
        return query.getResultList();
    }
}
