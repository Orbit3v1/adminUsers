package com.app.data.dao;

import com.app.data.entity.Privilege;
import com.app.data.entity.ProductGroup;

import java.util.List;

/**
 * Created by ayaroslavtsev on 14.02.2017.
 */
public interface ProductGroupDao extends GenericDao<ProductGroup, Integer> {

    public List<ProductGroup> getAllRoots();
}
