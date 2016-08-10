package com.app.validator;

import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named("validator")
@Scope("request")
public interface Validator<T>{

    public boolean validate(T object, Object ... args);

}



