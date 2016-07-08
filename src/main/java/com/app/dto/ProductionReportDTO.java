package com.app.dto;

import com.app.entity.Nomenclature;
import com.app.entity.Order;
import com.app.entity.OrderItem;

import java.util.Date;

public class ProductionReportDTO {

    private boolean fromNewOrder;
    private String name;
    private String customer;
    private String nomenclatureName;
    private Integer count;
    private String material;
    private Integer gib;
    private String responsible;
    private String developer;
    private Date start;
    private Date docDate;
    private Date endPlan;
    private Date endActual;
    private Order order;
    private OrderItem orderItem;
    private Nomenclature nomenclature;

    public ProductionReportDTO() {
    }

    public ProductionReportDTO(OrderItem orderItem, boolean fromNewOrder) {
        this.orderItem = orderItem;
        this.order = orderItem.getOrder();
        this.nomenclature = orderItem.getNomenclature();
        name = order.getName() + "_" + orderItem.getName();
        customer = order.getCustomer();
        nomenclatureName = nomenclature.getName();
        count = orderItem.getCount();
        material = nomenclature.getMaterial();
        gib = nomenclature.getGib();
        responsible = order.getResponsible() == null ? null : order.getResponsible().getLastName() + " " + order.getResponsible().getFirstName();
        developer = orderItem.getDeveloper() == null ? null : orderItem.getDeveloper().getLastName() + " " + orderItem.getDeveloper().getFirstName();
        start = order.getStart();
        docDate = orderItem.getDocDate();
        endPlan = orderItem.getEndPlan();
        endActual = orderItem.getEndActual();
        this.fromNewOrder = fromNewOrder;
    }

    public boolean isFromNewOrder() {
        return fromNewOrder;
    }

    public void setFromNewOrder(boolean fromNewOrder) {
        this.fromNewOrder = fromNewOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getNomenclatureName() {
        return nomenclatureName;
    }

    public void setNomenclatureName(String nomenclatureName) {
        this.nomenclatureName = nomenclatureName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getGib() {
        return gib;
    }

    public void setGib(Integer gib) {
        this.gib = gib;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Date getEndPlan() {
        return endPlan;
    }

    public void setEndPlan(Date endPlan) {
        this.endPlan = endPlan;
    }

    public Date getEndActual() {
        return endActual;
    }

    public void setEndActual(Date endActual) {
        this.endActual = endActual;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }
}