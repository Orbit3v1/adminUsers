package com.app.converter;

import com.app.data.entity.Person;
import org.springframework.context.annotation.Scope;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named("personConverter")
@Scope("request")
public class PersonConverter implements Converter {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return s.equals("") ? null : em.find(Person.class, Integer.valueOf(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        return o == null ? "" : String.valueOf(((Person) o).getId());
    }
}
