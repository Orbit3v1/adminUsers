package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class MenuSB {
    private static Object[][] contents = {
            {"menu.header.admin", "Администрирование"},
            {"menu.header.graphic", "Графики"},
            {"menu.header.home", "Вход"},
            {"menu.header.calculation", "Калькулятор"},

            {"menu.admin.user", "Пользователи"},
            {"menu.admin.role", "Роли"},
            {"menu.admin.nomenclature", "Номенклатуры"},

            {"menu.graphic.production", "Производство"},
            {"menu.graphic.specification", "Реестр ТЗ"},
            {"menu.graphic.tncRequest", "Заявки ТМЦ"},
            {"menu.graphic.tncSupply", "Заявки на поставку ТМЦ"},
            {"menu.graphic.carRequest", "Заявки на автомобиль"},

            {"menu.calculation.TNC", "ТМЦ"},
            {"menu.calculation.work", "Тех. операция"},
            {"menu.calculation.function", "Функции"},
            {"menu.calculation.product", "Расчет"},

            {"adminScreen.title", "Администрирование"},
            {"graphicScreen.title", "Графики"}
    };

    public static Object[][] getContents() {
        return contents;
    }
}
