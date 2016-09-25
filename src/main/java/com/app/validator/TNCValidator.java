package com.app.validator;

import com.app.data.entity.TNC;
import com.app.data.entity.Work;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named("TNCValidator")
@Scope("request")
public class TNCValidator extends AbstractValidator<TNC>  {
    @Override
    public boolean validate(TNC entity, Object... args) {
        this.entity = entity;
        return true;
    }

}
