package com.app.validator;

import com.app.data.entity.Product;
import com.app.data.entity.ProductInParameter;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Named;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named("productInParametersValidator")
@Scope("request")
public class ProductInParametersValidator extends AbstractValidator<List<ProductInParameter>> {
    @Override
    public boolean validate(List<ProductInParameter> entity, Object... args) {
        this.entity = entity;
        return isValidName();
    }

    private boolean isValidName() {
        boolean valid = true;
        if (entity != null && entity.size() != 0 && !isNameUnique()) {
            addMessage.setMessage("mainForm:entities", "error.namesIsNotUnique", FacesMessage.SEVERITY_ERROR);
            valid = false;
        }
        return valid;
    }

    private boolean isNameUnique() {
        Set<String> uniqueNames = entity.stream().map(ProductInParameter::getName).collect(Collectors.toSet());
        return uniqueNames.size() == entity.size();
    }
}
