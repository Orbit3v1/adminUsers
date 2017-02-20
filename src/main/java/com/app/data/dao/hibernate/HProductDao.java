package com.app.data.dao.hibernate;

import com.app.data.dao.ProductDao;
import com.app.data.dao.ProductGroupDao;
import com.app.data.entity.Product;
import com.app.data.entity.ProductGroup;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by ayaroslavtsev on 20.02.2017.
 */
public class HProductDao extends HGenericDao<Product, Integer> implements ProductDao {
    public HProductDao(){
        super(Product.class);
    }
    @Override
    public List<Product> getAll() {
        Query query = em.createQuery("select r from Product r order by r.name");
        return query.getResultList();
    }
}
