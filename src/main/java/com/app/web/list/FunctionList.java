package com.app.web.list;

import com.app.data.entity.Function;
import com.app.web.Loggable;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.Query;

@Named("functionList")
@Scope("view")
public class FunctionList extends EntityList<Function>{

    @Override
    protected Function createEntity() {
        return new Function();
    }

    @Override
    protected String getScreenName() {
        return "functionList";
    }

    @Loggable
    @PostConstruct
    public void init(){
        super.init();
        Query query = em.createQuery("select p from Function p order by p.name");
        entities = query.getResultList();
    }

    public void select(Function entity) {
        RequestContext.getCurrentInstance().closeDialog(entity);
    }



}
