package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class TncSB {
    private static Object[][] contents = {
            {"TNCEntity", "ТМЦ"},
            {"TNCEntity.name", "Наименование 1C"},
            {"TNCEntity.description", "Описание"},
            {"TNCEntity.unitsFrom", "Ед. изм. (1С)"},
            {"TNCEntity.unitsTo", "Ед. изм."},
            {"TNCEntity.ratio", "Коэф."},
            {"TNCEntity.price", "Цена"},
            {"TNCEntity.nameInner", "Вн. наименование"},
            {"TNCEntity.limitLow", "Лимит нижн."},
            {"TNCEntity.limitHigh", "Лимит верхн."},
            {"TNCEntity.balance", "Остаток"},

            {"TNCLinkEntity", "Ссылка"},
            {"TNCLinkEntity.link", "Ссылка"},

            {"TNCScreen.title", "ТМЦ"},
            {"TNCScreen.editText", "Редактирование ТМЦ"},
            {"TNCScreen.addText", "Добавление ТМЦ"},
            {"TNCScreen.panel.images", "Изображения"},
            {"TNCScreen.panel.links", "Где купить"},
            {"TNCScreen.column.consumption", "Расход материала"},
            {"TNCScreen.column.consumptionStart", "Период от"},
            {"TNCScreen.column.consumptionEnd", "Период до"},
            {"TNCScreen.column.consumptionResult", "Средний расход"},

            {"TNCList.success.save", "ТМЦ успешно создана"},
            {"TNCList.success.edit", "ТМЦ успешно изменена"},
            {"TNCList.error.nameInnerDuplicate", "Такое же внутренне имя есть у другой ТМЦ"}
    };

    public static Object[][] getContents() {
        return contents;
    }
}
