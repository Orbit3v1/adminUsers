package com.app.web.list;

import com.app.data.entity.*;
import com.app.security.Security;
import com.app.web.Loggable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.*;

@Named("TNCList")
@Scope("view")
public class TNCList extends EntityList<TNC>{

    private Date consumptionStart;
    private Date consumptionEnd;
    private BigDecimal consumptionResult;

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
        initConsumption();
    }

    private void initConsumption(){
        Date referenceDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        consumptionEnd = c.getTime();

        c.add(Calendar.MONTH, -3);
        consumptionStart = c.getTime();
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

    //todo make calculation
    public void consumptionCalculate(){
        consumptionResult = BigDecimal.ZERO;
    }

    @Override
    protected boolean canDelete(TNC entity){
        TNC tnc = em.find(TNC.class, entity.getId());
        return super.canDelete(entity)
                && tnc.getProducts().size() == 0;
    }

    public Date getConsumptionStart() {
        return consumptionStart;
    }

    public void setConsumptionStart(Date consumptionStart) {
        this.consumptionStart = consumptionStart;
    }

    public Date getConsumptionEnd() {
        return consumptionEnd;
    }

    public void setConsumptionEnd(Date consumptionEnd) {
        this.consumptionEnd = consumptionEnd;
    }

    public BigDecimal getConsumptionResult() {
        return consumptionResult;
    }

    public void setConsumptionResult(BigDecimal consumptionResult) {
        this.consumptionResult = consumptionResult;
    }
}
