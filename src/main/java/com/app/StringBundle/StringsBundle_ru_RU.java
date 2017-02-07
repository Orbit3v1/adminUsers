package com.app.StringBundle;

import com.app.utils.AppUtil;

import java.util.ListResourceBundle;

/**
 * Created by Andrey on 20.04.2016.
 */
public class StringsBundle_ru_RU extends ListResourceBundle {

    private static Object[][] contents;

    @Override
    protected Object[][] getContents() {
        if(contents == null){
            populate();
        }
        return contents;
    }

    private void populate() {
        contents = AppUtil.addAll(
                CarRequestSB.getContents(),
                CommonSB.getContents(),
                FunctionSB.getContents(),
                LoginSB.getContents(),
                MenuSB.getContents(),
                NomenclatureSB.getContents(),
                OrderSB.getContents(),
                PaymentSB.getContents(),
                PersonSB.getContents(),
                ProductInParameterSB.getContents(),
                ProductSB.getContents(),
                RoleSB.getContents(),
                SpecificationSB.getContents(),
                TncRequestSB.getContents(),
                TNCSB.getContents(),
                TncSupplySB.getContents(),
                WorkSB.getContents()
        );
    }
}

