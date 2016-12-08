package com.app.data.dictionary;

public enum TNCRequestState {
    IN_WORK("В работе"),
    ACCOUNT("Взят счет"),
    PAID("Оплачено"),
    DRIVER_SENT("Отправлен водитель"),
    ARRIVED("Прибыло на склад");

    private String description;

    TNCRequestState(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}