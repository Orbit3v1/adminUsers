package com.app.web.screen;

import com.app.data.dto.CalculationDTO;
import com.app.data.entity.*;
import com.app.utils.AddMessage;
import com.app.utils.AppUtil;
import com.app.utils.JSEngine;
import com.app.utils.SessionUtil;
import org.apache.log4j.Logger;
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
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.util.*;

@Named("productScreen")
@Scope("view")
public class ProductScreen {

    @PersistenceContext
    protected EntityManager em;
    @Inject
    private JSEngine jsEngine;
    @Inject
    protected ResourceBundle resourceBundle;
    @Inject
    protected AddMessage addMessage;
    protected Logger logger = Logger.getLogger(getClass());

    private TreeNode root;
    private TreeNode rootCalc;
    private Product entity;
    private TreeNode selectedNode;
    private String includedFunctions;

    @PostConstruct
    public void init() {
        String id = SessionUtil.getParameter("id");
        if (id != null && AppUtil.isNumeric(id)) {
            entity = em.find(Product.class, AppUtil.toInteger(id));
            entity.getSubordinates();
        } else {
            entity = new Product();
        }
        initTree();
    }

    private void initTree() {
        root = new DefaultTreeNode();
        TreeNode tn = new DefaultTreeNode(entity, root);
        tn.setExpanded(true);
        initSubordinates(entity, tn);
    }

    private void initSubordinates(Product product, TreeNode parentNode) {
        for (Product p : product.getSubordinates()) {
            TreeNode tn = new DefaultTreeNode(p, parentNode);
            tn.setExpanded(true);
            initSubordinates(p, tn);
        }
    }

    public void addProduct(){
        Product product = new Product();
        add(selectedNode, product);
    }

    private void add(TreeNode parentNode, Product product) {
        Product parentProduct = (Product) parentNode.getData();
        product.setParent(parentProduct);
        parentProduct.getSubordinates().add(product);
        TreeNode tn = new DefaultTreeNode(product, parentNode);
        tn.setExpanded(true);
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
    }

    public void save() {
        if (validate()) {
            try {
                saveAttempt();
                addMessage.setMessage(null, "success.save", FacesMessage.SEVERITY_INFO);
            } catch (OptimisticLockException e){
                logger.error(e.getMessage());
                e.printStackTrace();
                addMessage.setMessage("mainForm:entities", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
            } catch (Exception e){
                logger.error(e.getMessage());
                e.printStackTrace();
                addMessage.setMessage("mainForm:entities", "error.exception", FacesMessage.SEVERITY_ERROR);
            }
        } else {
            addMessage.setMessage(null, "error.data", FacesMessage.SEVERITY_ERROR);
        }
    }

    @Transactional
    private void saveAttempt() {
        entity = em.merge(entity);
        initTree();
    }

    protected boolean validate(){
        return true;
    }

    public void chooseTNC() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("width", 650);
        options.put("height", 400);
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

    public void chooseWork() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("width", 650);
        options.put("height", 400);
        RequestContext rq = RequestContext.getCurrentInstance();
        rq.openDialog("/select/selectWork", options, null);
    }

    @Transactional
    public void onWorkChosen(SelectEvent event) {
        Work work = (Work) event.getObject();
        ProductWork pWork = new ProductWork();
        pWork.setWork(work);
        add(selectedNode, pWork);
    }

    public void calculate(){
        initFunctions();
        rootCalc = new DefaultTreeNode();
        generateCalcTree(rootCalc, entity);
    }

    @Transactional
    private void initFunctions(){
        includedFunctions = "";
        Query query = em.createQuery("select p from Function p order by p.name");
        List<Function> functions = query.getResultList();
        for(Function f : functions){
            includedFunctions += f.getCode();
        }
    }

    private void generateCalcTree(TreeNode parentNode, Product product){
        BigDecimal price = getPrice(product);
        CalculationDTO calc = new CalculationDTO(product.getName(), price);
        TreeNode tn = new DefaultTreeNode(calc, parentNode);
        tn.setExpanded(true);

        for(Product p : product.getSubordinates()){
            generateCalcTree(tn, p);
        }

        if(parentNode.getData() != null){
            CalculationDTO parentCalc = (CalculationDTO) parentNode.getData();
            parentCalc.setPrice(parentCalc.getPrice().add(calc.getPrice()));
        }
    }

    private BigDecimal getPrice(Product product){
        BigDecimal result = executeCode(product.getFormula());

        if(product instanceof Converted){
            BigDecimal ratio = ((Converted) product).getRatio();
            ratio = ratio == null ? BigDecimal.ONE : ratio;
            result = result.divide(ratio, BigDecimal.ROUND_CEILING);
        }
        if(product instanceof Valuable){
            BigDecimal price = ((Valuable) product).getPrice();
            price = price == null ? BigDecimal.ZERO : price;
            result = result.multiply(price);
        }
        return  result;
    }


    private BigDecimal executeCode(String formula){
        String result = "0";
        if(formula != null) {
            formula = includedFunctions + " " + formula;
            try {
                result = jsEngine.calculate(formula);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
        return new BigDecimal(result);
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

    public TreeNode getRootCalc() {
        return rootCalc;
    }

    public void setRootCalc(TreeNode rootCalc) {
        this.rootCalc = rootCalc;
    }
}
