package com.app.web.screen;

import com.app.data.entity.*;
import com.app.security.Security;
import com.app.utils.AppUtil;
import com.app.utils.EntityUtil;
import com.app.utils.SessionUtil;
import com.app.web.Loggable;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.Date;

@Named("tncSupplyScreen")
@Scope("view")
public class TNCSupplyScreen extends EntityScreen<TNCSupply>{

    @Override
    protected String getScreenName() {
        return "tncSupplyScreen";
    }

    @Loggable
    @PostConstruct
    public void init() {
        initEntity();
    }

    @Transactional
    public void initEntity() {
        String id = SessionUtil.getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            entity = em.find(TNCSupply.class, AppUtil.toInteger(id));
            entity.getTncSupplyItems().size();
            edit = true;
        } else {
            entity = new TNCSupply();
            entity.setStart(new Date());
        }
    }

    @Override
    @Transactional
    protected void save() {
        entity = em.merge(entity);
    }

    public void setEndActual(){
        entity.setEndActual(new Date());
    }

    @Loggable
    public void cancelEndActual(){
        entity.setEndActual(null);
    }

    public void delete(TNCSupplyItem item){
        entity.getTncSupplyItems().remove(item);
    }

    public void shareEntity(){
        SessionUtil.addSessionVariable("TNCSupply" + entity.getId(), entity);
    }

    @Override
    protected void clearCash(){
        SessionUtil.removeSessionVariable("TNCSupply" + entity.getId());
    }

    @Loggable
    @Transactional
    public void refresh() {
        for (TNCSupplyItem item : entity.getTncSupplyItems()) {
            item.setTnc(em.find(TNC.class, item.getTnc().getId()));
        }
    }
}
