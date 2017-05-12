package com.app.data;


import com.app.data.entity.interfaces.Copy;

public class MementoEntity<T extends Copy<T>> {

    T saved;
    T edited;

    public MementoEntity(T entity){
        init(entity);
    }

    private void init(T entity){
        edited = entity;
        save(entity);
    }

    public void save(T entity){
        saved = entity.copy();
    }

    public void undo(){
        edited.copyData(saved);
    }

}
