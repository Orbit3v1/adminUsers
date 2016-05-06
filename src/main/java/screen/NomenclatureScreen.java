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
public class NomenclatureScreen extends EntityScreen<Nomenclature>{

    @PostConstruct
    public void init() {
        initSecurity();
        entity = new Nomenclature();
    }

    @Override
    protected String getScreenName() {
        return "nomenclatureScreen";
    }


    public boolean save(){
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

}
