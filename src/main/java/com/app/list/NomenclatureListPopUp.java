package com.app.list;

import com.app.entity.Nomenclature;
import com.app.entity.Order;
import com.app.entity.OrderItem;
import com.app.entity.SetNomenclature;
import com.app.screen.OrderItemScreen;
import com.app.screen.OrderScreen;
import com.app.utils.AppUtil;
import com.app.utils.SessionUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("nomenclatureListPopUp")
@Scope("request")
public class NomenclatureListPopUp extends NomenclatureList{

    public void choose(Nomenclature nomenclature){
        SetNomenclature source = (SetNomenclature) SessionUtil.getSessionVariable("EntityNomenclature");
        source.setNomenclature(nomenclature);
    }
}
