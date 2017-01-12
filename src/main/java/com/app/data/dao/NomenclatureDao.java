package com.app.data.dao;

import com.app.data.entity.Nomenclature;
import com.app.data.entity.Person;

import java.util.Collection;

/**
 * Created by ayaroslavtsev on 11.01.2017.
 */
public interface NomenclatureDao extends GenericDao<Nomenclature, Integer> {

    public Nomenclature getByIdWithResources(Integer id, Collection<Resource> resources);

    public enum Resource{
        ATTACHMENTS,
        COMPONENTS,
        ORDER_ITEMS,
        SPECIFICATIONS;
    }
}
