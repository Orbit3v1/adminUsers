package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class TncRequestSB {
    private static Object[][] contents = {
            {"tncRequestEntity", "Заявка ТМЦ"},
            {"tncRequestEntity.name", "#"},
            {"tncRequestEntity.creator", "Затребовал"},
            {"tncRequestEntity.responsible", "Ответственный"},
            {"tncRequestEntity.start", "Дата заявки"},
            {"tncRequestEntity.endPlan", "План поставки"},

            {"tncRequestItemEntity", "Позиция заявки ТМЦ"},
            {"tncRequestItemEntity.name", "#"},
            {"tncRequestItemEntity.tnc", "ТМЦ"},
            {"tncRequestItemEntity.count", "Кол-во"},
            {"tncRequestItemEntity.reason", "Причина"},
            {"tncRequestItemEntity.endPlan", "Требуемый срок"},
            {"tncRequestItemEntity.endActual", "Поставка"},
            {"tncRequestItemEntity.state", "Статус"},

            {"TNCRequestScreen.title", "Заявка ТМЦ"},
            {"TNCRequestScreen.editText", "Редактирование заявки ТМЦ"},
            {"TNCRequestScreen.addText", "Добавление заявки ТМЦ"},
            {"TNCRequestScreen.table.orderItems", "Позиции"},
            {"TNCRequestScreen.success.save", "Заявка ТМЦ успешно создана"},
            {"TNCRequestScreen.success.edit", "Заявка ТМЦ успешно изменена"},
            {"TNCRequestScreen.error.title", "Неверные данные"},

            {"TNCRequestItemScreen.title", "Позиция заявки ТМЦ"},
            {"TNCRequestItemScreen.editText", "Редактирование позиции заявки ТМЦ"},
            {"TNCRequestItemScreen.addText", "Добавление позиции заявки ТМЦ"},
            {"TNCRequestItemScreen.error.title", "Неверные данные"},
            {"TNCRequestItemScreen.success.save", "Позиция заявки ТМЦ успешно создана"},
            {"TNCRequestItemScreen.success.edit", "Позиция заявки ТМЦ успешно изменена"}
    };

    public static Object[][] getContents() {
        return contents;
    }
}
