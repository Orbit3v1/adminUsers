package converter;

import entity.Role;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@FacesConverter("roleConverter")
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
        return ((Role) o).getName();
    }
}
