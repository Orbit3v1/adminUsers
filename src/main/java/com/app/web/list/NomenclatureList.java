package com.app.web.list;

import com.app.data.entity.Nomenclature;
import com.app.web.Loggable;
import org.springframework.context.annotation.Scope;
import com.app.security.Security;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Named("nomenclatureList")
@Scope("request")
public class NomenclatureList {

    @PersistenceContext
    protected EntityManager em;

    private List<Nomenclature> nomenclatures;
    private Map<String, Boolean> userPA;
    private String nameFilter;

    @Loggable
    @PostConstruct
    public void init(){
        Query query = em.createQuery("select p from Nomenclature p " +
                "left join fetch p.specification " +
                "order by p.name");
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

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }
}
