package com.app.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ResourceBundle;

@Named("addMessage")
@Scope("session")
public class AddMessageImp implements AddMessage{

    @Inject
    protected ResourceBundle resourceBundle;

    public void setMessage(String componentId, String bundleKey, FacesMessage.Severity type){

        String message = resourceBundle.containsKey(bundleKey) ? resourceBundle.getString(bundleKey) : bundleKey;
        FacesMessage facesMessage = new FacesMessage(type, "", message);
        FacesContext.getCurrentInstance().addMessage(componentId, facesMessage);

    }
}
