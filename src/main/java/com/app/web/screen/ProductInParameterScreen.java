package com.app.web.screen;

import com.app.data.entity.ProductInParameter;
import com.app.data.entity.TNCLink;
import com.app.utils.SessionUtil;
import com.app.validator.Validator;
import com.app.web.Loggable;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named("productInParameterScreen")
@Scope("view")
public class ProductInParameterScreen {

    @Inject
    Validator<List<ProductInParameter>> validator;

    private List<ProductInParameter> inParameters;
    private String sessionKey;

    @Loggable
    @PostConstruct
    public void init() {
        initEntity();
    }

    public void initEntity() {
        sessionKey = SessionUtil.getParameter("inParameters");

        List<ProductInParameter> tmpInParameters = getInData();
        inParameters = tmpInParameters.stream().map(ProductInParameter::new).collect(Collectors.toList());
    }

    private List<ProductInParameter> getInData() {
        List<ProductInParameter> tmpInParameters = new ArrayList<>();
        if (sessionKey != null) {
            tmpInParameters = (List<ProductInParameter>) SessionUtil.getSessionVariable(sessionKey);
        }
        return tmpInParameters;
    }

    public void save() {
        if (validator.validate(inParameters)) {
            clearSession();
            RequestContext.getCurrentInstance().closeDialog(inParameters);
        }
    }

    public void cancel() {
        clearSession();
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public void add() {
        inParameters.add(new ProductInParameter());
    }

    public void delete(ProductInParameter row) {
        inParameters.remove(row);
    }

    private void clearSession() {
        SessionUtil.cleanSession(sessionKey);
    }

    public List<ProductInParameter> getInParameters() {
        return inParameters;
    }

    public void setInParameters(List<ProductInParameter> inParameters) {
        this.inParameters = inParameters;
    }
}
