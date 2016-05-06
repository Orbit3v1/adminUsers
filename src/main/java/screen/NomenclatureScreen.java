package screen;

import entity.Nomenclature;
import entity.Person;
import org.springframework.context.annotation.Scope;
import utils.Security;
import utils.SessionUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import java.util.Map;
import java.util.ResourceBundle;

@Named("nomenclatureScreen")
@Scope("session")
public class NomenclatureScreen {

    @Inject
    private EntityManagerFactory entityManagerFactory;
    @Inject
    ResourceBundle resourceBundle;

    private Nomenclature nomenclature;
    private boolean edit;
    private Map<String, Boolean> userPA;

    @PostConstruct
    public void init() {
        nomenclature = new Nomenclature();
        userPA = Security.getUserPrivilegeAction("nomenclatureScreen");
    }


    public String editEntity(Nomenclature nomenclature) {
        edit = true;
        this.nomenclature = nomenclature;
        return "editEntity";
    }

    public String newEntity() {
        return "editEntity";
    }

    public String exit() {
        SessionUtil.cleanSession("nomenclatureScreen");
        return "toList";
    }

    public String saveOnly() {
        save();
        return "";
    }

    public String saveAndExit() {
        return  save() ? exit() : "";
    }

    public boolean save(){
        if (validate()) {
            try {
                EntityManager em = entityManagerFactory.createEntityManager();
                em.getTransaction().begin();
                nomenclature = em.merge(nomenclature);
                em.getTransaction().commit();
                em.close();

                String bundleKey = edit ? "nomenclatureScreen.success.edit" : "nomenclatureScreen.success.save";
                SessionUtil.setMessage("mainForm:panel", bundleKey, FacesMessage.SEVERITY_INFO);
                edit = true;
                return true;
            } catch (OptimisticLockException e){
                SessionUtil.setMessage("mainForm:panel", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
            } catch (Exception e){
                SessionUtil.setMessage("mainForm:panel", "error.exception", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            SessionUtil.setMessage("mainForm:panel", "nomenclatureScreen.error.title", FacesMessage.SEVERITY_ERROR);
        }
        return false;
    }

    private boolean validate(){
//        return validator.validate(person, edit);
        return true;
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
