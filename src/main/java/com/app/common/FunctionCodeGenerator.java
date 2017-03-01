package com.app.common;

import com.app.data.entity.Function;
import com.app.data.entity.FunctionInParameter;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.util.StringJoiner;

@Named("functionCodeGenerator")
@Scope("request")
public class FunctionCodeGenerator {

    public String generate(Function function){
        String result = "";
        if(function != null){
            result += "function " + function.getName(); ;
            result += "(" + getInParameters(function) + ")";
            result += "{" + function.getCode() + "}";
        }
        return result;
    }

    private String getInParameters(Function function){
        String result = "";
        StringJoiner sj = new StringJoiner(",");
        for(FunctionInParameter inParameter : function.getInParameters()){
            sj.add(inParameter.getName());
        }
        return result + sj.toString();
    }
}
