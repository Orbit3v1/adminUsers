package com.app.web.list;

import com.app.data.entity.Function;
import com.app.data.entity.FunctionInParameter;
import com.app.web.Loggable;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.Comparator;
import java.util.List;

@Named("functionList")
@Scope("view")
public class FunctionList extends EntityList<Function>{

    private FunctionInParameter selectedParameter;

    @Override
    protected Function createEntity() {
        return new Function();
    }

    @Override
    protected String getScreenName() {
        return "functionList";
    }

    @Override
    protected List<Function> getData(){
        Query query = em.createQuery("select distinct p from Function p " +
                "left join fetch p.inParameters ip " +
                "order by p.name, ip.position");
        return  query.getResultList();
    }

    public void delete(FunctionInParameter parameter){
        editEntity.getInParameters().remove(parameter);
    }

    public void addParameter(){
        FunctionInParameter parameter = new FunctionInParameter();
        parameter.setFunction(editEntity);
        parameter.setPosition(getMaxPosition(editEntity) + 1);
        editEntity.getInParameters().add(parameter);
    }

    public int getMaxPosition(Function function){
        if(function.getInParameters().size() == 0){
            return 0;
        }
        return function.getInParameters().stream().max((x,y)-> x.getPosition() - y.getPosition()).get().getPosition();
    }

    public FunctionInParameter getSelectedParameter() {
        return selectedParameter;
    }

    public void setSelectedParameter(FunctionInParameter selectedParameter) {
        this.selectedParameter = selectedParameter;
    }




}
