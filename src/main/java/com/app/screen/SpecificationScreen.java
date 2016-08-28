package com.app.screen;

import com.app.entity.Order;
import com.app.entity.Person;
import com.app.entity.Role;
import com.app.entity.Specification;
import com.app.utils.AppUtil;
import com.app.utils.EntityUtil;
import com.app.utils.Security;
import com.app.utils.SessionUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

@Named("specificationScreen")
@Scope("view")
public class SpecificationScreen extends EntityScreen<Specification>{

    private List<Person> developers;

    @PostConstruct
    public void init() {
        logger.info("init");
        initEntity();

        developers = EntityUtil.getDevelopers(em);
    }

    @Override
    protected String getScreenName() {
        return "orderScreen";
    }

    @Transactional
    public void initEntity() {
        String id = SessionUtil.getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            entity = em.find(Specification.class, AppUtil.toInteger(id));
            edit = true;
        } else {
            entity = new Specification();
            entity.setResponsible(Security.getCurrentUser());
            entity.setStart(new Date());
        }

    }

    @Override
    public void save() {
        saveData();
    }

    @Transactional
    private void saveData() {
        entity = em.merge(entity);
    }
}
