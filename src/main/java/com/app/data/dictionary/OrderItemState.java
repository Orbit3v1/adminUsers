package com.app.data.dictionary;

public enum OrderItemState {
    ALL("Все", null), IN_WORK("В работе", "accessInWork"), FINISHED("Сдано", "accessFinished");

    private String description;
    private String PA;

    OrderItemState(String description, String PA){
        this.description = description;
        this.PA = PA;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPA() {
        return PA;
    }

    public void setPA(String PA) {
        this.PA = PA;
    }
}
