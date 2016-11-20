package com.app.data.dictionary;


public enum SpecificationState {
    IN_WORK("inWork"), CALCULATED("calculated"), IN_PROCESS("");

    private String style;

    SpecificationState(String style){
        this.style = style;
    }

    public String getStyle() {
        return style;
    }
}
