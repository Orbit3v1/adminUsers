package com.app.list;

import com.app.entity.Nomenclature;
import com.app.screen.OrderItemScreen;
import com.app.screen.OrderScreen;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

@Named("nomenclatureListPopUp")
@Scope("request")
public class NomenclatureListPopUp extends NomenclatureList{
    @Inject
    private ApplicationContext applicationContext;

    public void choose(Nomenclature nomenclature){
        OrderItemScreen orderItemScreen = applicationContext.getBean(OrderItemScreen.class);
        orderItemScreen.getEntity().setNomenclature(nomenclature);
    }
}
