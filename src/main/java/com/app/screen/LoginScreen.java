package com.app.screen;

import com.app.entity.Person;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import com.app.utils.SessionUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.ResourceBundle;

@Named("loginScreen")
@Scope("request")
public class LoginScreen {

    @PersistenceContext
    protected EntityManager em;
    @Inject
    ResourceBundle resourceBundle;

    private String login;
    private String password;

    @PostConstruct
    public void init() {

    }

    public LoginScreen() {
    }

    public String login(){
        Query query = em.createQuery("select p from Person p where p.login = :login and p.password = :password")
                .setParameter("login", login)
                .setParameter("password", DigestUtils.md5Hex(password));
        List<Person> persons = query.getResultList();
        if (persons.size() != 0) {
            Person user = persons.get(0);
            if(user.isActive()){
                SessionUtil.invalidateSession();
                SessionUtil.addSessionVariable("user", user);
            } else {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "errorTitle", resourceBundle.getString("loginScreen.inactive"));
                FacesContext.getCurrentInstance().addMessage("mainForm:panelLogin", facesMessage);
            }
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "errorTitle", resourceBundle.getString("loginScreen.error"));
            FacesContext.getCurrentInstance().addMessage("mainForm:panelLogin", facesMessage);
        }

        return "";
    }

    public String logout(){
        SessionUtil.removeSessionVariable("user");
        SessionUtil.invalidateSession();
        return "";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
