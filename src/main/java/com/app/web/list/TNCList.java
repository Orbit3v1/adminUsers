package com.app.web.list;

import com.app.data.entity.*;
import com.app.utils.Security;
import com.app.web.Loggable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
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

    public void select(TNC tnc) {
        RequestContext.getCurrentInstance().closeDialog(tnc);
    }

    public void cancel(){
        RequestContext.getCurrentInstance().closeDialog(null);
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

    public void refresh1C(){
        try {
            StoredProcedureQuery query = this.em.createStoredProcedureQuery("refreshTNC");
            query.execute();
            super.init();
            addMessage.setMessage(null, "success.refresh1C", FacesMessage.SEVERITY_INFO);
        } catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            addMessage.setMessage("mainForm:entities", "error.refresh1C", FacesMessage.SEVERITY_ERROR);
        }
    }
}
