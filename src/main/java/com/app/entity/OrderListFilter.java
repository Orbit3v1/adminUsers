package com.app.entity;

import java.util.Date;

/**
 * Created by ayaroslavtsev on 10.06.2016.
 */
public class OrderListFilter {

    private String name;
    private String customer;
    private String nomenclature;
    private String responsible;
    private String developer;
    private Date startL;
    private Date startH;
    private Date docDateL;
    private Date docDateH;
    private Date endPlanL;
    private Date endPlanH;
    private Date endActualL;
    private Date endActualH;

    public OrderListFilter() {
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

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Date getStartL() {
        return startL;
    }

    public void setStartL(Date startL) {
        this.startL = startL;
    }

    public Date getStartH() {
        return startH;
    }

    public void setStartH(Date startH) {
        this.startH = startH;
    }

    public Date getDocDateL() {
        return docDateL;
    }

    public void setDocDateL(Date docDateL) {
        this.docDateL = docDateL;
    }

    public Date getDocDateH() {
        return docDateH;
    }

    public void setDocDateH(Date docDateH) {
        this.docDateH = docDateH;
    }

    public Date getEndPlanL() {
        return endPlanL;
    }

    public void setEndPlanL(Date endPlanL) {
        this.endPlanL = endPlanL;
    }

    public Date getEndPlanH() {
        return endPlanH;
    }

    public void setEndPlanH(Date endPlanH) {
        this.endPlanH = endPlanH;
    }

    public Date getEndActualL() {
        return endActualL;
    }

    public void setEndActualL(Date endActualL) {
        this.endActualL = endActualL;
    }

    public Date getEndActualH() {
        return endActualH;
    }

    public void setEndActualH(Date endActualH) {
        this.endActualH = endActualH;
    }
}
