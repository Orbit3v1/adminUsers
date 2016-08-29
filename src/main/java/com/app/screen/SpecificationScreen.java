package com.app.screen;

import com.app.entity.*;
import com.app.utils.*;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityGraph;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.http.Part;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Named("specificationScreen")
@Scope("view")
public class SpecificationScreen extends EntityScreen<Specification>{

    @Inject
    private Download downloader;

    private List<Person> developers;
    private Part file;

    @PostConstruct
    public void init() {
        logger.info("init");
        initEntity();

        developers = EntityUtil.getDevelopers(em);
    }

    @Override
    protected String getScreenName() {
        return "specificationScreen";
    }

    @Transactional
    public void initEntity() {
        String id = SessionUtil.getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            EntityGraph<Specification> graph = em.createEntityGraph(Specification.class);
            graph.addAttributeNodes("specificationAttachments");
            Map<String, Object> hints = new HashMap<>();
            hints.put("javax.persistence.loadgraph", graph);

            entity = em.find(Specification.class, AppUtil.toInteger(id), hints);
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

    public void download(SpecificationAttachment specificationAttachment) {
        logger.info("download attachment. fileName = " + specificationAttachment.getAttachment().getName());
        Attachment attachment = specificationAttachment.getAttachment();
        downloader.download(attachment);
    }

    @Override
    public void save() {
        if(!edit){
            generateName();
        }
        try {
            saveData();
        } catch (Exception e){
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                generateSubName();
                saveData();
            } else {
                throw e;
            }
        }
    }

    private void generateName(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String name = df.format(new Date());
        entity.setName(name);
        generateSubName();
    }

    private void generateSubName(){
        Query query = em.createQuery("select max(cast(p.subName as int)) from Specification p where p.name like :name");
        query.setParameter("name", entity.getName());
        Integer lastSubName = (Integer) query.getSingleResult();
        entity.setSubName(String.valueOf(lastSubName + 1));
    }

    @Transactional
    private void saveData() {
        for(SpecificationAttachment sa : entity.getSpecificationAttachments()){
            Attachment attachment = sa.getAttachment();
            if(attachment.getId() == 0) {
                AttachmentContent attachmentContent = attachment.getContent();
                attachmentContent = em.merge(attachmentContent);

                attachment.setId(attachmentContent.getId());
                attachment.setContent(attachmentContent);
            }
        }
        entity = em.merge(entity);
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
        Nomenclature nomenclature = em.find(Nomenclature.class, entity.getNomenclature().getId());
        entity.setNomenclature(nomenclature);
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
}
