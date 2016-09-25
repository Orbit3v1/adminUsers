package com.app.web.list;

import com.app.data.entity.Person;
import com.app.data.entity.TNC;
import com.app.utils.Security;
import com.app.web.Loggable;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Named("TNCList")
@Scope("view")
public class TNCList extends EntityList<TNC>{

    @Override
    protected TNC createEntity() {
        return new TNC();
    }

    @Override
    protected String getScreenName() {
        return "TNCList";
    }

    @Override
    protected List<TNC> getData() {
        Query query = em.createQuery("select p from TNC p order by p.name");
        return query.getResultList();
    }
}
