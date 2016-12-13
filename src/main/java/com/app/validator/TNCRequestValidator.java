package com.app.validator;

import com.app.data.entity.TNC;
import com.app.data.entity.TNCRequest;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Named;

@Named("TNCRequestValidator")
@Scope("request")
public class TNCRequestValidator extends AbstractValidator<TNCRequest> {
    @Override
    public boolean validate(TNCRequest entity, Object... args) {
        this.entity = entity;
        return isValidName() & isValidItems();
    }

    protected boolean isValidName() {
        boolean valid = true;
        if (entity.getName() == null || entity.getName().equals("")) {
            valid = false;
            addMessage.setMessage("mainForm:panel:name", "error.notNull", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected boolean isValidItems() {
        boolean valid = true;
        if (entity.getTncRequestItems() == null || entity.getTncRequestItems().size() == 0) {
            valid = false;
            addMessage.setMessage("mainForm:panel:items", "TNCRequestScreen.error.emptyItems", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }
}
