package com.app.web.list;

import com.app.data.dao.ProductDao;
import com.app.data.dao.ProductGroupDao;
import com.app.data.entity.Product;
import com.app.data.entity.ProductGroup;
import com.app.security.Security;
import com.app.web.Loggable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Named("productList")
@Scope("view")
public class ProductList extends EntityList<Product>{

    @Autowired
    ProductDao productDao;

    private Product selectedProduct;
    private ProductGroup selectedProductGroup;


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
        return Collections.EMPTY_LIST;
    }

    @Transactional
    public void copy(TreeNode node){
        try {
            ProductGroup productGroup = (ProductGroup) node.getData();
            selectedProduct = em.find(Product.class, selectedProduct.getId());
            Product copyProduct = (Product) selectedProduct.clone();
            copyProduct = productDao.save(copyProduct);

            productGroup.getProducts().add(copyProduct);
            copyProduct.setProductGroup(productGroup);
            addMessage.setMessage(null, "success.copy", FacesMessage.SEVERITY_INFO);
            selectedProduct = null;
            closeDialog("copyPopUp");
        } catch (CloneNotSupportedException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            addMessage.setMessage(":mainForm:infoPanel", "error.copy", FacesMessage.SEVERITY_ERROR);
        } finally {
            node.setSelected(false);
        }
    }

    @Transactional
    public void move(TreeNode node){
        try {
            Product moveProduct = selectedProduct;
            ProductGroup productGroupTo = (ProductGroup) node.getData();
            ProductGroup productGroupFrom = moveProduct.getProductGroup();

            productGroupFrom.getProducts().remove(moveProduct);
            moveProduct = productDao.getById(moveProduct.getId());
            moveProduct.setProductGroup(productGroupTo);
            moveProduct = productDao.save(moveProduct);
            productGroupTo.getProducts().add(moveProduct);

            addMessage.setMessage(null, "success.move", FacesMessage.SEVERITY_INFO);
            selectedProduct = null;
            closeDialog("copyPopUp");
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            addMessage.setMessage(":mainForm:infoPanel", "error.move", FacesMessage.SEVERITY_ERROR);
        } finally {
            node.setSelected(false);
        }
    }

    public void add(TreeNode node){
        super.add();
        selectedProductGroup = (ProductGroup) node.getData();
        editEntity.setProductGroup(selectedProductGroup);
    }


    @Override
    protected void postSave() {
        super.postSave();
        editEntity.setProductGroup(selectedProductGroup);
        selectedProductGroup.getProducts().add(editEntity);
        selectedProductGroup = null;
    }

    @Override
    public boolean delete(Product entity) {
        if(super.delete(entity)){
            selectedProduct = null;
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void removeFromParent(Product entity){
        entity.getProductGroup().getProducts().remove(entity);
    }

    @Override
    public void closeDialog(){
        closeDialog("productPopUp");
    }

    public void groupSelected(NodeSelectEvent event){
        filteredEntities = ((ProductGroup) event.getTreeNode().getData()).getProducts();
        selectedProduct = null;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }


}

