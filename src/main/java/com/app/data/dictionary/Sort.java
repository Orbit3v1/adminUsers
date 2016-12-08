package com.app.data.dictionary;

/**
 * Created by ayaroslavtsev on 25.08.2016.
 */
public interface Sort {
    public Sort getReverse();
    public String getSqlOrder();
    public String name();
}
