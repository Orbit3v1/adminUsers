package com.app.converter;

import com.app.entity.Role;
import org.springframework.context.annotation.Scope;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named("roleConverter")
@Scope("request")
public class RoleConverter implements Converter {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return em.find(Role.class, s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return ((Role) o).getId();
    }

}
