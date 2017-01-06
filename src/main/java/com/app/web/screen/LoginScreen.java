package com.app.web.screen;

import com.app.data.dao.PersonDao;
import com.app.data.dao.RoleDao;
import com.app.data.entity.Person;
import com.app.utils.AddMessage;
import com.app.security.Security;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import com.app.utils.SessionUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
    @Inject
    protected AddMessage addMessage;
    @Inject
    private PersonDao personDao;

    private String login;
    private String password;
    protected Logger logger = Logger.getLogger(getClass());

    public LoginScreen() {
    }

    @Transactional
    public String login(){
        logger.info("User login attempt. Login: " + login);
        Person user = personDao.getByLogin(login, DigestUtils.md5Hex(password));
        if (user == null) {
            addMessage.setMessage("mainForm:panelLogin", "loginScreen.error", FacesMessage.SEVERITY_ERROR);
            logger.info("User login fail (Wrong credentials). Login: " + login);
        } else if(!user.isActive()){
            addMessage.setMessage("mainForm:panelLogin", "loginScreen.inactive", FacesMessage.SEVERITY_ERROR);
            logger.info("User login fail (Inactive). Login: " + login);
        } else {
            SessionUtil.invalidateSession();
            SessionUtil.addSessionVariable("user", user);
            logger.info("User login successful. Login: " + login);
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
