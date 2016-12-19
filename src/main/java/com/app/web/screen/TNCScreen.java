package com.app.web.screen;

import com.app.common.NomenclatureComponentManager;
import com.app.data.dictionary.NAType;
import com.app.data.entity.*;
import com.app.security.Security;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;
import com.app.web.Loggable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.*;

@Named("TNCScreen")
@Scope("view")
public class TNCScreen extends EntityScreen<TNC>{

    private Date consumptionStart;
    private Date consumptionEnd;
    private BigDecimal consumptionResult;

    @Loggable
    @PostConstruct
    public void init() {
        initEntity();
        initConsumption();
    }

    @Transactional
    public void initEntity() {
        String id = SessionUtil.getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            entity = em.find(TNC.class, AppUtil.toInteger(id));
            entity.getTNCAttachments().size();
            entity.getTncLinks().size();
            edit = true;
        } else {
            entity = new TNC();
            entity.setRatio(BigDecimal.ONE);
        }
    }

    @Override
    protected String getScreenName() {
        return "TNCList";
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
    @Transactional
    protected void save() {
        for(TNCAttachment tncAttachment : entity.getTNCAttachments()){
            Attachment attachment = tncAttachment.getAttachment();
            if(attachment.getId() == 0) {
                AttachmentContent attachmentContent = attachment.getContent();
                attachmentContent = em.merge(attachmentContent);

                attachment.setId(attachmentContent.getId());
                attachment.setContent(attachmentContent);
            }
        }
        entity = em.merge(entity);
    }


    @Override
    protected void clearCash(){
        for(TNCAttachment tncAttachment : entity.getTNCAttachments()){
            SessionUtil.removeSessionVariable(String.valueOf(tncAttachment.getAttachment().hashCode()));
        }
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
        entity.setName(tnc1C.getName());
        entity.setPrice(tnc1C.getPrice());
        entity.setLink1C(tnc1C.getId());
        entity.setUnitsFrom(tnc1C.getUnit());
        entity.setUnitsTo(tnc1C.getUnit());
    }

    public void uploadFile(FileUploadEvent event) {
        Attachment attachment = AppUtil.getAttachment(event.getFile());
        TNCAttachment tncAttachment = new TNCAttachment();
        tncAttachment.setAttachment(attachment);
        tncAttachment.setTnc(entity);
        entity.getTNCAttachments().add(tncAttachment);
        SessionUtil.addSessionVariable(String.valueOf(attachment.hashCode()), attachment);
    }

    public void deleteAttachment(TNCAttachment tncAttachment){
        entity.getTNCAttachments().remove(tncAttachment);
        SessionUtil.removeSessionVariable(String.valueOf(tncAttachment.getAttachment().hashCode()));
    }

    //todo make calculation
    public void consumptionCalculate(){
        consumptionResult = BigDecimal.ZERO;
    }
    
    protected boolean canDelete(){
        TNC tnc = em.find(TNC.class, entity.getId());
        return tnc.getProducts().size() == 0
                && tnc.getTncRequestItems().size() == 0
                && tnc.getTncSupplyItems().size() == 0;
    }

    public void deleteLink(TNCLink tncLink){
        entity.getTncLinks().remove(tncLink);
    }

    public void addLink(){
        openDialog("/screen/TNCLinkScreen", new HashMap<>(), null);
    }

    public void editLink(TNCLink link){
        String linkKey = "TNCLink_" + link.hashCode();
        SessionUtil.addSessionVariable(linkKey, link);

        Map<String,List<String>> params = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add(linkKey);
        params.put("link", list);

        openDialog("/screen/TNCLinkScreen", new HashMap<>(), params);
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
            link.setTnc(entity);
            entity.getTncLinks().add(link);
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
