package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 06.02.2017.
 */
public class ProductInParameterSB {
    private static Object[][] contents = {
            {"productInParameterEntity", "Входные параметры"},
            {"productInParameterEntity.name", "Переменная"},
            {"productInParameterEntity.description", "Описание"},
            {"productInParameterEntity.value", "Знач."},

            {"productInParameterScreen.title", "Входные параметры"}
    };

    public static Object[][] getContents() {
        return contents;
    }
}
