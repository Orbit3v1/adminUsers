package converter;

import entity.Role;
import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Named("roleConverter")
@Scope("request")
public class RoleConverter implements Converter {

    @Inject
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(Role.class, s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return ((Role) o).getId();
    }

}
