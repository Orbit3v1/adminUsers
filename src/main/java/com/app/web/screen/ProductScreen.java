package com.app.web.screen;

import com.app.data.entity.Person;
import com.app.data.entity.Product;
import com.app.data.entity.Role;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Named("productScreen")
@Scope("view")
public class ProductScreen {

    @PersistenceContext
    protected EntityManager em;

    private TreeNode root;
    private Product entity;

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
        root = new DefaultTreeNode(entity);
        List<TreeNode> roots = new ArrayList<>();
        TreeNode root1 = new DefaultTreeNode(entity.getSubordinates().get(0),root );
        TreeNode root2 = new DefaultTreeNode(entity.getSubordinates().get(1),root1 );
        for(Product p : entity.getSubordinates()){
            roots.add(new DefaultTreeNode(p, root));
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
