package com.app.validator;

public interface Validator<T>{
    public boolean validate(T object, Object ... args);

}



