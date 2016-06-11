package com.app.dictionary;

public enum OrderItemState {
    ALL("Все"), IN_WORK("В работе"), FINISHED("Сдано");

    private String description;

    OrderItemState(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
