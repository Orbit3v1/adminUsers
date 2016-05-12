package converter;

import entity.Nomenclature;
import entity.Person;
import org.springframework.context.annotation.Scope;
import utils.SessionUtil;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

@Named("nomenclatureConverter")
@Scope("request")
public class NomenclatureConverter implements Converter {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Object res = null;
        if(!s.equals("")) {
            EntityManager em = entityManagerFactory.createEntityManager();
            Query query = em.createQuery("select r from Nomenclature r where r.name = :name")
                    .setParameter("name", s);
            if (query.getResultList().size() != 0) {
                res = query.getResultList().get(0);
            } else {
                res = new Nomenclature(s);
            }
        }
        return res;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o == null ? "" : ((Nomenclature) o).getName();
    }
}
