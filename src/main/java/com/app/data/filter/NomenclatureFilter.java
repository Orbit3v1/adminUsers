package com.app.data.filter;


import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named("nomenclatureFilter")
@Scope("session")
public class NomenclatureFilter {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
