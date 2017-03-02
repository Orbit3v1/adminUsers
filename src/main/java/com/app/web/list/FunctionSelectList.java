package com.app.web.list;

import com.app.data.entity.Function;
import com.app.data.entity.FunctionInParameter;
import com.app.security.Security;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.*;

@Named("functionSelectList")
@Scope("view")
public class FunctionSelectList extends FunctionList {

    private Function selectedEntity;
    private List<FunctionInParameterValue> inParameters = new ArrayList<>();

    public void select() {
        RequestContext.getCurrentInstance().closeDialog(getFunction());
    }

    private String getFunction(){
        String result = "";
        if(selectedEntity != null){
            result += selectedEntity.getName();
            result += "(" + getParameters() + ")";
        }
        return result;
    }

    private String getParameters(){
        String result = "";
        StringJoiner sj = new StringJoiner(",");
        for(FunctionInParameterValue inParameter : inParameters){
            sj.add(inParameter.getValue());
        }
        return result + sj.toString();
    }

    public void cancel(){
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public void functionSelected(){
        inParameters = new ArrayList<>();
        for(FunctionInParameter inParameter : selectedEntity.getInParameters()){
            inParameters.add(new FunctionInParameterValue(inParameter, inParameter.getName()));
        }
    }

    public Function getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(Function selectedEntity) {
        this.selectedEntity = selectedEntity;
    }

    public List<FunctionInParameterValue> getInParameters() {
        return inParameters;
    }

    public void setInParameters(List<FunctionInParameterValue> inParameters) {
        this.inParameters = inParameters;
    }

    public class FunctionInParameterValue extends FunctionInParameter{
        private String value;

        public FunctionInParameterValue(FunctionInParameter functionInParameter, String value) {
            setName(functionInParameter.getName());
            setDescription(functionInParameter.getDescription());
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


}
