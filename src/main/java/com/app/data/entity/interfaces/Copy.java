package com.app.data.entity.interfaces;

/**
 * Created by ayaroslavtsev on 23.09.2016.
 */
public interface Copy<T> {
    public T copy();
    public void copyData(T copy);
}