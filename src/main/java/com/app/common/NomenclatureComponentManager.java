package com.app.common;

import com.app.data.MementoEntity;
import com.app.data.entity.Component;
import com.app.data.entity.Nomenclature;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

public class NomenclatureComponentManager {

    private Nomenclature nomenclature;
    private Logger logger = Logger.getLogger(getClass());

    private Component component;
    private boolean edit;

    MementoEntity<Component> mementoEntity;

    public NomenclatureComponentManager(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
        component = new Component();
    }

    public void add(){
        logger.info("add component");
        component = new Component();
        component.setNomenclature(nomenclature);

        edit = false;
    }

    public void edit(Component component){
        logger.info("edit component");
        mementoEntity = new MementoEntity<>(component);
        this.component = component;
        edit = true;
    }

    public void delete(Component component){
        nomenclature.getComponents().remove(component);
    }

    public void save(){
        if(edit){
            logger.info("save existing component");
            mementoEntity.save(component);
        } else {
            logger.info("save new component");
            nomenclature.getComponents().add(component);
            edit = true;
        }
    }

    public void cancel(){
        if(edit){
            mementoEntity.undo();
        }
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }
}
