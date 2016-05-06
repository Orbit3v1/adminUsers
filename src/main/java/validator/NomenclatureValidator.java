package validator;

import entity.Nomenclature;
import entity.Person;
import org.springframework.context.annotation.Scope;
import utils.SessionUtil;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ResourceBundle;

@Named("nomenclatureValidator")
@Scope("request")
public class NomenclatureValidator implements Validator<Nomenclature> {
    @Inject
    private EntityManagerFactory entityManagerFactory;
    @Inject
    ResourceBundle resourceBundle;

    private Nomenclature nomenclature;
    private boolean edit;

    @Override
    public boolean validate(Nomenclature nomenclature, Object... args) {
        this.nomenclature = nomenclature;
        edit = args.length > 0 ? (Boolean) args[0] : false;
        return isValidName();
    }

    private boolean isValidName(){
        boolean valid = true;
        if(nomenclature.getName().equals("")){
            valid = false;
            SessionUtil.setMessage("mainForm:name", "error.notNull", FacesMessage.SEVERITY_ERROR);
        } else {
            EntityManager em = entityManagerFactory.createEntityManager();
            Query query = em.createQuery("select p from Nomenclature p where p.name = :name and p.id != :id")
                    .setParameter("name", nomenclature.getName())
                    .setParameter("id", nomenclature.getId());
            if (query.getResultList().size() != 0) {
                valid = false;
                SessionUtil.setMessage("mainForm:name", "nomenclatureScreen.error.nameDuplicate", FacesMessage.SEVERITY_ERROR);
            }
        }
        return valid;
    }
}
