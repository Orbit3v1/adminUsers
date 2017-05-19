package com.app.data.dao.hibernate;

import com.app.data.dao.CarRequestDao;
import com.app.data.dao.ServiceRequestDao;
import com.app.data.entity.CarRequest;
import com.app.data.entity.ServiceRequest;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by ayaroslavtsev on 18.05.2017.
 */
public class HServiceRequestDao extends HGenericDao<ServiceRequest, Integer> implements ServiceRequestDao {

    public HServiceRequestDao() {
        super(ServiceRequest.class);
    }

    @Override
    public List<ServiceRequest> getAll() {
        Query query = em.createQuery("select p from ServiceRequest p " +
                "order by p.name");
        return query.getResultList();
    }
}
