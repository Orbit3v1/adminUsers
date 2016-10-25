package com.app.web.list;

import com.app.data.entity.Work;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named("workList")
@Scope("view")
public class WorkList extends EntityList<Work>{

    @Override
    protected Work createEntity() {
        return new Work();
    }

    @Override
    protected String getScreenName() {
        return "workList";
    }

    @Override
    protected List<Work> getData(){
        Query query = em.createQuery("select p from Work p order by p.name");
        return  query.getResultList();
    }

    public void select(Work work) {
        RequestContext.getCurrentInstance().closeDialog(work);
    }

    @Override
    protected boolean canDelete(Work entity){
        Work work = em.find(Work.class, entity.getId());
        return super.canDelete(entity)
                && work.getProducts().size() == 0;
    }
}
