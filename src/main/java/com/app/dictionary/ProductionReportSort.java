package com.app.dictionary;

import java.util.Date;

/**
 * Created by ayaroslavtsev on 01.07.2016.
 */
public enum ProductionReportSort {
    NAME_ASC("r.order.name, r.name"),
    NAME_DESC("r.order.name desc, r.name desc"),
    CUSTOMER_ASC("r.order.customer"),
    CUSTOMER_DESC("r.order.customer desc"),
    NOMENCLATURE_ASC("r.nomenclature.name"),
    NOMENCLATURE_DESC("r.nomenclature.name desc"),
    COUNT_ASC("r.count"),
    COUNT_DESC("r.count desc"),
    MATERIAL_ASC("r.nomenclature.material"),
    MATERIAL_DESC("r.nomenclature.material desc"),
    GIB_ASC("r.nomenclature.gib"),
    GIB_DESC("r.nomenclature.gib desc"),
    RESPONSIBLE_ASC("r.order.responsible.lastName, r.order.responsible.firstName"),
    RESPONSIBLE_DESC("r.order.responsible.lastName desc, r.order.responsible.firstName desc"),
    DEVELOPER_ASC("r.developer.lastName, r.developer.firstName"),
    DEVELOPER_DESC("r.developer.lastName desc, r.developer.firstName desc"),
    START_ASC("r.order.start"),
    START_DESC("r.order.start desc"),
    DOC_DATE_ASC("r.docDate"),
    DOC_DATE_DESC("r.docDate desc"),
    END_PLAN_ASC("r.endPlan"),
    END_PLAN_DESC("r.endPlan desc"),
    END_ACTUAL_ASC("r.endActual"),
    END_ACTUAL_DESC("r.endActual desc"),
    ;

    private String sqlOrder;

    ProductionReportSort(String sqlOrder) {
        this.sqlOrder = sqlOrder;
    }

    public String getSqlOrder() {
        return sqlOrder;
    }

    public void setSqlOrder(String sqlOrder) {
        this.sqlOrder = sqlOrder;
    }

    public ProductionReportSort getReverse(){
        if(name().endsWith("_ASC")){
            return valueOf(name().replace("_ASC", "_DESC"));
        } else if(name().endsWith("_DESC")) {
            return valueOf(name().replace("_DESC", "_ASC"));
        }
        return null;
    }
}
