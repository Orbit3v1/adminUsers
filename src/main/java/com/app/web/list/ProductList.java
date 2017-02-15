package com.app.web.list;

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
import java.util.List;

@Named("productList")
@Scope("view")
public class ProductList extends EntityList<Product>{


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
        return  null;
    }

    @Transactional
    public void copy(){
        try {
            ProductGroup productGroup = selectedProduct.getProductGroup();
            selectedProduct = em.find(Product.class, selectedProduct.getId());
            Product copyProduct = (Product) selectedProduct.clone();
            copyProduct = em.merge(copyProduct);
            productGroup.getProducts().add(copyProduct);
            //entities.add(copyProduct);
            addMessage.setMessage(null, "success.copy", FacesMessage.SEVERITY_INFO);
            selectedProduct = null;
        } catch (CloneNotSupportedException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            addMessage.setMessage("mainForm:entities", "error.copy", FacesMessage.SEVERITY_ERROR);
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
    protected void closeDialog(){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('productPopUp').hide();");
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

