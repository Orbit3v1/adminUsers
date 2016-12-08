package com.app.data.dictionary;

public enum TNCRequestSort implements Sort {
    NAME_ASC("r.tncRequest.name, cast(r.name as int)"),
    NAME_DESC("r.tncRequest.name desc, cast(r.name as int) desc"),
    TNC_ASC("r.tnc.name"),
    TNC_DESC("r.tnc.name desc"),
    COUNT_ASC("r.count"),
    COUNT_DESC("r.count desc"),
    RESPONSIBLE_ASC("r.tncRequest.responsible.lastName, r.tncRequest.responsible.firstName"),
    RESPONSIBLE_DESC("r.tncRequest.responsible.lastName desc, r.tncRequest.responsible.firstName desc"),
    CREATOR_ASC("r.tncRequest.creator.lastName, r.tncRequest.creator.firstName"),
    CREATOR_DESC("r.tncRequest.creator.lastName desc, r.tncRequest.creator.firstName desc"),
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
