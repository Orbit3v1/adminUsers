package list;

import entity.Nomenclature;
import entity.Person;
import org.springframework.context.annotation.Scope;
import utils.Security;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Named("nomenclatureList")
@Scope("request")
public class NomenclatureList {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    private List<Nomenclature> nomenclatures;
    private Map<String, Boolean> userPA;

    @PostConstruct
    public void init(){
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select p from Nomenclature p order by p.name");
        nomenclatures = query.getResultList();
        userPA = Security.getUserPrivilegeAction("nomenclatureList");
    }

    public List<Nomenclature> getNomenclatures() {
        return nomenclatures;
    }

    public void setNomenclatures(List<Nomenclature> nomenclatures) {
        this.nomenclatures = nomenclatures;
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }
}
