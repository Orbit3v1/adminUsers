package com.app.list;

import com.app.dictionary.OrderItemState;
import com.app.dictionary.ProductionReportSort;
import com.app.dto.ProductionReportDTO;
import com.app.entity.OrderItem;
import com.app.entity.OrderListFilter;
import com.app.entity.Person;
import com.app.excel.ProductionXLS;
import com.app.utils.AddMessage;
import com.app.web.OrderListFilterBean;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import com.app.utils.Security;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.app.utils.AppUtil.notEmpty;
import static com.app.utils.AppUtil.endDay;

@Named("orderList")
@Scope("session")
public class OrderList {
    @PersistenceContext
    protected EntityManager em;
    protected Logger logger = Logger.getLogger(getClass());
    private List<Person> developers;

    @Inject
    OrderListFilterBean orderListFilterBean;
    @Inject
    protected AddMessage addMessage;

    private List<ProductionReportDTO> listRows;
    private Map<String, Boolean> userPA;
    private boolean saveError;
    private String textError;

    private OrderListFilter filter;
    private int gibTotal;

    @PostConstruct
    public void init() {
        logger.info("init");
        userPA = Security.getUserPrivilegeAction("orderList");
        filter = orderListFilterBean.getFilterOriginal();

        Query query = em.createQuery("select r from Person r order by r.lastName, r.firstName");
        developers = query.getResultList();
    }

    public void updateList() {
        initList();
    }

    private void initList() {
        logger.info("initList");
        Map<String, Object> parameters = new HashMap<>();
        String sqlFrom = "select r from OrderItem r " +
                "left join fetch r.order " +
                "left join fetch r.nomenclature " +
                "left join fetch r.developer ";

        String sqlAccess = "";
        if (!Security.hasAccess(userPA, "accessInWork")) {
            sqlAccess += " AND r.endActual is not null";
        }
        if (!Security.hasAccess(userPA, "accessFinished")) {
            sqlAccess += " AND r.endActual is null";
        }

        String sqlWhere = sqlAccess;

        if (notEmpty(filter.getName())) {
            sqlWhere += " AND concat(r.order.name, '_', r.name) like :name";
            parameters.put("name", filter.getName() + "%");
        }
        if (notEmpty(filter.getCustomer())) {
            sqlWhere += " AND r.order.customer like :customer";
            parameters.put("customer", filter.getCustomer() + "%");
        }
        if (notEmpty(filter.getNomenclature())) {
            sqlWhere += " AND r.nomenclature.name like :nomenclature";
            parameters.put("nomenclature", "%" + filter.getNomenclature() + "%");
        }
        if (notEmpty(filter.getResponsible())) {
            sqlWhere += " AND concat(r.order.responsible.lastName, ' ', r.order.responsible.firstName) like :responsible";
            parameters.put("responsible", "%" + filter.getResponsible() + "%");
        }
        if (notEmpty(filter.getDeveloper())) {
            sqlWhere += " AND concat(r.developer.lastName, ' ', r.developer.firstName) like :developer";
            parameters.put("developer", "%" + filter.getDeveloper() + "%");
        }
        if (filter.getStartL() != null) {
            sqlWhere += " AND r.order.start >= :startL";
            parameters.put("startL", filter.getStartL());
        }
        if (filter.getStartH() != null) {
            sqlWhere += " AND r.order.start <= :startH";
            parameters.put("startH", endDay(filter.getStartH()));
        }
        if (filter.getDocDateL() != null) {
            sqlWhere += " AND r.docDate >= :docDateL";
            parameters.put("docDateL", filter.getDocDateL());
        }
        if (filter.getDocDateH() != null) {
            sqlWhere += " AND r.docDate <= :docDateH";
            parameters.put("docDateH", endDay(filter.getDocDateH()));
        }
        if (filter.getEndPlanL() != null) {
            sqlWhere += " AND r.endPlan >= :endPlanL";
            parameters.put("endPlanL", filter.getEndPlanL());
        }
        if (filter.getEndPlanH() != null) {
            sqlWhere += " AND r.endPlan <= :endPlanH";
            parameters.put("endPlanH", endDay(filter.getEndPlanH()));
        }
        if (filter.getEndActualL() != null) {
            sqlWhere += " AND r.endActual >= :endActualL";
            parameters.put("endActualL", filter.getEndActualL());
        }
        if (filter.getEndActualH() != null) {
            sqlWhere += " AND r.endActual <= :endActualH";
            parameters.put("endActualH", endDay(filter.getEndActualH()));
        }
        switch (filter.getState()) {
            case IN_WORK:
                sqlWhere += " AND r.endActual is null";
                break;
            case FINISHED:
                sqlWhere += " AND r.endActual is not null";
                break;
            default:
                break;
        }

        if (!sqlWhere.equals("")) {
            sqlWhere = "WHERE" + sqlWhere.substring(4);
        }

        String sqlOrder = "";
        if(filter.getSort() != null){
            sqlOrder += filter.getSort().getSqlOrder();
        }
        if(!ProductionReportSort.NAME_ASC.equals(filter.getSort())
                    && !ProductionReportSort.NAME_DESC.equals(filter.getSort())) {
            sqlOrder += (sqlOrder.equals("") ? "" : ", ") + "r.order.name, r.name";
        }
        sqlOrder = " order by " + sqlOrder;

        String sqlFull = sqlFrom + sqlWhere + sqlOrder;

        Query query = em.createQuery(sqlFull);
        for (Map.Entry<String, Object> e : parameters.entrySet()) {
            query.setParameter(e.getKey(), e.getValue());
        }

        List<OrderItem> orderItems = query.getResultList();
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
        ProductionXLS pXLS = new ProductionXLS(listRows, userPA, filter);
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


