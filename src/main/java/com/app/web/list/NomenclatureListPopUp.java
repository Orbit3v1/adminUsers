package com.app.web.list;

import com.app.data.entity.Nomenclature;
import com.app.data.entity.SetNomenclature;
import com.app.utils.SessionUtil;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named("nomenclatureListPopUp")
@Scope("request")
public class NomenclatureListPopUp extends NomenclatureList{

    public void choose(Nomenclature nomenclature){
        SetNomenclature source = (SetNomenclature) SessionUtil.getSessionVariable("EntityNomenclature");
        source.setNomenclature(nomenclature);
    }
}
