package com.app.data.dictionary;

public enum TNCRequestSort implements Sort {
    NAME_ASC("r.tncRequest.name, r.name"),
    NAME_DESC("r.tncRequest.name desc, r.name desc"),
    TNC_ASC("r.tnc.name"),
    TNC_DESC("r.tnc.name desc"),
    COUNT_ASC("r.count"),
    COUNT_DESC("r.count desc"),
    LIMIT_LOW_ASC("r.tnc.limitLow"),
    LIMIT_LOW_DESC("r.tnc.limitLow desc"),
    LIMIT_HIGH_ASC("r.tnc.limitHigh"),
    LIMIT_HIGH_DESC("r.tnc.limitHigh desc"),
    RESPONSIBLE_ASC("resp.lastName, resp.firstName"),
    RESPONSIBLE_DESC("resp.lastName desc, resp.firstName desc"),
    CREATOR_ASC("cr.lastName, cr.firstName"),
    CREATOR_DESC("cr.lastName desc, cr.firstName desc"),
    START_ASC("r.tncRequest.start"),
    START_DESC("r.tncRequest.start desc"),
    END_PLAN_ASC("r.endPlan"),
    END_PLAN_DESC("r.endPlan desc"),
    END_ACTUAL_ASC("r.endActual"),
    END_ACTUAL_DESC("r.endActual desc"),
    STATE_ASC("r.state"),
    STATE_DESC("r.state desc")
    ;

    private String sqlOrder;

    TNCRequestSort(String sqlOrder) {
        this.sqlOrder = sqlOrder;
    }

    public String getSqlOrder() {
        return sqlOrder;
    }

    public void setSqlOrder(String sqlOrder) {
        this.sqlOrder = sqlOrder;
    }

    public TNCRequestSort getReverse(){
        if(name().endsWith("_ASC")){
            return valueOf(name().replace("_ASC", "_DESC"));
        } else if(name().endsWith("_DESC")) {
            return valueOf(name().replace("_DESC", "_ASC"));
        }
        return null;
    }
}
