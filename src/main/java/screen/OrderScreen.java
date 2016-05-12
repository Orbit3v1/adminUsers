package screen;

import entity.Nomenclature;
import entity.Order;
import entity.Person;
import org.springframework.context.annotation.Scope;
import utils.SessionUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Named("orderScreen")
@Scope("session")
public class OrderScreen extends EntityScreen<Order> {

    @Inject
    Locale locale;

    private String count;
    private List<Person> developers;
    private List<Nomenclature> nomenclatures;

    @PostConstruct
    public void init() {
        entity = new Order();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select r from Person r order by r.lastName, r.firstName");
        developers = query.getResultList();

        query = em.createQuery("select r from Nomenclature r order by r.name");
        nomenclatures = query.getResultList();
    }

    @Override
    protected String getScreenName() {
        return "orderScreen";
    }

    @Override
    public String editEntity(Order entity) {
        count = String.valueOf(entity.getCount());
        return super.editEntity(entity);
    }

    public List<Nomenclature> autocomplete(String prefix) {
        List<Nomenclature> result;
        if ((prefix == null) || (prefix.length() == 0)) {
            result = nomenclatures;
        } else {
            result = nomenclatures.stream()
                    .filter(s -> s.getName().toLowerCase().startsWith(prefix.toLowerCase()))
                    .collect(Collectors.toList());
        }
        return result;
    }

    @Override
    public boolean save() {
        if (validate()) {
            entity.setCount(Integer.valueOf(count));
            try {
                EntityManager em = entityManagerFactory.createEntityManager();
                em.getTransaction().begin();
                entity = em.merge(entity);
                em.getTransaction().commit();
                em.close();

                String bundleKey = edit ? "orderScreen.success.edit" : "orderScreen.success.save";
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
            SessionUtil.setMessage("mainForm:panel", "orderScreen.error.title", FacesMessage.SEVERITY_ERROR);
        }
        return false;
    }

    private boolean validate() {
        //return validator.validate(entity, edit);
        return true;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<Person> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Person> developers) {
        this.developers = developers;
    }
}
