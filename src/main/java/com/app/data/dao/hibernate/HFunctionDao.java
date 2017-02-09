package com.app.data.dao.hibernate;

import com.app.data.dao.CarRequestDao;
import com.app.data.dao.FunctionDao;
import com.app.data.entity.CarRequest;
import com.app.data.entity.Function;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by ayaroslavtsev on 09.02.2017.
 */
public class HFunctionDao extends HGenericDao<Function, Integer> implements FunctionDao {
    public HFunctionDao() {
        super(Function.class);
    }

    @Override
    public List<Function> getAll() {
        Query query = em.createQuery("select p from Function p " +
                "order by p.name");
        return query.getResultList();
    }
}
