package com.app.list;

import com.app.entity.Nomenclature;
import com.app.screen.OrderItemScreen;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

@Named("nomenclatureListPopUp")
@Scope("request")
public class NomenclatureListPopUp extends NomenclatureList{
    @Inject
    OrderItemScreen orderItemScreen;

    public void choose(Nomenclature nomenclature){
        orderItemScreen.getEntity().setNomenclature(nomenclature);
    }
}
