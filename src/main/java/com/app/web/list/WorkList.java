package com.app.web.list;

import com.app.data.entity.Function;
import com.app.data.entity.TNC;
import com.app.data.entity.Work;
import com.app.utils.AddMessage;
import com.app.utils.Security;
import com.app.web.Loggable;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Named("workList")
@Scope("view")
public class WorkList extends EntityList<Work>{

    @Override
    protected Work createEntity() {
        return new Work();
    }

    @Override
    protected String getScreenName() {
        return "workList";
    }

    @Loggable
    @PostConstruct
    public void init(){
        super.init();
        Query query = em.createQuery("select p from Work p order by p.name");
        entities = query.getResultList();
    }

    public void select(Work work) {
        RequestContext.getCurrentInstance().closeDialog(work);
    }
}
