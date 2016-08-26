package com.app.dictionary;

/**
 * Created by ayaroslavtsev on 25.08.2016.
 */
public enum SpecificationSort implements Sort{
    NAME_ASC("r.name, r.subName"),
    NAME_DESC("r.name desc, r.subName desc"),
    TYPE_ASC("r.type"),
    TYPE_DESC("r.type desc"),
    NOMENCLATURE_ASC("r.nomenclature.name"),
    NOMENCLATURE_DESC("r.nomenclature.name desc"),
    PRICE_ASC("r.price"),
    PRICE_DESC("r.price desc"),
    DISCOUNT_ASC("r.discount"),
    DISCOUNT_DESC("r.discount desc"),
    RESPONSIBLE_ASC("r.responsible.lastName, r.responsible.firstName"),
    RESPONSIBLE_DESC("r.responsible.lastName desc, r.responsible.firstName desc"),
    DEVELOPER_ASC("r.developer.lastName, r.developer.firstName"),
    DEVELOPER_DESC("r.developer.lastName desc, r.developer.firstName desc"),
    START_ASC("r.start"),
    START_DESC("r.start desc"),
    RESPONSE_DATE_ASC("r.responseDate"),
    RESPONSE_DATE_DESC("r.responseDate desc"),
    ;

    private String sqlOrder;

    SpecificationSort(String sqlOrder) {
        this.sqlOrder = sqlOrder;
    }

    public String getSqlOrder() {
        return sqlOrder;
    }

    public void setSqlOrder(String sqlOrder) {
        this.sqlOrder = sqlOrder;
    }

    public SpecificationSort getReverse(){
        if(name().endsWith("_ASC")){
            return valueOf(name().replace("_ASC", "_DESC"));
        } else if(name().endsWith("_DESC")) {
            return valueOf(name().replace("_DESC", "_ASC"));
        }
        return null;
    }
}
