package com.app.data.dao.hibernate;

import com.app.data.dao.ProductGroupDao;
import com.app.data.dao.SpecificationDao;
import com.app.data.entity.ProductGroup;
import com.app.data.entity.Specification;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by ayaroslavtsev on 14.02.2017.
 */
public class HProductGroupDao extends HGenericDao<ProductGroup, Integer> implements ProductGroupDao {
    public HProductGroupDao(){
        super(ProductGroup.class);
    }

    @Override
    public List<ProductGroup> getAll() {
        Query query = em.createQuery("select r from ProductGroup r order by r.name");
        return query.getResultList();
    }

    @Override
    public List<ProductGroup> getAllRoots() {
        Query query = em.createQuery("select r from ProductGroup r where r.parent = null order by r.name");
        return query.getResultList();
    }
}
