package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class WorkSB {
    private static Object[][] contents = {
            {"workEntity", "Тех. операция"},
            {"workEntity.name", "Наименование"},
            {"workEntity.description", "Описание"},
            {"workEntity.price", "Цена"},

            {"workList.error.nameDuplicate", "Такое же имя есть у другой работы"}
    };

    public static Object[][] getContents() {
        return contents;
    }
}
