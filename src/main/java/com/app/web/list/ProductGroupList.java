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
import javax.inject.Named;
import java.util.List;

@Named("productGroupList")
@Scope("view")
public class ProductGroupList extends EntityList<ProductGroup> {

    @Autowired
    ProductGroupDao productGroupDao;
    private TreeNode rootGroup;
    private TreeNode selectedNode;

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
        TreeNode parentNode = selectedNode == null ? rootGroup : selectedNode;
        ProductGroup parentGroup = (ProductGroup) parentNode.getData();
        editEntity.setParent(parentGroup);
    }

    @Override
    protected void postSave() {
        super.postSave();
        TreeNode parentNode = selectedNode == null ? rootGroup : selectedNode;
        TreeNode tn = new DefaultTreeNode(editEntity, parentNode);
    }

   @Transactional
   public void delete(TreeNode node){
       if(delete((ProductGroup) node.getData())){
           TreeNode parentNode = node.getParent();
           parentNode.getChildren().remove(node);
           node.setParent(null);
       }
   }

    @Override
    protected void closeDialog(){
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('groupPopUp').hide();");
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
}
