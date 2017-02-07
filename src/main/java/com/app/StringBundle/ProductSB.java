package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class ProductSB {
    private static Object[][] contents = {
            {"productEntity", "Изделие"},
            {"productEntity.name", "Наименование"},
            {"productEntity.detail", "Дополнительно"},
            {"productEntity.description", "Примечание"},
            {"productEntity.height", "Высота"},
            {"productEntity.width", "Ширина"},
            {"productEntity.length", "Длина"},
            {"productEntity.count", "Кол-во"},
            {"productEntity.formula", "Формула"},
            {"productEntity.price", "Цена"},

            {"productScreen.calculation", "Стоимость"},
            {"productScreen.product", "Деталь"},
            {"productScreen.TNC", "Материал"},
            {"productScreen.work", "Работа"},
            {"productScreen.value", "Значение"},
            {"productScreen.map", "Заменить"},

    };

    public static Object[][] getContents() {
        return contents;
    }
}
