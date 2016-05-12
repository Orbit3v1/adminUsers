package converter;

import entity.Person;
import entity.Role;
import org.springframework.context.annotation.Scope;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Named("personConverter")
@Scope("request")
public class PersonConverter implements Converter {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return s.equals("") ? null : em.find(Person.class, Integer.valueOf(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        return o == null ? "" : String.valueOf(((Person) o).getId());
    }
}
