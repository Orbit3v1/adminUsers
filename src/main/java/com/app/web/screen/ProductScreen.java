package com.app.web.screen;

import com.app.data.entity.Person;
import com.app.data.entity.Product;
import com.app.data.entity.Role;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;
import org.primefaces.event.CellEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Named("productScreen")
@Scope("view")
public class ProductScreen {

    @PersistenceContext
    protected EntityManager em;

    private TreeNode root;
    private Product entity;
    private Map<Product, TreeNode> connector;

    @PostConstruct
    public void init() {
        String id = SessionUtil.getParameter("id");
        if(id != null && AppUtil.isNumeric(id)){
            entity = em.find(Product.class, AppUtil.toInteger(id));
            entity.getSubordinates();
        } else{
            entity = new Product();
        }
        initRoot();
    }

    private void initRoot(){
        root = new DefaultTreeNode();
        connector = new HashMap<>();
        TreeNode tn = new DefaultTreeNode(entity, root);
        connector.put(entity, tn);
        initSubordinates(entity, tn);
    }

    @Transactional
    private void initSubordinates(Product product, TreeNode parentNode){
        product = em.find(Product.class, product.getId());
        for(Product p : product.getSubordinates()){
            TreeNode tn = new DefaultTreeNode(p, parentNode);
            connector.put(p, tn);
            initSubordinates(p, tn);
        }
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    @Transactional
    public void save(){
        for(Product p : connector.keySet()){
            p =  em.merge(p);
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
