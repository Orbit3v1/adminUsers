package com.app.data.dao;

import com.app.data.entity.Nomenclature;
import com.app.data.entity.Role;
import com.app.data.entity.Specification;

import java.util.Collection;


public interface SpecificationDao extends GenericDao<Specification, Integer>{

    public Specification getByIdWithResources(Integer id, Collection<Resource> resources);

    public Integer getMaxSubName(String name);

    public enum Resource{
        ATTACHMENTS,
        NOMENCLATURE;
    }
}
