package com.app.web.list;

import com.app.data.dictionary.OrderItemState;
import com.app.data.dictionary.ProductionReportSort;
import com.app.data.dto.ProductionReportDTO;
import com.app.data.entity.OrderItem;
import com.app.data.entity.Person;
import com.app.data.entity.filter.OrderListFilter;
import com.app.data.filter.ListFilterBean;
import com.app.data.filter.OrderListCDI;
import com.app.data.filter.OrderListFilterBean;
import com.app.msOffice.ProductionXLS;
import com.app.security.Security;
import com.app.utils.AddMessage;
import com.app.utils.EntityUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Named("orderList_tmp")
@Scope("session")
public class OrderList_tmp {
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

    private OrderListFilter filter;
    private int gibTotal;

    @PostConstruct
    public void init() {
        logger.info("init");
        userPA = Security.getUserPrivilegeAction("orderList");
        filter = ((OrderListFilterBean) listFilterBean).getFilterOriginal();

        developers = entityUtil.getDevelopers();
    }

    public void updateList() {
        initList();
    }

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

    public String getImage(String name){
        String image = "sort_neutral";
        if(filter.getSort() != null){
            if(filter.getSort().equals(ProductionReportSort.valueOf(name + "_ASC"))){
                image = "sort_asc";
            } else if(filter.getSort().equals(ProductionReportSort.valueOf(name + "_DESC"))){
                image = "sort_desc";
            }
        }
        return image;
    }

    public void setEndActual(OrderItem orderItem) {
        logger.info("set actual end. OrderItem.id = " + orderItem.getId());
        Date date = new Date();
        orderItem.setEndActual(date);
        save(orderItem);
    }

//    public void setDocDate(ProductionReportDTO dto){
//        OrderItem orderItem = dto.getOrderItem();
//        logger.info("change docDate. OrderItem.id = " + orderItem.getId());
//        orderItem.setDocDate(dto.getDocDate());
//        save(orderItem);
//    }


    public void setEndPlan(ValueChangeEvent e){
        ProductionReportDTO dto = eventToDTO(e);
        dto.setEndPlan((Date) e.getNewValue());

        OrderItem orderItem = dto.getOrderItem();
        logger.info("change endPlan. OrderItem.id = " + orderItem.getId());
        orderItem.setEndPlan(dto.getEndPlan());
        save(orderItem);
    }

    public void setDocDate(ValueChangeEvent e){
        ProductionReportDTO dto = eventToDTO(e);
        dto.setDocDate((Date) e.getNewValue());

        OrderItem orderItem = dto.getOrderItem();
        logger.info("change docDate. OrderItem.id = " + orderItem.getId());
        orderItem.setDocDate(dto.getDocDate());
        save(orderItem);
    }

    public void setDeveloper(ValueChangeEvent e){
        ProductionReportDTO dto = eventToDTO(e);
        dto.setDeveloperEntity((Person) e.getNewValue());

        OrderItem orderItem = dto.getOrderItem();
        logger.info("change developer. OrderItem.id = " + orderItem.getId());
        orderItem.setDeveloper(dto.getDeveloperEntity());
        save(orderItem);
    }

    private ProductionReportDTO eventToDTO(ValueChangeEvent e){
        UIInput ui = (UIInput) e.getSource();
        return (ProductionReportDTO) ui.getAttributes().get("row");
    }

    public void exportExcel(){
        ProductionXLS pXLS = new ProductionXLS(listRows, userPA);
        pXLS.renderExcel();
    }

    private void save(OrderItem orderItem){
        try {
            orderItem = saveData(orderItem);
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
        String textError = orderItem.getOrder().getName() + "_" + orderItem.getName();
        addMessage.setMessage("mainForm:orders", textError, FacesMessage.SEVERITY_ERROR);
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

    public List<Person> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Person> developers) {
        this.developers = developers;
    }
}


