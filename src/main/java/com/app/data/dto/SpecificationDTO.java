package com.app.data.dto;

import com.app.data.dictionary.SpecificationState;
import com.app.data.entity.Specification;

import java.util.Date;

/**
 * Created by ayaroslavtsev on 26.08.2016.
 */
public class SpecificationDTO {

    private int id;
    private String name;
    private String type;
    private String nomenclatureName;
    private Integer nomenclatureId;
    private String price;
    private String discount;
    private String responsible;
    private String developer;
    private Date start;
    private Date responseDate;
    private Specification specification;
    private SpecificationState state;

    public SpecificationDTO(Specification sp) {
        this.id = sp.getId();
        this.name = sp.getFullName();
        this.type = sp.getType();
        this.nomenclatureName = sp.getNomenclature() == null ? null : sp.getNomenclature().getName();
        this.nomenclatureId = sp.getNomenclature() == null ? null : sp.getNomenclature().getId();
        this.price = sp.getPrice();
        this.discount = sp.getDiscount();
        this.responsible = sp.getResponsible() == null ? null : sp.getResponsible().toString();
        this.developer = sp.getDeveloper() == null ? null : sp.getDeveloper().toString();
        this.start = sp.getStart();
        this.responseDate = sp.getResponseDate();
        this.state = calculateState(sp);
    }

    private SpecificationState calculateState(Specification sp){
        SpecificationState state = SpecificationState.IN_PROCESS;
        if(sp.getNomenclature() != null && sp.getNomenclature().getOrderItems().size() > 0){
            state = SpecificationState.IN_WORK;
        } else if(sp.getPrice() != null && !sp.getPrice().equals("")){
            state = SpecificationState.CALCULATED;
        }
        return state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNomenclatureName() {
        return nomenclatureName;
    }

    public void setNomenclatureName(String nomenclatureName) {
        this.nomenclatureName = nomenclatureName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNomenclatureId() {
        return nomenclatureId;
    }

    public void setNomenclatureId(Integer nomenclatureId) {
        this.nomenclatureId = nomenclatureId;
    }

    public SpecificationState getState() {
        return state;
    }

    public void setState(SpecificationState state) {
        this.state = state;
    }
}
