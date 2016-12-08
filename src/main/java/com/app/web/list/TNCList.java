package com.app.web.list;

import com.app.data.entity.*;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;
import com.app.web.Loggable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

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

    @Loggable
    @PostConstruct
    public void init(){
        super.init();
    }

    @Override
    protected List<TNC> getData() {
        Query query = em.createQuery("select p from TNC p " +
                "order by p.name");
        return query.getResultList();
    }

    public void select(TNC tnc) {
        RequestContext.getCurrentInstance().closeDialog(tnc);
    }

    public void cancel(){
        RequestContext.getCurrentInstance().closeDialog(null);
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

    @Override
    protected boolean canDelete(TNC entity){
        TNC tnc = em.find(TNC.class, entity.getId());
        return super.canDelete(entity)
                && tnc.getProducts().size() == 0;
    }

}
