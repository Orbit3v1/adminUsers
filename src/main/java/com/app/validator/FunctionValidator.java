package com.app.validator;

import com.app.common.FunctionCodeGenerator;
import com.app.data.entity.Function;
import com.app.data.entity.FunctionInParameter;
import com.app.data.entity.ProductInParameter;
import com.app.data.entity.Role;
import com.app.utils.JSEngine;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named("functionValidator")
@Scope("request")
public class FunctionValidator extends AbstractValidator<Function> {

    @Inject
    JSEngine jsEngine;
    @Inject
    FunctionCodeGenerator functionCodeGenerator;

    @Override
    public boolean validate(Function entity, Object... args) {
        this.entity = entity;
        return isValidCode() & isValidName() & isValidParameters();
    }

    private boolean isValidCode() {
        boolean valid = true;
        String err = jsEngine.validate(functionCodeGenerator.generate(entity));
        if (err != null) {
            valid = false;
            addMessage.setMessage("mainForm:code", err, FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected boolean isValidName() {
        boolean valid = true;
        if(entity.getName() == null || entity.getName().equals("")){
            valid = false;
            addMessage.setMessage("mainForm:name", "error.notNull", FacesMessage.SEVERITY_ERROR);
        } else if (getEntityWithSameName().size() != 0) {
            valid = false;
            addMessage.setMessage("mainForm:name", "functionList.error.nameDuplicate", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected List<Role> getEntityWithSameName() {
        Query query = em.createQuery("select r from Function r where r.name = :name and r.id != :id")
                .setParameter("name", entity.getName())
                .setParameter("id", entity.getId());
        return query.getResultList();
    }

    private boolean isValidParameters() {
        boolean valid = true;
        if (entity != null && entity.getInParameters().size() != 0 && !isParametersUnique()) {
            addMessage.setMessage("mainForm:parameters", "error.namesIsNotUnique", FacesMessage.SEVERITY_ERROR);
            valid = false;
        }
        return valid;
    }

    private boolean isParametersUnique() {
        Set<String> uniqueNames = entity.getInParameters().stream().map(FunctionInParameter::getName).collect(Collectors.toSet());
        return uniqueNames.size() == entity.getInParameters().size();
    }

}
