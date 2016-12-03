package com.app.web.screen;

import com.app.data.entity.TNCLink;
import com.app.web.Loggable;
import org.primefaces.context.RequestContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Named("TNCLinkScreen")
@Scope("view")
public class TNCLinkScreen {
    private TNCLink editEntity;

    @Loggable
    @PostConstruct
    public void init(){
        editEntity = new TNCLink();
    }

    public void save() {
        RequestContext.getCurrentInstance().closeDialog(editEntity);
    }

    public void cancel(){
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public TNCLink getEditEntity() {
        return editEntity;
    }

    public void setEditEntity(TNCLink editEntity) {
        this.editEntity = editEntity;
    }
}
