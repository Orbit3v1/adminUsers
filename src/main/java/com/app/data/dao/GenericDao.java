package com.app.data.dao;

import com.app.data.entity.interfaces.Unique;

import java.util.List;


public interface GenericDao<T extends Unique<K>, K> {

    public T save(T entity);

    public T getById(K id);

    public void delete(T entity);
}
