package com.app.validator;

import com.app.data.entity.TNCRequest;
import com.app.data.entity.TNCRequestItem;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Named;

@Named("TNCRequestItemValidator")
@Scope("request")
public class TNCRequestItemValidator extends AbstractValidator<TNCRequestItem> {
    @Override
    public boolean validate(TNCRequestItem entity, Object... args) {
        this.entity = entity;
        return isValidTNC();
    }

    protected boolean isValidTNC(){
        boolean valid = true;
        if (entity.getTnc() == null) {
            valid = false;
            addMessage.setMessage("mainForm:tnc", "error.notNull", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }
}
