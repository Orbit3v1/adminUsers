package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class FunctionSB {
    private static Object[][] contents = {
            {"functionEntity", "Функция"},
            {"functionEntity.name", "Наименование"},
            {"functionEntity.description", "Описание"},
            {"functionEntity.code", "Код"},

            {"functionInParameterEntity.name", "Параметр"},
            {"functionInParameterEntity.description", "Описание"},

            {"functionList.error.nameDuplicate", "Такое же имя есть у другой функции"}
    };

    public static Object[][] getContents() {
        return contents;
    }
}
