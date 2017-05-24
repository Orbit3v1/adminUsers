package com.app.data.dictionary;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public enum CarRequestSort implements Sort{

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
    ADDRESS_FROM_ASC("r.addressFrom"),
    ADDRESS_FROM_DESC("r.addressFrom desc"),
    ADDRESS_TO_ASC("r.addressTo"),
    ADDRESS_TO_DESC("r.addressTo desc"),
    RECEIVER_NAME_ASC("r.receiverName, r.receiverPhone "),
    RECEIVER_NAME_DESC("r.receiverName desc, r.receiverPhone desc"),
    RECEIVER_PHONE_ASC("r.receiverPhone"),
    RECEIVER_PHONE_DESC("r.receiverPhone desc"),
    PAYMENT_ASC("r.payment"),
    PAYMENT_DESC("r.payment desc"),
    DESCRIPTION_ASC("r.description"),
    DESCRIPTION_DESC("r.description desc"),
    PRIORITY_ASC("r.priority"),
    PRIORITY_DESC("r.priority desc"),
    DECLARATION_ASC("r.declaration"),
    DECLARATION_DESC("r.declaration desc"),
    ;

    private String sqlOrder;

    CarRequestSort(String sqlOrder) {
        this.sqlOrder = sqlOrder;
    }

    public String getSqlOrder() {
        return sqlOrder;
    }

    public void setSqlOrder(String sqlOrder) {
        this.sqlOrder = sqlOrder;
    }

    public CarRequestSort getReverse(){
        if(name().endsWith("_ASC")){
            return valueOf(name().replace("_ASC", "_DESC"));
        } else if(name().endsWith("_DESC")) {
            return valueOf(name().replace("_DESC", "_ASC"));
        }
        return null;
    }
}
