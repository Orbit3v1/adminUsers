package com.app.web.list;

import com.app.data.entity.Product;
import com.app.data.entity.Specification;
import com.app.data.entity.Work;
import com.app.utils.AddMessage;
import com.app.utils.Security;
import com.app.web.Loggable;
import org.apache.log4j.Logger;
import org.hibernate.annotations.QueryHints;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("productList")
@Scope("view")
public class ProductList extends EntityList<Product>{

    @Override
    protected Product createEntity() {
        return new Product();
    }

    @Override
    protected String getScreenName() {
        return "productList";
    }

    @Override
    protected List<Product> getData(){
        Query query = em.createQuery("select p from Product p where p.parent = null  order by p.name");
        return  query.getResultList();
    }
}

