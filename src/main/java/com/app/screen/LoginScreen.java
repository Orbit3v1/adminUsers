package com.app.screen;

import com.app.entity.Person;
import com.app.utils.AddMessage;
import com.app.utils.Security;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import com.app.utils.SessionUtil;
import org.springframework.transaction.annotation.Transactional;

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
import org.slf4j.MDC;

@Named("loginScreen")
@Scope("request")
public class LoginScreen {

    @PersistenceContext
    protected EntityManager em;
    @Inject
    ResourceBundle resourceBundle;
    @Inject
    protected AddMessage addMessage;

    private String login;
    private String password;
    protected Logger logger = Logger.getLogger(getClass());

    @PostConstruct
    public void init() {

    }

    public LoginScreen() {
    }

    @Transactional
    public String login(){
        logger.info("User login attempt. Login: " + login);
        Query query = em.createQuery("select p from Person p where p.login = :login and p.password = :password")
                .setParameter("login", login)
                .setParameter("password", DigestUtils.md5Hex(password));
        List<Person> persons = query.getResultList();
        if (persons.size() != 0) {
            Person user = persons.get(0);
            if(user.isActive()){
                user.getRoles().size();
                SessionUtil.invalidateSession();
                SessionUtil.addSessionVariable("user", user);
                logger.info("User login successful. Login: " + login);
            } else {
                addMessage.setMessage("mainForm:panelLogin", "loginScreen.inactive", FacesMessage.SEVERITY_ERROR);
                logger.info("User login fail (Inactive). Login: " + login);
            }
        } else {
            addMessage.setMessage("mainForm:panelLogin", "loginScreen.error", FacesMessage.SEVERITY_ERROR);
            logger.info("User login fail (Wrong credentials). Login: " + login);
        }

        return "";
    }

    public String logout(){
        Person user = Security.getCurrentUser();
        logger.info("User logout. User: " + (user == null ? "" : user.getLogin()));
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
