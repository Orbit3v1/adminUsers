package com.app.validator;

import com.app.data.entity.OrderItem;
import com.app.utils.AppUtil;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.inject.Named;

@Named("orderItemValidator")
@Scope("request")
public class OrderItemValidator extends AbstractValidator<OrderItem> {

    protected boolean edit;
    protected String count;

    @Override
    public boolean validate(OrderItem orderItem, Object... args) {
        this.entity = orderItem;
        edit = args.length > 0 ? (Boolean) args[0] : false;
        count = args.length > 1 ? (String) args[1] : "";
        return isValidNomenclature() & isValidCount();
    }

    protected boolean isValidNomenclature() {
        boolean valid = true;
        if (entity.getNomenclature() == null) {
            valid = false;
            addMessage.setMessage("mainForm:nomenclature", "error.notNull", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

    protected boolean isValidCount() {
        boolean valid = true;
        if (count == null || count.equals("")) {
            valid = false;
            addMessage.setMessage("mainForm:count", "error.notNull", FacesMessage.SEVERITY_ERROR);
        } else if (!AppUtil.isNumeric(count)) {
            valid = false;
            addMessage.setMessage("mainForm:count", "error.notNumber", FacesMessage.SEVERITY_ERROR);
        }
        return valid;
    }

}


