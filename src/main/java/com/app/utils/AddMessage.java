package com.app.utils;


import javax.faces.application.FacesMessage;

public interface AddMessage {

    public void setMessage(String componentId, String bundleKey, FacesMessage.Severity type);

}
