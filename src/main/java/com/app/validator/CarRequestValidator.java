package com.app.validator;

import com.app.data.entity.CarRequest;
import com.app.data.entity.Nomenclature;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named("carRequestValidator")
@Scope("request")
public class CarRequestValidator  extends AbstractValidator<CarRequest>  {
    @Override
    public boolean validate(CarRequest entity, Object... args) {
        this.entity = entity;
        return true;
    }
}
