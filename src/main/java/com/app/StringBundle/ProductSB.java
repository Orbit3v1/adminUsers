package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class ProductSB {
    private static Object[][] contents = {
            {"productEntity", "Изделие"},
            {"productEntity.name", "Наименование"},
            {"productEntity.detail", "Примечание"},
            {"productEntity.description", "Дополнительно"},
            {"productEntity.height", "Высота"},
            {"productEntity.width", "Ширина"},
            {"productEntity.length", "Длина"},
            {"productEntity.count", "Кол-во"},
            {"productEntity.formula", "Формула"},
            {"productEntity.price", "Цена"},

            {"productScreen.calculation", "Стоимость"},
            {"productScreen.product", "Деталь"},
            {"productScreen.TNC", "Материал"},
    };

    public static Object[][] getContents() {
        return contents;
    }
}
