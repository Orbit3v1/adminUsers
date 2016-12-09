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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Named("TNCRequestScreen")
@Scope("view")
public class TNCRequestScreen extends EntityScreen<TNCRequest>{

    private List<Person> developers;

    @Loggable
    @PostConstruct
    public void init() {
        initEntity();
        developers = EntityUtil.getDevelopers(em);
    }

    @Transactional
    public void initEntity() {
        String id = SessionUtil.getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            entity = em.find(TNCRequest.class, AppUtil.toInteger(id));
            entity.getTncRequestItems().size();
            edit = true;
        } else {
            entity = new TNCRequest();
            entity.setCreator(Security.getCurrentUser());
            entity.setResponsible(Security.getCurrentUser());
            entity.setStart(new Date());
        }
    }

    @Override
    protected String getScreenName() {
        return "TNCRequestScreen";
    }

    @Override
    @Transactional
    protected void save() {
        entity = em.merge(entity);
    }

    public List<Person> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Person> developers) {
        this.developers = developers;
    }
}
