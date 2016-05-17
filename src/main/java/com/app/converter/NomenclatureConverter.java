package com.app.converter;

import com.app.entity.Nomenclature;
import org.springframework.context.annotation.Scope;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Named("nomenclatureConverter")
@Scope("request")
public class NomenclatureConverter implements Converter {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Object res = null;
        if(!s.equals("")) {
            Query query = em.createQuery("select r from Nomenclature r where r.name = :name")
                    .setParameter("name", s);
            List<Nomenclature> resList = query.getResultList();
            if (resList.size() != 0) {
                res = resList.get(0);
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
