package com.app.web.list;

import com.app.data.entity.Person;
import com.app.data.entity.ProductTNC;
import com.app.data.entity.TNC;
import com.app.data.entity.TNC1C;
import com.app.utils.Security;
import com.app.web.Loggable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("TNCList")
@Scope("view")
public class TNCList extends EntityList<TNC>{

    @Override
    protected TNC createEntity() {
        TNC tnc = new TNC();
        tnc.setRatio(BigDecimal.ONE);
        return tnc;
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

    public void chooseTNC() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("width", 800);
        options.put("height", 450);
        options.put("draggable", true);
        options.put("modal", true);
        RequestContext rq = RequestContext.getCurrentInstance();
        rq.openDialog("/select/selectTNC1C", options, null);
    }

    @Transactional
    public void onTNCChosen(SelectEvent event) {
        TNC1C tnc1C = (TNC1C) event.getObject();
        editEntity.setName(tnc1C.getName());
        editEntity.setPrice(tnc1C.getPrice());
        editEntity.setLink1C(tnc1C.getId());
        editEntity.setUnitsFrom(tnc1C.getUnit());
        editEntity.setUnitsTo(tnc1C.getUnit());
    }
}
