package com.app.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class SessionUtil {

    public static void cleanSession(String beanName){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.removeAttribute(beanName);
    }

    public static <T> void addSessionVariable(String key, T value){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
    }

    public static void removeSessionVariable(String key){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, null);
    }

    public static Object getSessionVariable(String key){
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
    }

    public static void invalidateSession(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public static void setMessage(String componentId, String bundleKey, FacesMessage.Severity type){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ResourceBundle resourceBundle = context.getBean("messages", ResourceBundle.class);

        FacesMessage facesMessage = new FacesMessage(type, "", resourceBundle.getString(bundleKey));
        FacesContext.getCurrentInstance().addMessage(componentId, facesMessage);

    }



}
