package com.app.data.dao.hibernate;

import com.app.data.dao.CarRequestDao;
import com.app.data.dao.NomenclatureDao;
import com.app.data.entity.CarRequest;
import com.app.data.entity.Nomenclature;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class HCarRequestDao extends HGenericDao<CarRequest, Integer> implements CarRequestDao {

    public HCarRequestDao() {
        super(CarRequest.class);
    }

    @Override
    public List<CarRequest> getAll() {
        Query query = em.createQuery("select p from CarRequest p " +
                "order by p.name");
        return query.getResultList();
    }
}
