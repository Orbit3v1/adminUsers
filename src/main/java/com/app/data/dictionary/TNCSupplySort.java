package com.app.data.dictionary;

public enum TNCSupplySort implements Sort{

    NAME_ASC("r.name"),
    NAME_DESC("r.name desc"),
    PROVIDER_ASC("r.provider"),
    PROVIDER_DESC("r.provider desc"),
    ACCOUNT_NUMBER_ASC("r.accountNumber"),
    ACCOUNT_NUMBER_DESC("r.accountNumber desc"),
    DELIVERY_TYPE_ASC("r.deliveryType"),
    DELIVERY_TYPE_DESC("r.deliveryType desc"),
    START_ASC("r.start"),
    START_DESC("r.start desc"),
    END_PLAN_ASC("r.endPlan"),
    END_PLAN_DESC("r.endPlan desc"),
    END_ACTUAL_ASC("r.endActual"),
    END_ACTUAL_DESC("r.endActual desc"),
    PAYMENT_DATE_ASC("r.paymentDate"),
    PAYMENT_DATE_DESC("r.paymentDate desc")
    ;

    private String sqlOrder;

    TNCSupplySort(String sqlOrder) {
        this.sqlOrder = sqlOrder;
    }

    public String getSqlOrder() {
        return sqlOrder;
    }

    public void setSqlOrder(String sqlOrder) {
        this.sqlOrder = sqlOrder;
    }

    public TNCSupplySort getReverse(){
        if(name().endsWith("_ASC")){
            return valueOf(name().replace("_ASC", "_DESC"));
        } else if(name().endsWith("_DESC")) {
            return valueOf(name().replace("_DESC", "_ASC"));
        }
        return null;
    }
}
