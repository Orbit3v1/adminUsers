package com.app.web.list;

import com.app.data.dictionary.OrderItemState;
import com.app.data.dictionary.ProductionReportSort;
import com.app.data.dto.ProductionReportDTO;
import com.app.data.entity.OrderItem;
import com.app.data.entity.filter.OrderListFilter;
import com.app.data.entity.Person;
import com.app.msOffice.ProductionXLS;
import com.app.data.filter.ListFilterBean;
import com.app.data.filter.OrderListCDI;
import com.app.data.filter.OrderListFilterBean;
import com.app.utils.AddMessage;
import com.app.utils.EntityUtil;
import com.app.web.Loggable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import com.app.security.Security;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Named("orderList")
@Scope("session")
public class OrderList {
    @PersistenceContext
    private EntityManager em;
    private Logger logger = Logger.getLogger(getClass());
    private List<Person> developers;

    @OrderListCDI
    @Autowired
    ListFilterBean listFilterBean;
    @Inject
    private AddMessage addMessage;
    @Inject
    private EntityUtil entityUtil;

    private List<ProductionReportDTO> listRows;
    private Map<String, Boolean> userPA;
    private boolean saveError;
    private String textError;

    private int gibTotal;

    @PostConstruct
    public void init() {
        logger.info("init");
        userPA = Security.getUserPrivilegeAction("orderList");

        developers = entityUtil.getDevelopers();
    }

    public void updateList() {
        initList();
    }

    @Loggable
    private void initList() {
        logger.info("initList");

        List<OrderItem> orderItems = listFilterBean.getList();
        initListRows(orderItems);
        gibTotal = listRows.stream().filter(v -> v.getGib() != null).mapToInt(v -> v.getGib() * v.getCount()).sum();
    }

    private void initListRows(List<OrderItem> orderItems){
        listRows = new ArrayList<>();
        if(orderItems.size() > 0){
            int lastOrderId = orderItems.get(0).getOrder().getId();
            listRows.add(new ProductionReportDTO(orderItems.get(0), false));
            for(int i = 1; i < orderItems.size(); i++){
                OrderItem oi = orderItems.get(i);
                listRows.add(new ProductionReportDTO(oi, oi.getOrder().getId() != lastOrderId));
                lastOrderId = orderItems.get(i).getOrder().getId();
            }
        }
    }

    public void setEndActual(OrderItem orderItem) {
        logger.info("set actual end. OrderItem.id = " + orderItem.getId());
        Date date = new Date();
        orderItem.setEndActual(date);
        save(orderItem);
    }

    public void setDocDate(ProductionReportDTO dto){
        OrderItem orderItem = dto.getOrderItem();
        logger.info("change docDate. OrderItem.id = " + orderItem.getId());
        orderItem.setDocDate(dto.getDocDate());
        save(orderItem);
    }

    public void setEndPlan(ProductionReportDTO dto){
        OrderItem orderItem = dto.getOrderItem();
        logger.info("change endPlan. OrderItem.id = " + orderItem.getId());
        orderItem.setEndPlan(dto.getEndPlan());
        save(orderItem);
    }

    public void setDeveloper(ProductionReportDTO dto){
        OrderItem orderItem = dto.getOrderItem();
        logger.info("change developer. OrderItem.id = " + orderItem.getId());
        orderItem.setDeveloper(dto.getDeveloperEntity());
        save(orderItem);
    }

    public void exportExcel(){
        ProductionXLS pXLS = new ProductionXLS(listRows, userPA);
        pXLS.renderExcel();
    }

    private void save(OrderItem orderItem){
        try {
            orderItem = saveData(orderItem);
            saveError = false;
        } catch (OptimisticLockException e) {
            catchError(e, orderItem);
            addMessage.setMessage("mainForm:orders", "error.entityWasChanged", FacesMessage.SEVERITY_ERROR);
        } catch (Exception e) {
            catchError(e, orderItem);
            addMessage.setMessage("mainForm:orders", "error.exception", FacesMessage.SEVERITY_ERROR);

        }
    }

    private void catchError(Exception e, OrderItem orderItem){
        logger.error(e.getMessage());
        e.printStackTrace();
        saveError = true;
        textError = orderItem.getOrder().getName() + "_" + orderItem.getName();
    }

    @Transactional
    private OrderItem saveData(OrderItem orderItem) {
        return em.merge(orderItem);
    }


    public void refresh() {
        initList();
    }

    public Map<String, Boolean> getUserPA() {
        return userPA;
    }

    public void setUserPA(Map<String, Boolean> userPA) {
        this.userPA = userPA;
    }


    public int getGibTotal() {
        return gibTotal;
    }

    public List<OrderItemState> getFilterStates() {
        return Arrays.asList(OrderItemState.values()).stream().filter(v -> v.getPA() == null || Security.hasAccess(userPA, v.getPA())).collect(Collectors.toList());
    }

    public List<ProductionReportDTO> getListRows() {
        return listRows;
    }

    public void setListRows(List<ProductionReportDTO> listRows) {
        this.listRows = listRows;
    }

    public boolean isSaveError() {
        return saveError;
    }

    public void setSaveError(boolean saveError) {
        this.saveError = saveError;
    }

    public String getTextError() {
        return textError;
    }

    public void setTextError(String textError) {
        this.textError = textError;
    }

    public List<Person> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Person> developers) {
        this.developers = developers;
    }
}


