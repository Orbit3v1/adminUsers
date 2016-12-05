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

    @Override
    @Transactional
    public void edit(TNC entity){
        entity = em.find(TNC.class, entity.getId());
        entity.getTNCAttachments().size();
        entity.getTncLinks().size();
        super.edit(entity);
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

    @Transactional
    protected void mergeEntity(){
        for(TNCAttachment tncAttachment : editEntity.getTNCAttachments()){
            Attachment attachment = tncAttachment.getAttachment();
            if(attachment.getId() == 0) {
                AttachmentContent attachmentContent = attachment.getContent();
                attachmentContent = em.merge(attachmentContent);

                attachment.setId(attachmentContent.getId());
                attachment.setContent(attachmentContent);
            }
        }
        editEntity = em.merge(editEntity);
    }

    public void uploadFile(FileUploadEvent event) {
        Attachment attachment = AppUtil.getAttachment(event.getFile());
        TNCAttachment tncAttachment = new TNCAttachment();
        tncAttachment.setAttachment(attachment);
        tncAttachment.setTnc(editEntity);
        editEntity.getTNCAttachments().add(tncAttachment);
        SessionUtil.addSessionVariable(String.valueOf(attachment.hashCode()), attachment);
    }

    public void deleteAttachment(TNCAttachment tncAttachment){
        editEntity.getTNCAttachments().remove(tncAttachment);
        SessionUtil.removeSessionVariable(String.valueOf(tncAttachment.getAttachment().hashCode()));
    }

    @Override
    protected void clearCash(){
        for(TNCAttachment tncAttachment : editEntity.getTNCAttachments()){
            SessionUtil.removeSessionVariable(String.valueOf(tncAttachment.getAttachment().hashCode()));
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

    public void deleteLink(TNCLink tncLink){
        editEntity.getTncLinks().remove(tncLink);
    }

    public void addLink(){
        openDialog("/screen/TNCListScreen", new HashMap<>(), null);
    }

    public void editLink(TNCLink link){
        String linkKey = "TNCLink_" + link.hashCode();
        SessionUtil.addSessionVariable(linkKey, link);
        Map<String,List<String>> params = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add(linkKey);
        params.put("link", list);
        openDialog("/screen/TNCListScreen", new HashMap<>(), params);
    }

    private void openDialog(String path, Map<String, Object> options, Map<String,List<String>> params){
        options.put("resizable", true);
        options.put("width", 650);
        options.put("height", 150);
        options.put("draggable", true);
        options.put("modal", true);
        RequestContext rq = RequestContext.getCurrentInstance();
        rq.openDialog(path, options, params);
    }

    public void onLinkAdded(SelectEvent event) {
        Object obj = event.getObject();
        if(obj != null){
            TNCLink link = (TNCLink) obj;
            link.setTnc(editEntity);
            editEntity.getTncLinks().add(link);
        }
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
