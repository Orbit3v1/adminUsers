package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class TncSupplySB {
    private static Object[][] contents = {
            {"tncSupplyEntity", "Заявка на поставку ТНЦ"},
            {"tncSupplyEntity.name", "#"},
            {"tncSupplyEntity.provider", "Поставщик"},
            {"tncSupplyEntity.accountNumber", "№ счета"},
            {"tncSupplyEntity.deliveryType", "Вид доставки"},
            {"tncSupplyEntity.start", "Дата взятия счета"},
            {"tncSupplyEntity.paymentDate", "Дата оплаты"},
            {"tncSupplyEntity.endPlan", "План доставки"},
            {"tncSupplyEntity.endActual", "Получение кладовщика"},
            {"tncSupplyEntity.endActualShort", "Получение"},

            {"tncSupplyItemEntity", "Позиция заявки на поставку ТНЦ"},
            {"tncSupplyItemEntity.name", "#"},
            {"tncSupplyItemEntity.tnc", "ТНЦ"},
            {"tncSupplyItemEntity.count", "Кол-во"},
            {"tncSupplyItemEntity.units", "Ед. изм."},

            {"tncSupplyList.title", "Заявки на поставку ТНЦ"},

            {"tncSupplyScreen.title", "Заявка на поставку ТНЦ"},
            {"tncSupplyScreen.editText", "Редактирование заявки на поставку ТНЦ"},
            {"tncSupplyScreen.addText", "Добавление заявки на поставку ТНЦ"},
            {"tncSupplyScreen.table.items", "Позиции"},
            {"tncSupplyScreen.table.requests", "Связанные заявки"},
            {"tncSupplyScreen.success.save", "Заявка на поставку ТНЦ успешно создана"},
            {"tncSupplyScreen.success.edit", "Заявка на поставку ТНЦ успешно изменена"},
            {"tncSupplyScreen.error.title", "Неверные данные"},

            {"tncSupplyItemScreen.title", "Позиция заявки на поставку ТНЦ"},
            {"tncSupplyItemScreen.editText", "Редактирование позиции заявки на поставку ТНЦ"},
            {"tncSupplyItemScreen.addText", "Добавление позиции заявки на поставку ТНЦ"},
            {"tncSupplyItemScreen.error.title", "Неверные данные"},
            {"tncSupplyItemScreen.success.save", "Позиция заявки на поставку ТНЦ успешно создана"},
            {"tncSupplyItemScreen.success.edit", "Позиция заявки на поставку ТНЦ успешно изменена"}
    };

    public static Object[][] getContents() {
        return contents;
    }
}
