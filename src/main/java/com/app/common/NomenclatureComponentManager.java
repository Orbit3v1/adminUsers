package com.app.common;

import com.app.data.entity.Component;
import com.app.data.entity.Nomenclature;
import org.apache.log4j.Logger;

public class NomenclatureComponentManager {

    private Nomenclature nomenclature;
    private Logger logger = Logger.getLogger(getClass());

    private Component tmpComponent;
    private Component originalComponent;
    private boolean edit;

    public NomenclatureComponentManager(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
        tmpComponent = new Component();
    }

    public void add(){
        logger.info("add component");
        tmpComponent = new Component();
        tmpComponent.setNomenclature(nomenclature);
        edit = false;
    }

    public void edit(Component component){
        logger.info("edit component");
        originalComponent = component;
        tmpComponent = new Component();
        tmpComponent.copyForm(component);
        edit = true;
    }

    public void delete(Component component){
        nomenclature.getComponents().remove(component);
    }

    public void save(){
        if(edit){
            logger.info("save existing component");
            originalComponent.copyForm(tmpComponent);
        } else {
            logger.info("save new component");
            nomenclature.getComponents().add(tmpComponent);
            edit = true;
        }
    }

    public Component getTmpComponent() {
        return tmpComponent;
    }

    public void setTmpComponent(Component tmpComponent) {
        this.tmpComponent = tmpComponent;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
