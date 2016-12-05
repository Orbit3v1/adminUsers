package com.app.web.screen;

import com.app.data.entity.Specification;
import com.app.data.entity.TNCLink;
import com.app.security.Security;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;
import com.app.web.Loggable;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityGraph;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Named("TNCLinkScreen")
@Scope("view")
public class TNCLinkScreen {
    private TNCLink editEntity;
    private String sessionKey;

    @Loggable
    @PostConstruct
    public void init(){
        initEntity();
    }

    public void initEntity() {
        String key = SessionUtil.getParameter("link");
        if(key != null){
            editEntity = (TNCLink) SessionUtil.getSessionVariable(key);
            sessionKey = key;
        }
        if(editEntity == null){
            editEntity = new TNCLink();
        }
    }

    public void save() {
        clearSession();
        RequestContext.getCurrentInstance().closeDialog(editEntity);
    }

    public void cancel(){
        clearSession();
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    private void clearSession(){
        SessionUtil.cleanSession(sessionKey);
    }

    public TNCLink getEditEntity() {
        return editEntity;
    }

    public void setEditEntity(TNCLink editEntity) {
        this.editEntity = editEntity;
    }
}
