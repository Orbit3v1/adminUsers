package com.app.data.dictionary;

/**
 * Created by ayaroslavtsev on 18.05.2017.
 */
public enum ServiceRequestSort implements Sort{
    ID_ASC("r.id"),
    ID_DESC("r.id desc"),
    NAME_ASC("r.name"),
    NAME_DESC("r.name desc"),
    CREATOR_ASC("r.creator.lastName, r.creator.firstName"),
    CREATOR_DESC("r.creator.lastName desc, r.creator.firstName desc"),
    RESPONSIBLE_ASC("r.responsible.lastName, r.responsible.firstName"),
    RESPONSIBLE_DESC("r.responsible.lastName desc, r.responsible.firstName desc"),
    START_ASC("r.start"),
    START_DESC("r.start desc"),
    END_ACTUAL_ASC("r.endActual"),
    END_ACTUAL_DESC("r.endActual desc"),
    WARRANTY_NUMBER_ASC("r.warranty_number"),
    WARRANTY_NUMBER_DESC("r.warranty_number desc"),
    COUNTERPARTY_ASC("r.counterparty"),
    COUNTERPARTY_DESC("r.counterparty desc"),
    CONTACT_ASC("r.contactName, r.contactPhone "),
    CONTACT_DESC("r.contactName desc, r.contactPhone desc"),
    PAYMENT_ASC("r.payment"),
    PAYMENT_DESC("r.payment desc"),
    DESCRIPTION_ASC("r.description"),
    DESCRIPTION_DESC("r.description desc"),
    ADDRESS_ASC("r.address"),
    ADDRESS_DESC("r.address desc"),
    RESULT_ASC("r.result"),
    RESULT_DESC("r.result desc"),
    ;

    private String sqlOrder;

    ServiceRequestSort(String sqlOrder) {
        this.sqlOrder = sqlOrder;
    }

    public String getSqlOrder() {
        return sqlOrder;
    }

    public void setSqlOrder(String sqlOrder) {
        this.sqlOrder = sqlOrder;
    }

    public ServiceRequestSort getReverse(){
        if(name().endsWith("_ASC")){
            return valueOf(name().replace("_ASC", "_DESC"));
        } else if(name().endsWith("_DESC")) {
            return valueOf(name().replace("_DESC", "_ASC"));
        }
        return null;
    }
}
