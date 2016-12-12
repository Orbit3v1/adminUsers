package com.app.validator;

import com.app.data.entity.TNCRequest;
import com.app.data.entity.TNCRequestItem;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named("TNCRequestItemValidator")
@Scope("request")
public class TNCRequestItemValidator extends AbstractValidator<TNCRequestItem> {
    @Override
    public boolean validate(TNCRequestItem entity, Object... args) {
        this.entity = entity;
        return true;
    }
}
