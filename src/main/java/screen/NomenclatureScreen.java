package screen;

import dictionary.NAType;
import entity.Attachment;
import entity.Nomenclature;
import entity.NomenclatureAttachment;
import org.springframework.context.annotation.Scope;
import utils.AppUtil;
import utils.SessionUtil;
import validator.Validator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.servlet.http.Part;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Named("nomenclatureScreen")
@Scope("session")
public class NomenclatureScreen extends EntityScreen<Nomenclature> {

    @Inject
    Validator<Nomenclature> validator;

    private Part file;
    private NAType fileType;

    @PostConstruct
    public void init() {
        initSecurity();
        fileType = NAType.BENDING;
        entity = new Nomenclature();
        entity.setNomenclatureAttachments(new ArrayList<>());
    }

    @Override
    protected String getScreenName() {
        return "nomenclatureScreen";
    }


    public boolean save() {
        if (validate()) {
            try {
                EntityManager em = entityManagerFactory.createEntityManager();
                em.getTransaction().begin();
                entity = em.merge(entity);
                em.getTransaction().commit();
                em.close();

                String bundleKey = edit ? "nomenclatureScreen.success.edit" : "nomenclatureScreen.success.save";
                SessionUtil.setMessage("mainForm:panel", bundleKey, FacesMessage.SEVERITY_INFO);
                edit = true;
                return true;
            } catch (OptimisticLockException e) {
                e.printStackTrace();
                SessionUtil.setMessage("mainForm:panel", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
            } catch (Exception e) {
                e.printStackTrace();
                SessionUtil.setMessage("mainForm:panel", "error.exception", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            SessionUtil.setMessage("mainForm:panel", "nomenclatureScreen.error.title", FacesMessage.SEVERITY_ERROR);
        }
        return false;
    }

    private boolean validate() {
        return validator.validate(entity, edit);
    }

    public void uploadSketch(){
        uploadFile(NAType.SKETCH);

    }

    public void uploadDrawing(){
        uploadFile(fileType);
    }

    public void uploadFile(NAType fileType){
        Attachment attachment = AppUtil.getAttachment(file);
        NomenclatureAttachment na = new NomenclatureAttachment();
        na.setAttachment(attachment);
        na.setNomenclature(entity);
        na.setType(fileType);
        entity.getNomenclatureAttachments().add(na);
    }

    public void delete(NomenclatureAttachment nomenclatureAttachment ){
        entity.getNomenclatureAttachments().remove(nomenclatureAttachment);
    }

    public void download(NomenclatureAttachment nomenclatureAttachment){

        Attachment attachment = nomenclatureAttachment.getAttachment();

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseContentType(attachment.getType());
        ec.setResponseContentLength((int) attachment.getSize());
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");

        try {
            OutputStream output = ec.getResponseOutputStream();
            output.write(attachment.getContent());
        } catch (Exception e) {
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

    public List<NomenclatureAttachment> getSketches(){
        return entity.getNomenclatureAttachments().stream().filter(s -> s.getType() == NAType.SKETCH).collect(Collectors.toList());
    }

    public List<NomenclatureAttachment> getDrawings(){
        return entity.getNomenclatureAttachments().stream()
                .filter(s -> s.getType() != NAType.SKETCH)
                .sorted((NomenclatureAttachment o1, NomenclatureAttachment o2) -> o1.getType().getDescription().compareTo(o2.getType().getDescription()))
                .collect(Collectors.toList());
    }

    public List<NAType> getNATypes(){
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

}
