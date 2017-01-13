package com.app.data.dao.hibernate;

import com.app.data.dao.NomenclatureDao;
import com.app.data.dao.PersonDao;
import com.app.data.dao.SpecificationDao;
import com.app.data.entity.Nomenclature;
import com.app.data.entity.Person;
import com.app.data.entity.Role;
import com.app.data.entity.Specification;

import javax.persistence.EntityGraph;
import javax.persistence.Query;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HSpecificationDao extends HGenericDao<Specification, Integer> implements SpecificationDao {
    public HSpecificationDao(){
        super(Specification.class);
    }

    @Override
    public List<Specification> getAll() {
        Query query = em.createQuery("select r from Specification r order by r.id");
        return query.getResultList();
    }

    @Override
    public Specification getByIdWithResources(Integer id, Collection<Resource> resources) {
        EntityGraph<Specification> graph = em.createEntityGraph(Specification.class);
        if(resources.contains(SpecificationDao.Resource.ATTACHMENTS)){
            graph.addAttributeNodes("specificationAttachments");
        }
        if(resources.contains(SpecificationDao.Resource.NOMENCLATURE)){
            graph.addAttributeNodes("nomenclature");
        }
        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.loadgraph", graph);
        Specification entity = em.find(Specification.class, id, hints);
        return entity;
    }

    @Override
    public Integer getMaxSubName(String name) {
        Query query = em.createQuery("select max(cast(p.subName as int)) from Specification p where p.name like :name");
        query.setParameter("name", name);
        return (Integer) query.getSingleResult();
    }
}
