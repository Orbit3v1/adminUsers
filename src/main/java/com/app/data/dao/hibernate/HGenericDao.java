package com.app.data.dao.hibernate;

import com.app.data.dao.GenericDao;
import com.app.data.entity.interfaces.Unique;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public abstract class HGenericDao<T extends Unique<K>, K> implements GenericDao<T, K>{

    @PersistenceContext
    protected EntityManager em;

    protected Class<T> type;

    public HGenericDao(Class<T> type){
        this.type = type;
    }

    @Override
    public T save(T entity) {
       return em.merge(entity);
    }

    @Override
    public T getById(K id) {
        return em.find(type, id);
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

}
