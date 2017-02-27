package com.app.web.list;

import com.app.data.dao.ProductGroupDao;
import com.app.data.entity.Person;
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
import java.util.List;

@Named("productGroupList")
@Scope("view")
public class ProductGroupList extends EntityList<ProductGroup> {

    @Autowired
    ProductGroupDao productGroupDao;
    private TreeNode rootGroup;
    private TreeNode selectedNode;
    private TreeNode selectedPopupNode;

    @Override
    protected ProductGroup createEntity() {
        return new ProductGroup();
    }

    @Override
    protected String getScreenName() {
        return "productGroupList";
    }

    @Override
    protected List<ProductGroup> getData() {
        return productGroupDao.getAllRoots();
    }

    @Loggable
    @PostConstruct
    public void init(){
        userPA = Security.getUserPrivilegeAction(getScreenName());
        editEntity = createEntity();
        initGroup();
        initList();
        filteredEntities = entities;
    }

    private void initGroup(){
        rootGroup = new DefaultTreeNode();
        rootGroup.setExpanded(true);
        List<ProductGroup> productGroups = getData();
        initSubordinates(productGroups, rootGroup);
    }

    private void initSubordinates(List<ProductGroup> productGroups, TreeNode parentNode) {
        for(ProductGroup productGroup : productGroups) {
            TreeNode tn = new DefaultTreeNode(productGroup, parentNode);
            initSubordinates(productGroup.getSubordinates(), tn);
        }
    }

    @Override
    protected void preSave() {
        super.preSave();
        if(!edit) {
            TreeNode parentNode = selectedNode == null ? rootGroup : selectedNode;
            ProductGroup parentGroup = (ProductGroup) parentNode.getData();
            editEntity.setParent(parentGroup);
        }
    }

    @Override
    protected void postSave() {
        super.postSave();
        TreeNode parentNode = selectedNode == null ? rootGroup : selectedNode;
        parentNode.setExpanded(true);
        TreeNode tn = new DefaultTreeNode(editEntity, parentNode);
        selectedPopupNode.setSelected(false);
    }

    @Override
    protected void postEdit() {
        super.postEdit();
        selectedPopupNode.setSelected(false);
    }

   @Transactional
   public void delete(TreeNode node){
       if(delete((ProductGroup) node.getData())){
           TreeNode parentNode = node.getParent();
           parentNode.getChildren().remove(node);
           node.setParent(null);
           selectedNode = null;
       }
   }

    protected void removeFromParent(ProductGroup entity){
        ProductGroup parent = entity.getParent();
        if(parent != null){
            parent.getSubordinates().remove(entity);
        }
    }

    @Override
    protected void setErrorMessage(){
        addMessage.setMessage("mainForm:infoPanel", "error.delete.products", FacesMessage.SEVERITY_ERROR);
    }

    protected boolean canDelete(ProductGroup entity){
        boolean result = super.canDelete(entity)
                && entity.getProducts().size() == 0;
        for(ProductGroup child : entity.getSubordinates()){
            result &= canDelete(child);
        }
        return result;
    }

    public void setPoPupSelection(){
        if(selectedNode != null){
            selectedNode.setSelected(false);
        }
        if(selectedPopupNode != null){
            selectedPopupNode.setSelected(true);
        }
    }

    @Override
    public void closeDialog(){
        closeDialog("groupPopUp");
    }

    public void nodeExpand(NodeExpandEvent event) {
        event.getTreeNode().setExpanded(true);
    }

    public void nodeCollapse(NodeCollapseEvent event) {
        event.getTreeNode().setExpanded(false);
    }

    public TreeNode getRootGroup() {
        return rootGroup;
    }

    public void setRootGroup(TreeNode rootGroup) {
        this.rootGroup = rootGroup;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public TreeNode getSelectedPopupNode() {
        return selectedPopupNode;
    }

    public void setSelectedPopupNode(TreeNode selectedPopupNode) {
        this.selectedPopupNode = selectedPopupNode;
    }
}
