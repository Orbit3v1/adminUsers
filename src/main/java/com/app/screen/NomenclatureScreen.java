package com.app.screen;

import com.app.dictionary.NAType;
import com.app.entity.*;
import com.app.utils.SessionUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import com.app.utils.AppUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Named("nomenclatureScreen")
@Scope("view")
public class NomenclatureScreen extends EntityScreen<Nomenclature> {

    private String gib;
    private Part file;
    private NAType fileType;

    private Component tmpComponent;
    private Component originalComponent;
    private boolean componentEdit;

    @PostConstruct
    public void init() {
        logger.info("init");
        fileType = NAType.BENDING;
        initEntity();
        tmpComponent = new Component();

    }

    @Override
    protected String getScreenName() {
        return "nomenclatureScreen";
    }

    public void newComponent(){
        logger.info("add component");
        tmpComponent = new Component();
        tmpComponent.setNomenclature(entity);
        componentEdit = false;
    }

    public void editComponent(Component component){
        logger.info("edit component");
        originalComponent = component;
        tmpComponent = new Component();
        tmpComponent.copyForm(component);
        componentEdit = true;
    }

    public void deleteComponent(Component component){
        entity.getComponents().remove(component);
    }

    public void saveComponent(){
        if(componentEdit){
            logger.info("save existing component");
            originalComponent.copyForm(tmpComponent);
        } else {
            logger.info("save new component");
            entity.getComponents().add(tmpComponent);
            componentEdit = true;
        }
    }

    @Transactional
    public void initEntity() {
        String id = SessionUtil.getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            entity = em.find(Nomenclature.class, AppUtil.toInteger(id));
            this.entity.getNomenclatureAttachments().size();
            this.entity.getComponents().size();
            this.entity.getOrderItems().size();
            gib = AppUtil.toString(entity.getGib());
            edit = true;
        } else{
            entity = new Nomenclature();
        }
    }

    public void save() {
        entity.setGib(AppUtil.toInteger(gib));
        saveData();
    }

    @Transactional
    private void saveData() {
        for(NomenclatureAttachment na : entity.getNomenclatureAttachments()){
            Attachment attachment = na.getAttachment();
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
    protected boolean validate() {
        return validator.validate(entity, edit, gib);
    }

    public void uploadSketch() {
        uploadFile(NAType.SKETCH);
    }

    public void uploadDrawing() {
        uploadFile(fileType);
    }

    public void uploadFile(NAType fileType) {
        logger.info("upload file. type = " + fileType.getDescription());
        Attachment attachment = AppUtil.getAttachment(file);
        NomenclatureAttachment na = new NomenclatureAttachment();
        na.setAttachment(attachment);
        na.setNomenclature(entity);
        na.setType(fileType);
        entity.getNomenclatureAttachments().add(na);
    }

    public void deleteAttachment(NomenclatureAttachment nomenclatureAttachment) {
        logger.info("deleteAttachment attachment. fileName = " + nomenclatureAttachment.getAttachment().getName());
        entity.getNomenclatureAttachments().remove(nomenclatureAttachment);
    }

    @Transactional
    public void delete(){
        logger.info("delete. id = " + entity.getId() + "; name = " + entity.getName());
        if(canDelete()){
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            saved = true;
            logger.info("delete success");
            exit();
        } else {
            logger.info("delete fail");
            addMessage.setMessage("mainForm:panel", "nomenclatureScreen.error.delete", FacesMessage.SEVERITY_ERROR);
        }
    }

    private boolean canDelete(){
        return entity.getOrderItems().size() == 0;
    }


    @Transactional
    public void download(NomenclatureAttachment nomenclatureAttachment) {
        logger.info("download attachment. fileName = " + nomenclatureAttachment.getAttachment().getName());
        Attachment attachment = nomenclatureAttachment.getAttachment();

        String fileName = attachment.getName();
        try {
            fileName = URLEncoder.encode(attachment.getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseContentType(attachment.getType());
        ec.setResponseContentLength(Math.toIntExact(attachment.getSize()));
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        try {
            AttachmentContent content = em.find(AttachmentContent.class, attachment.getId());
            OutputStream output = ec.getResponseOutputStream();
            output.write(content.getContent());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        fc.responseComplete();

    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public List<NomenclatureAttachment> getSketches() {
        return entity.getNomenclatureAttachments().stream().filter(s -> s.getType() == NAType.SKETCH).collect(Collectors.toList());
    }

    public List<NomenclatureAttachment> getDrawings() {
        return entity.getNomenclatureAttachments().stream()
                .filter(s -> s.getType() != NAType.SKETCH)
                .sorted((NomenclatureAttachment o1, NomenclatureAttachment o2) -> o1.getType().getDescription().compareTo(o2.getType().getDescription()))
                .collect(Collectors.toList());
    }

    public List<NAType> getNATypes() {
        return Arrays.asList(NAType.values()).stream()
                .filter(s -> s != NAType.SKETCH)
                .sorted((NAType o1, NAType o2) -> o1.getDescription().compareTo(o2.getDescription()))
                .collect(Collectors.toList());
    }

    public NAType getFileType() {
        return fileType;
    }

    public void setFileType(NAType fileType) {
        this.fileType = fileType;
    }

    public String getGib() {
        return gib;
    }

    public void setGib(String gib) {
        this.gib = gib;
    }

    public Component getTmpComponent() {
        return tmpComponent;
    }

    public void setTmpComponent(Component tmpComponent) {
        this.tmpComponent = tmpComponent;
    }

    public boolean isComponentEdit() {
        return componentEdit;
    }

    public void setComponentEdit(boolean componentEdit) {
        this.componentEdit = componentEdit;
    }
}
