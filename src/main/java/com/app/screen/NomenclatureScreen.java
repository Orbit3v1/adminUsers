package com.app.screen;

import com.app.dictionary.NAType;
import com.app.entity.*;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;
import com.app.validator.Validator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.servlet.http.Part;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

@Named("nomenclatureScreen")
@Scope("session")
public class NomenclatureScreen extends EntityScreen<Nomenclature> {

    @Inject
    Validator<Nomenclature> validator;

    private String gib;
    private Part file;
    private NAType fileType;
    private boolean closed;
    private boolean saved;
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
    public String saveRefresh() {
        logger.info("save in popUp");
        saved = false;
        if (save()) {
            saved = true;
        }
        return "";
    }

    public String close() {
        logger.info("close popUp");
        saved = false;
        closed = true;
        return "";
    }

    @Transactional
    public String saveRefreshClose() {
        logger.info("save and close popUp");
        saved = false;
        if (save()) {
            saved = true;
            closed = true;
        }
        return "";
    }


    @Transactional
    public void initEntity(Nomenclature entity) {
        saved = false;
        closed = false;
        gib = AppUtil.toString(entity.getGib());
        if(entity.getId() != 0){
            this.entity = em.find(Nomenclature.class, entity.getId());
            this.entity.getNomenclatureAttachments().size();
            this.entity.getComponents().size();
        } else {
            super.initEntity(entity);
        }
    }

    @Override
    public void initEntity() {
        saved = false;
        closed = false;
        entity = new Nomenclature();
        entity.setNomenclatureAttachments(new ArrayList<>());
        entity.setComponents(new ArrayList<>());
    }

    public boolean save() {
        if (validate()) {
            entity.setGib(AppUtil.toInteger(gib));
            try {
                saveData();

                String bundleKey = edit ? "nomenclatureScreen.success.edit" : "nomenclatureScreen.success.save";
                SessionUtil.setMessage("mainForm:panel", bundleKey, FacesMessage.SEVERITY_INFO);
                edit = true;
                return true;
            } catch (OptimisticLockException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                SessionUtil.setMessage("mainForm:panel", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                SessionUtil.setMessage("mainForm:panel", "error.exception", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            SessionUtil.setMessage("mainForm:panel", "nomenclatureScreen.error.title", FacesMessage.SEVERITY_ERROR);
        }
        return false;
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

    private boolean validate() {
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

    public void delete(NomenclatureAttachment nomenclatureAttachment) {
        logger.info("delete attachment. fileName = " + nomenclatureAttachment.getAttachment().getName());
        entity.getNomenclatureAttachments().remove(nomenclatureAttachment);
    }

    @Transactional
    public void download(NomenclatureAttachment nomenclatureAttachment) {
        logger.info("download attachment. fileName = " + nomenclatureAttachment.getAttachment().getName());
        Attachment attachment = nomenclatureAttachment.getAttachment();

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseContentType(attachment.getType());
        ec.setResponseContentLength(Math.toIntExact(attachment.getSize()));
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");

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

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
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
