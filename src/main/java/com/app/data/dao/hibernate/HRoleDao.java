package com.app.data.dao.hibernate;

import com.app.data.dao.PersonDao;
import com.app.data.dao.RoleDao;
import com.app.data.entity.Person;
import com.app.data.entity.Role;
import com.app.data.entity.Specification;

import javax.persistence.EntityGraph;
import javax.persistence.Query;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HRoleDao extends HGenericDao<Role, String> implements RoleDao {
    public HRoleDao(){
        super(Role.class);
    }

    @Override
    public List<Role> getAll() {
        Query query = em.createQuery("select r from Role r order by r.id");
        return query.getResultList();
    }

    public Role getByIdWithResources(String id, Collection<Resource> resources){
        EntityGraph<Role> graph = em.createEntityGraph(Role.class);
        if(resources.contains(Resource.PRIVILEGES)){
            graph.addAttributeNodes("privilegeAction");
        }
        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.loadgraph", graph);
        Role entity = em.find(Role.class, id, hints);
        return entity;
    }
}
