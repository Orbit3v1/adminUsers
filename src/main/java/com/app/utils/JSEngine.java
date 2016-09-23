package com.app.utils;

import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Named("JSEngine")
@Scope("request")
public class JSEngine {

    private ScriptEngine engine;

    @PostConstruct
    public void init(){
        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");
    }

    public String validate(String code){
        String err = null;
        try {
            engine.eval(code);
        } catch (ScriptException e) {
            err = e.getMessage();
        }
        return err;
    }

    public String calculate(String code) throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String result = engine.eval(code).toString();
        return result;
    }
}
