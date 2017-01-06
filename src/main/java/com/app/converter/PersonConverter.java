package com.app.converter;

import com.app.data.entity.Person;
import com.app.utils.EntityUtil;
import org.springframework.context.annotation.Scope;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named("personConverter")
@Scope("request")
public class PersonConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Object person = null;
        if(!s.equals("")){
            List<Person> persons = EntityUtil.getDevelopers();
            for(Person p : persons){
                if(p.getId().equals(Integer.valueOf(s))){
                    person = p;
                    break;
                }
            }
        }
        return person;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        return o == null ? "" : String.valueOf(((Person) o).getId());
    }
}
