package com.app.web.screen;

import com.app.data.dao.AttachmentContentDao;
import com.app.data.dao.NomenclatureDao;
import com.app.data.dao.RoleDao;
import com.app.data.dao.SpecificationDao;
import com.app.data.entity.*;
import com.app.msOffice.SpecificationDoc;
import com.app.security.Security;
import com.app.utils.*;
import com.app.web.Loggable;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import javax.servlet.http.Part;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Named("specificationScreen")
@Scope("view")
public class SpecificationScreen extends EntityScreen<Specification>{

    @Inject
    private SpecificationDao specificationDao;
    @Inject
    private AttachmentContentDao attachmentContentDao;
    @Inject
    private NomenclatureDao nomenclatureDao;

    private List<Person> developers;
    private Part file;
    private String workDays;

    @PostConstruct
    public void init() {
        logger.info("init");
        initEntity();

        developers = entityUtil.getDevelopers();
    }

    @Override
    protected String getScreenName() {
        return "specificationScreen";
    }

    @Transactional
    public void initEntity() {
        String id = SessionUtil.getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            entity = specificationDao.getByIdWithResources(AppUtil.toInteger(id), EnumSet.of(SpecificationDao.Resource.ATTACHMENTS, SpecificationDao.Resource.NOMENCLATURE));
            workDays = AppUtil.toString(entity.getWorkDays());
            edit = true;
        } else {
            entity = new Specification();
            entity.setResponsible(Security.getCurrentUser());
            entity.setStart(new Date());
        }
    }

    public void uploadFile() {
        logger.info("upload file");
        Attachment attachment = AppUtil.getAttachment(file);
        SpecificationAttachment sa = new SpecificationAttachment();
        sa.setAttachment(attachment);
        sa.setSpecification(entity);
        entity.getSpecificationAttachments().add(sa);
    }

    public void deleteAttachment(SpecificationAttachment specificationAttachment) {
        logger.info("deleteAttachment attachment. fileName = " + specificationAttachment.getAttachment().getName());
        entity.getSpecificationAttachments().remove(specificationAttachment);
    }

    @Override
    public void save() {
        entity.setWorkDays(AppUtil.toInteger(workDays));
        if(!edit){
            generateName();
        }
        try {
            saveData();
        } catch (Exception e){
            if (isConstraintViolationException(e)) {
                generateSubName();
                saveData();
            } else {
                throw e;
            }
        }
    }

    private boolean isConstraintViolationException(Throwable e){
        Throwable t = e.getCause();
        while ((t != null) && !(t instanceof ConstraintViolationException)) {
            t = t.getCause();
        }
        return t instanceof ConstraintViolationException;
    }

    @Override
    protected boolean validate() {
        return validator.validate(entity, edit, workDays);
    }

    private void generateName(){
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String name = df.format(new Date());
        entity.setName(name);
        generateSubName();
    }

    private void generateSubName(){
        Integer lastSubName = specificationDao.getMaxSubName(entity.getName());
        lastSubName = lastSubName == null ? 1 : lastSubName + 1;
        entity.setSubName(String.valueOf(lastSubName));
    }

    @Transactional
    private void saveData() {
        saveAttachment();
        entity = specificationDao.save(entity);
    }

    private void saveAttachment(){
        for(SpecificationAttachment sa : entity.getSpecificationAttachments()){
            Attachment attachment = sa.getAttachment();
            if(attachment.getId() == 0) {
                AttachmentContent attachmentContent = attachment.getContent();
                attachmentContent = attachmentContentDao.save(attachmentContent);

                attachment.setId(attachmentContent.getId());
                attachment.setContent(attachmentContent);
            }
        }
    }

    public void shareEntity(){
        SessionUtil.addSessionVariable("EntityNomenclature", entity);
    }

    @Override
    public void exit() {
        SessionUtil.removeSessionVariable("EntityNomenclature" + entity.getId());
        super.exit();
    }

    @Transactional
    public void refresh(){
        logger.info("refresh");
        Nomenclature nomenclature = nomenclatureDao.getById(entity.getNomenclature().getId());
        entity.setNomenclature(nomenclature);
    }

    public String getApproved(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String approved = df.format(new Date());
        String approvedBy = entity.getApprovedBy().toString();
        return approvedBy + " " + approved;
    }

    @Loggable
    public void setApprove(){
        entity.setApproved(new Date());
        Person approver = Security.getCurrentUser();
        entity.setApprovedBy(approver);
    }


    public void generateWord(){
        SpecificationDoc specificationDoc = new SpecificationDoc(entity);
        try {
            specificationDoc.render();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            addMessage.setMessage("mainForm:panel", "error.exception", FacesMessage.SEVERITY_ERROR);
        }
    }

    public void clearNomenclature(){
        entity.setNomenclature(null);
    }


    public List<Person> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Person> developers) {
        this.developers = developers;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public List<SpecificationAttachment> getSketches() {
        return entity.getSpecificationAttachments();
    }

    public String getWorkDays() {
        return workDays;
    }

    public void setWorkDays(String workDays) {
        this.workDays = workDays;
    }
}
