package com.app.web.list;

import com.app.data.entity.Product;
import com.app.data.entity.Work;
import com.app.utils.AddMessage;
import com.app.utils.Security;
import com.app.web.Loggable;
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

@Named("productList")
@Scope("request")
public class ProductList {
    @PersistenceContext
    private EntityManager em;
    @Inject
    protected AddMessage addMessage;
    protected Logger logger = Logger.getLogger(getClass());

    private List<Product> products;
    private Map<String, Boolean> userPA;

    @Loggable
    @PostConstruct
    public void init(){
        Query query = em.createQuery("select p from Product p where p.parent = null order by p.name");
        products = query.getResultList();
        userPA = Security.getUserPrivilegeAction("personList");
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

