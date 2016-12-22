package com.app.utils;


import com.app.data.entity.TNC;
import com.app.data.entity.interfaces.TNCOwner;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import java.util.HashMap;
import java.util.Map;

public class TNCChooser {
    TNCOwner entity;

    public TNCChooser(TNCOwner entity){
        this.entity = entity;
    }

    public void choose(String screenName){
        RequestContext rq = RequestContext.getCurrentInstance();
        rq.openDialog("/select/" + screenName, getStandardOptions(), null);
    }

    private Map<String, Object> getStandardOptions() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("width", 650);
        options.put("height", 400);
        return options;
    }

    public void onTNCChosen(SelectEvent event) {
        TNC tnc = (TNC) event.getObject();
        entity.setTnc(tnc);
    }

}
