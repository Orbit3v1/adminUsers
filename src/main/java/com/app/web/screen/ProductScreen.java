package com.app.web.screen;

import com.app.data.entity.*;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;

@Named("productScreen")
@Scope("view")
public class ProductScreen {

    @PersistenceContext
    protected EntityManager em;

    private TreeNode root;
    private TreeNode rootCalc;
    private Product entity;
    //    private List<Product> deleteProducts;
    private TreeNode selectedNode;
    //   private Map<Product, TreeNode> connector;

    @PostConstruct
    public void init() {
        String id = SessionUtil.getParameter("id");
//        deleteProducts = new ArrayList<>();
        if (id != null && AppUtil.isNumeric(id)) {
            entity = em.find(Product.class, AppUtil.toInteger(id));
            entity.getSubordinates();
        } else {
            entity = new Product();
        }
        initRoot();
    }

    private void initRoot() {
        root = new DefaultTreeNode();
        TreeNode tn = new DefaultTreeNode(entity, root);
        tn.setExpanded(true);
        initSubordinates(entity, tn);
        int a = 1;
    }

    @Transactional
    private void initSubordinates(Product product, TreeNode parentNode) {
        // product = em.find(Product.class, product.getId());
        for (Product p : product.getSubordinates()) {
            TreeNode tn = new DefaultTreeNode(p, parentNode);
            tn.setExpanded(true);
            initSubordinates(p, tn);
        }
    }

    public void addNodeTo(TreeNode node) {
        Product product = new Product();
        add(node, product);
    }

    private void add(TreeNode node, Product product) {
        Product parentProduct = (Product) node.getData();
        product.setParent(parentProduct);
        parentProduct.getSubordinates().add(product);
        TreeNode tn = new DefaultTreeNode(product, node);
        tn.setExpanded(true);
    }

    public void addProduct(){
        Product product = new Product();
        add(selectedNode, product);
    }

    public void delete(){
        deleteNode(selectedNode);
    }

    public void deleteNode(TreeNode node) {
        TreeNode parentNode = node.getParent();
        parentNode.getChildren().remove(node);
        node.setParent(null);

        Product parentProduct = (Product) parentNode.getData();
        Product product = (Product) node.getData();
        parentProduct.getSubordinates().remove(product);
        selectedNode = null;
        //  deleteProducts.add();
    }

    public void onCellEdit(CellEditEvent event) {
//        Object oldValue = event.getOldValue();
//        Object newValue = event.getNewValue();
//
//        if(newValue != null && !newValue.equals(oldValue)) {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
    }
    public void onRowSelect(SelectEvent event) {

    }

    public void save() {
        saveNode(root);
//        delete();

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved", "Saved");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    @Transactional
    private void saveNode(TreeNode treeNode) {
        for (TreeNode tn : treeNode.getChildren()) {
            em.merge((Product) tn.getData());
//            saveNode(tn);
        }
    }
//    @Transactional
//    private void delete(){
//        for(Product p : deleteProducts){
////            em.remove(em.contains(p) ? p : em.merge(p));
//            p = em.find(Product.class, p.getId());
//            em.remove(p);
//        }
//    }

    public void chooseTNC() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        RequestContext rq = RequestContext.getCurrentInstance();
        rq.openDialog("/select/selectTNC", options, null);
    }

    @Transactional
    public void onTNCChosen(SelectEvent event) {
        TNC tnc = (TNC) event.getObject();
        ProductTNC pTNC = new ProductTNC();
        pTNC.setTnc(tnc);
        add(selectedNode, pTNC);
    }

    public void calculate(){

        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        String foo = "function calc(a){" +
                "return a+2;" +
                "}" +
                "" +
                "a=1;" +
                "calc(a)";

        try {
            System.out.println(engine.eval(foo));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
}
