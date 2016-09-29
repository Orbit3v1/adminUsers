package com.app.web.list;

import com.app.data.entity.TNC1C;
import com.app.web.Loggable;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.*;
import java.util.List;



@Named("TNC1CList")
@Scope("view")
public class TNC1CList {
    @PersistenceContext
    protected EntityManager em;
    protected List<TNC1C> entities;
    protected List<TNC1C> filteredEntities;

    @Loggable
    @PostConstruct
    public void init(){
        entities = getData();
        filteredEntities = entities;
    }

    protected List<TNC1C> getData(){
        Query query = em.createNativeQuery(
                "select n._IDRRef id, " +
                "  cast(n._description as varchar(500)) as name, " +
                "  cast(s._description as varchar(500)) as unit, " +
                "  p._fld8268 as price, " +
                "  case when c.id is not null then 'Y' else 'N' end as exist " +
                "from owner1C.dbo._Reference66 n " +
                "join owner1C.dbo._Reference46 s on n._fld1029rref = s._IDRRef " +
                "left join owner1C.dbo._InfoRg8264 p on p._fld8266rref = n._IDRRef " +
                "left join calc_tnc c on c.link1c = n._IDRRef",
                "TNCMapping"
        );
        return query.getResultList();
    }

    public void select(TNC1C tnc) {
        RequestContext.getCurrentInstance().closeDialog(tnc);
    }

    public List<TNC1C> getEntities() {
        return entities;
    }

    public void setEntities(List<TNC1C> entities) {
        this.entities = entities;
    }

    public List<TNC1C> getFilteredEntities() {
        return filteredEntities;
    }

    public void setFilteredEntities(List<TNC1C> filteredEntities) {
        this.filteredEntities = filteredEntities;
    }
}
