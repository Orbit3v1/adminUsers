package com.app.data.dictionary;

/**
 * Created by ayaroslavtsev on 08.02.2017.
 */
public enum CarRequestState {
    ALL("Все"), IN_WORK("В работе"), FINISHED("Сдано");

    private String description;

    CarRequestState(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
