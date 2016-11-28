package com.app.web.screen;

import com.app.common.NomenclatureComponentManager;
import com.app.data.dictionary.NAType;
import com.app.data.entity.*;
import com.app.utils.Download;
import com.app.utils.SessionUtil;
import com.app.web.Loggable;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import com.app.utils.AppUtil;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityGraph;
import javax.servlet.http.Part;
import java.util.*;
import java.util.stream.Collectors;

@Named("nomenclatureScreen")
@Scope("view")
public class NomenclatureScreen extends EntityScreen<Nomenclature> {

    @Inject
    private Download downloader;

    private String gib;
    private Part file;
    private NAType fileType;
    private NomenclatureComponentManager NCM;
    private Specification spToSave;

    @PostConstruct
    public void init() {
        logger.info("init");
        fileType = NAType.BENDING;
        initEntity();

        NCM = new NomenclatureComponentManager(entity);
    }

    @Override
    protected String getScreenName() {
        return "nomenclatureScreen";
    }

    @Transactional
    public void initEntity() {
        String id = SessionUtil.getParameter("id");
        String type = SessionUtil.getParameter("type");

        if(id != null && AppUtil.isNumeric(id)){
            entity = loadEntity(AppUtil.toInteger(id));
            gib = AppUtil.toString(entity.getGib());
            if(type != null && type.equals("copy")){
                entity = new Nomenclature(entity);
            } else {
                edit = true;
            }
        } else{
            entity = new Nomenclature();
        }
    }

    private Nomenclature loadEntity(int id){
        EntityGraph<Nomenclature> graph = em.createEntityGraph(Nomenclature.class);
        graph.addAttributeNodes("nomenclatureAttachments");
        graph.addAttributeNodes("components");
        graph.addAttributeNodes("orderItems");
        graph.addAttributeNodes("specifications");
        Map<String, Object> hints = new HashMap<>();
        hints.put("javax.persistence.loadgraph", graph);

        return  em.find(Nomenclature.class, id, hints);
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
        if(spToSave != null) {
            em.merge(spToSave);
            spToSave = null;
        }
        entity = em.merge(entity);
        entity.getSpecifications().size();
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
    protected boolean canDelete(){
        Nomenclature nomenclature = em.find(Nomenclature.class, entity.getId());
        return super.canDelete()
                && nomenclature.getOrderItems().size() == 0
                && nomenclature.getSpecification() == null
                ;
    }


    public void download(NomenclatureAttachment nomenclatureAttachment) {
        logger.info("download attachment. fileName = " + nomenclatureAttachment.getAttachment().getName());
        Attachment attachment = nomenclatureAttachment.getAttachment();
        downloader.download(attachment);
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

    @Loggable
    @Transactional
    public void refresh() {
        logger.info("refresh");
        List<Specification> specifications = new ArrayList<>();
        for(Specification sp : entity.getSpecifications()){
            specifications.add(em.find(Specification.class, sp.getId()));
        }
        entity.setSpecifications(specifications);
    }

    public void clearSpecification(){
        spToSave = entity.getSpecification();
        spToSave.setNomenclature(null);
        entity.setSpecifications(new ArrayList<>());
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

    public NomenclatureComponentManager getNCM() {
        return NCM;
    }

    public void setNCM(NomenclatureComponentManager NCM) {
        this.NCM = NCM;
    }
}
