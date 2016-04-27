package utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.Objects;

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
}
