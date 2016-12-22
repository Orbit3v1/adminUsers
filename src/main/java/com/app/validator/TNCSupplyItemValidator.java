package com.app.validator;

import com.app.data.entity.TNCRequestItem;
import com.app.data.entity.TNCSupplyItem;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Named;

@Named("TNCSupplyItemValidator")
@Scope("request")
public class TNCSupplyItemValidator extends AbstractValidator<TNCSupplyItem> {
    @Override
    public boolean validate(TNCSupplyItem entity, Object... args) {
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
