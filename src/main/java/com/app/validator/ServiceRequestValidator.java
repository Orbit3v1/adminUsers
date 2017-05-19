package com.app.validator;

import com.app.data.entity.CarRequest;
import com.app.data.entity.ServiceRequest;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named("serviceRequestValidator")
@Scope("request")
public class ServiceRequestValidator extends AbstractValidator<ServiceRequest>{
    @Override
    public boolean validate(ServiceRequest entity, Object... args) {
        this.entity = entity;
        return true;
    }
}
