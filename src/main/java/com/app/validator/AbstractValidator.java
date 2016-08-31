package com.app.validator;

import com.app.entity.Person;
import com.app.utils.AddMessage;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractValidator<T> implements Validator<T>{

    @PersistenceContext
    protected EntityManager em;
    @Inject
    protected  AddMessage addMessage;

    protected T entity;

    @Override
    public abstract boolean validate(T entity, Object ... args);

}

