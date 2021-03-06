package com.app.data.entity.filter;

import com.app.data.dictionary.Sort;

/**
 * Created by ayaroslavtsev on 25.08.2016.
 */
public interface Filter<T> {

    public void clear();
    public void copyFrom(T filter);
    public void setSort(Sort o);
    public Sort getSort();
    public void setId(Integer id);

}
