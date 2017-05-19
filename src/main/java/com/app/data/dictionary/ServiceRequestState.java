package com.app.data.dictionary;

public enum ServiceRequestState {
    ALL("Все"), IN_WORK("В работе"), FINISHED("Сдано");

    private String description;

    ServiceRequestState(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
