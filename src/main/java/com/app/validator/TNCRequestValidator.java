package com.app.validator;

import com.app.data.entity.TNC;
import com.app.data.entity.TNCRequest;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named("TNCRequestValidator")
@Scope("request")
public class TNCRequestValidator extends AbstractValidator<TNCRequest> {
    @Override
    public boolean validate(TNCRequest entity, Object... args) {
        this.entity = entity;
        return true;
    }
}
