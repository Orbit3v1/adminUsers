package com.app.web.screen;

import com.app.common.CalculatorTreeResultGenerator;
import com.app.data.dto.CalculationDTO;
import com.app.data.entity.*;
import com.app.data.entity.interfaces.Converted;
import com.app.data.entity.interfaces.Selectable;
import com.app.data.entity.interfaces.Valuable;
import com.app.utils.AddMessage;
import com.app.utils.AppUtil;
import com.app.utils.JSEngine;
import com.app.utils.SessionUtil;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Named("productScreen")
@Scope("view")
public class ProductScreen {

    @PersistenceContext
    protected EntityManager em;
    @Inject
    protected ResourceBundle resourceBundle;
    @Inject
    protected AddMessage addMessage;
    @Inject
    private CalculatorTreeResultGenerator calculatorTreeResultGenerator;
    protected Logger logger = Logger.getLogger(getClass());

    private TreeNode root;
    private TreeNode rootCalc;
    private Product entity;
    private TreeNode selectedNode;
    private Map<Product, String> errorProducts;

    @PostConstruct
    @Transactional
    public void init() {
        String id = SessionUtil.getParameter("id");
        if (id != null && AppUtil.isNumeric(id)) {
            entity = em.find(Product.class, AppUtil.toInteger(id));
            entity.getSubordinates().size();
            entity.getInParameters().size();
        } else {
            entity = new Product();
        }
        errorProducts = new HashMap<>();
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

    public void sync() {
    }

    public void nodeExpand(NodeExpandEvent event) {
        event.getTreeNode().setExpanded(true);
    }

    public void nodeCollapse(NodeCollapseEvent event) {
        event.getTreeNode().setExpanded(false);
    }

    public void addProduct() {
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

    public void delete() {
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
            } catch (OptimisticLockException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
                addMessage.setMessage("mainForm:entities", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
            } catch (Exception e) {
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

    protected boolean validate() {
        return true;
    }

    public boolean isSelectable() {
        return selectedNode != null && selectedNode.getData() instanceof Selectable;
    }

    public boolean isSelectable(Product product) {
        return product instanceof Selectable;
    }

    public void choose() {
        Product product = (Product) selectedNode.getData();
        if (product instanceof ProductTNC) {
            choose("selectTNC");
        } else if (product instanceof ProductWork) {
            choose("selectWork");
        }
    }

    public void onChosen(SelectEvent event) {
        Selectable selectable = (Selectable) selectedNode.getData();
        selectable.onSelect(event);
    }

    public void choose(String screenName) {
        RequestContext rq = RequestContext.getCurrentInstance();
        rq.openDialog("/select/" + screenName, getStandardOptions(), null);
    }

    public void onTNCChosen(SelectEvent event) {
        TNC tnc = (TNC) event.getObject();
        ProductTNC pTNC = new ProductTNC();
        pTNC.setTnc(tnc);
        pTNC.setType("TNC");
        add(selectedNode, pTNC);
    }

    public void onWorkChosen(SelectEvent event) {
        Work work = (Work) event.getObject();
        ProductWork pWork = new ProductWork();
        pWork.setWork(work);
        pWork.setType("WORK");
        add(selectedNode, pWork);
    }

    public void onFunctionChosen(SelectEvent event) {
        Function function = (Function) event.getObject();
        Product product = (Product) selectedNode.getData();
        product.setFormula(product.getFormula() + getFunctionName(function));
    }

    private String getFunctionName(Function f) {
        String code = f.getCode();
        code = code.substring(0, code.indexOf("{"));
        return code.replaceAll("(?i)function", "");
    }

    private Map<String, Object> getStandardOptions() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("width", 800);
        options.put("height", 430);
        return options;
    }

    public void editInParameters() {
        String paramKey = "inParameters" + entity.hashCode();
        SessionUtil.addSessionVariable(paramKey, entity.getInParameters());

        Map<String, List<String>> params = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add(paramKey);
        params.put("inParameters", list);

        RequestContext rq = RequestContext.getCurrentInstance();
        rq.openDialog("/screen/ProductInParameterScreen", getStandardOptions(), params);
    }

    public void onInParametersSaved(SelectEvent event) {
        List<ProductInParameter> inParameters = (List<ProductInParameter>) event.getObject();
        if (inParameters != null) {
            inParameters.stream().forEach(x -> x.setProduct(entity));
            entity.setInParameters(inParameters);
        }
    }


    public void calculate() {
        rootCalc = null;
        errorProducts.clear();
        try {
            calculatorTreeResultGenerator.calculate(entity);
            rootCalc = calculatorTreeResultGenerator.getResultRoot();
        } catch (ScriptException e){
            addMessage.setMessage(null, "error.data", FacesMessage.SEVERITY_ERROR);
            errorProducts = calculatorTreeResultGenerator.getErrorProducts();
        }
    }


    private BigDecimal getPrice(Product product, BigDecimal formulaResult) {
        BigDecimal result = formulaResult;
        if(result != null) {
            if (product instanceof Converted) {
                BigDecimal ratio = ((Converted) product).getRatio();
                ratio = ratio == null ? BigDecimal.ONE : ratio;
                result = result.divide(ratio, BigDecimal.ROUND_CEILING);
            }
            if (product instanceof Valuable) {
                BigDecimal price = ((Valuable) product).getPrice();
                price = price == null ? BigDecimal.ZERO : price;
                result = result.multiply(price);
            }
            result.setScale(2, RoundingMode.CEILING);
        }
        return result;
    }

    public boolean isError(Product product) {
        return errorProducts.containsKey(product);
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

    public Map<Product, String> getErrorProducts() {
        return errorProducts;
    }

    public void setErrorProducts(Map<Product, String> errorProducts) {
        this.errorProducts = errorProducts;
    }

    public Product getSelectedProduct() {
        return selectedNode == null ? null : (Product) selectedNode.getData();
    }
}
