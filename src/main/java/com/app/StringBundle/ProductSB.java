package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class ProductSB {
    private static Object[][] contents = {
            {"productEntity", "Продукция"},
            {"productEntity.name", "Наименование"},
            {"productEntity.detail", "Описание"},
            {"productEntity.height", "Высота"},
            {"productEntity.width", "Ширина"},
            {"productEntity.length", "Длина"},
            {"productEntity.formula", "Формула"},
            {"productEntity.price", "Цена"},

            {"productScreen.calculation", "Стоимость"}
    };

    public static Object[][] getContents() {
        return contents;
    }
}
