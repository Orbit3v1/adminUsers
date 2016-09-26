package com.app.validator;

import com.app.data.entity.TNC;
import com.app.data.entity.Work;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Named;

@Named("TNCValidator")
@Scope("request")
public class TNCValidator extends AbstractValidator<TNC>  {
    @Override
    public boolean validate(TNC entity, Object... args) {
        this.entity = entity;
        return isValidName();
    }

    protected boolean isValidName() {
        boolean valid = true;
        if (entity.getName() == null || entity.getName().equals("")) {
            valid = false;
            addMessage.setMessage("mainForm:name", "error.notNull", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

}
