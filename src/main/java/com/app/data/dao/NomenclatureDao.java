package com.app.data.dao;

import com.app.data.entity.Nomenclature;

import java.util.Collection;

public interface NomenclatureDao extends GenericDao<Nomenclature, Integer> {

    public Nomenclature getByIdWithResources(Integer id, Collection<Resource> resources);

    public enum Resource{
        ATTACHMENTS,
        COMPONENTS,
        ORDER_ITEMS,
        SPECIFICATIONS;
    }
}
