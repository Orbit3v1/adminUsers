package com.app.list;

import com.app.entity.OrderItem;
import com.app.entity.Role;
import com.app.entity.Specification;
import com.app.utils.AddMessage;
import com.app.utils.Security;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Named("specificationList")
@Scope("session")
public class SpecificationList {
    @PersistenceContext
    private EntityManager em;
    private Logger logger = Logger.getLogger(getClass());

    @Inject
    private AddMessage addMessage;

    private List<Specification> specifications;
    private Map<String, Boolean> userPA;


    @PostConstruct
    public void init(){
        Query query = em.createQuery("select r from Specification r order by r.id");
        specifications = query.getResultList();
        userPA = Security.getUserPrivilegeAction("specificationList");
    }

    public void updateList() {
        initList();
    }

    private void initList() {
        logger.info("initList");

        //TODO
    }


    public List<Specification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }
}
