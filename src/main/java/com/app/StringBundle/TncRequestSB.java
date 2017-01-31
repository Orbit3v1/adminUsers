package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class TncRequestSB {
    private static Object[][] contents = {
            {"tncRequestEntity", "Заявка ТНЦ"},
            {"tncRequestEntity.name", "#"},
            {"tncRequestEntity.creator", "Затребовал"},
            {"tncRequestEntity.responsible", "Ответственный"},
            {"tncRequestEntity.start", "Дата заявки"},
            {"tncRequestEntity.endPlan", "План поставки"},

            {"tncRequestItemEntity", "Позиция заявки ТНЦ"},
            {"tncRequestItemEntity.name", "#"},
            {"tncRequestItemEntity.tnc", "ТНЦ"},
            {"tncRequestItemEntity.count", "Кол-во"},
            {"tncRequestItemEntity.reason", "Причина"},
            {"tncRequestItemEntity.endPlan", "Требуемый срок"},
            {"tncRequestItemEntity.endActual", "Поставка"},
            {"tncRequestItemEntity.state", "Статус"},

            {"TNCRequestScreen.title", "Заявка ТНЦ"},
            {"TNCRequestScreen.editText", "Редактирование заявки ТНЦ"},
            {"TNCRequestScreen.addText", "Добавление заявки ТНЦ"},
            {"TNCRequestScreen.table.orderItems", "Позиции"},
            {"TNCRequestScreen.success.save", "Заявка ТНЦ успешно создана"},
            {"TNCRequestScreen.success.edit", "Заявка ТНЦ успешно изменена"},
            {"TNCRequestScreen.error.title", "Неверные данные"},

            {"TNCRequestItemScreen.title", "Позиция заявки ТНЦ"},
            {"TNCRequestItemScreen.editText", "Редактирование позиции заявки ТНЦ"},
            {"TNCRequestItemScreen.addText", "Добавление позиции заявки ТНЦ"},
            {"TNCRequestItemScreen.error.title", "Неверные данные"},
            {"TNCRequestItemScreen.success.save", "Позиция заявки ТНЦ успешно создана"},
            {"TNCRequestItemScreen.success.edit", "Позиция заявки ТНЦ успешно изменена"}
    };

    public static Object[][] getContents() {
        return contents;
    }
}
