package com.app.web.list;

import com.app.data.entity.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.persistence.*;
import java.util.List;

@Named("productList")
@Scope("view")
public class ProductList extends EntityList<Product>{

    private Product selectedProduct;

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

    @Transactional
    public void copy(){
        try {
            selectedProduct = em.find(Product.class, selectedProduct.getId());
            Product copyProduct = (Product) selectedProduct.clone();
            copyProduct = em.merge(copyProduct);
            entities.add(copyProduct);
            addMessage.setMessage(null, "success.copy", FacesMessage.SEVERITY_INFO);
            selectedProduct = null;
        } catch (CloneNotSupportedException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            addMessage.setMessage("mainForm:entities", "error.copy", FacesMessage.SEVERITY_ERROR);
        }
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
}

