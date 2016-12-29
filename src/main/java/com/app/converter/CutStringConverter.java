package com.app.converter;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cutStringConverter")
public class CutStringConverter implements Converter {

    private final int PREVIEW_SIZE = 50;
    private final String PREVIEW_END = "...";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return s;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        String description = (String) o;
        String value = description;
        if(description.length() > PREVIEW_SIZE){
            value = description.substring(0, PREVIEW_SIZE - PREVIEW_END.length()) + PREVIEW_END;
        }
        return value;
    }
}
