package com.app.data.dao.hibernate;

import com.app.data.dao.NomenclatureDao;
import com.app.data.dao.PersonDao;
import com.app.data.dao.RoleDao;
import com.app.data.entity.Nomenclature;
import com.app.data.entity.Person;
import com.app.data.entity.Role;

import javax.persistence.EntityGraph;
import javax.persistence.Query;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HNomenclatureDao extends HGenericDao<Nomenclature, Integer> implements NomenclatureDao {

    public HNomenclatureDao() {
        super(Nomenclature.class);
    }

    @Override
    public Nomenclature getByIdWithResources(Integer id, Collection<Resource> resources) {
        EntityGraph<Nomenclature> graph = em.createEntityGraph(Nomenclature.class);
        if(resources.contains(NomenclatureDao.Resource.ATTACHMENTS)){
            graph.addAttributeNodes("nomenclatureAttachments");
        }
        if(resources.contains(NomenclatureDao.Resource.COMPONENTS)){
            graph.addAttributeNodes("components");
        }
        if(resources.contains(NomenclatureDao.Resource.ORDER_ITEMS)){
            graph.addAttributeNodes("orderItems");
        }
        if(resources.contains(NomenclatureDao.Resource.SPECIFICATIONS)){
            graph.addAttributeNodes("specifications");
        }
        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.loadgraph", graph);
        Nomenclature entity = em.find(Nomenclature.class, id, hints);
        return entity;
    }

    @Override
    public List<Nomenclature> getAll() {
        Query query = em.createQuery("select p from Nomenclature p " +
                "order by p.name");
        return query.getResultList();
    }
}


