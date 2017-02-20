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

            {"productGroupEntity", "Раздел"},
            {"productGroupEntity.name", "Наименование"},
            {"productList.productTitle", "Изделия"},
            {"productList.groupsTitle", "Разделы"},
            {"productList.copyTo", "Выберете раздел куда копировать или переместить изделие"},
            {"productList.moveTo", "Выберете раздел куда переместить изделие"},

            {"productScreen.calculation", "Результаты"},
            {"productScreen.price", "Стоимость"},
            {"productScreen.product", "Деталь"},
            {"productScreen.TNC", "Материал"},
            {"productScreen.work", "Работа"},
            {"productScreen.value", "Значение"},
            {"productScreen.map", "Заменить"},
            {"productScreen.units", "Ед. изм."},
            {"productScreen.formulaConverted", "Кол-во"}
    };

    public static Object[][] getContents() {
        return contents;
    }
}
