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
public class ProductList {
    @PersistenceContext
    private EntityManager em;
    @Inject
    protected AddMessage addMessage;
    protected Logger logger = Logger.getLogger(getClass());

    private List<Product> products;
    private Map<String, Boolean> userPA;
    private Product editEntity;

    @Loggable
    @PostConstruct
    public void init(){

//        EntityGraph<Product> graph = em.createEntityGraph(Product.class);
//        graph.addAttributeNodes("name");
//        Query query = em.createQuery("select p from Product p where p.parent = null order by p.name").setHint(QueryHints.FETCHGRAPH, graph);

        Query query = em.createQuery("select p from Product p where p.parent = null order by p.name");
        products = query.getResultList();
        userPA = Security.getUserPrivilegeAction("personList");
        editEntity = new Product();
    }

    public void add(){
        editEntity = new Product();
    }

    public void save(){
        if (validate()) {
            try {
                saveAttempt();
            } catch (OptimisticLockException e){
                logger.error(e.getMessage());
                e.printStackTrace();
                addMessage.setMessage("mainForm:panel", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
            } catch (Exception e){
                logger.error(e.getMessage());
                e.printStackTrace();
                addMessage.setMessage("mainForm:panel", "error.exception", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            addMessage.setMessage("mainForm:panel", ".error.title", FacesMessage.SEVERITY_ERROR);
        }
    }

    private boolean validate() {
        return true;
    }

    @Transactional
    private void saveAttempt(){
        editEntity = em.merge(editEntity);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getEditEntity() {
        return editEntity;
    }

    public void setEditEntity(Product editEntity) {
        this.editEntity = editEntity;
    }
}

