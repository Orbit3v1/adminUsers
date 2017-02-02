package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class TncSupplySB {
    private static Object[][] contents = {
            {"tncSupplyEntity", "Заявка на поставку ТМЦ"},
            {"tncSupplyEntity.name", "#"},
            {"tncSupplyEntity.provider", "Поставщик"},
            {"tncSupplyEntity.accountNumber", "№ счета"},
            {"tncSupplyEntity.deliveryType", "Вид доставки"},
            {"tncSupplyEntity.start", "Дата взятия счета"},
            {"tncSupplyEntity.paymentDate", "Дата оплаты"},
            {"tncSupplyEntity.endPlan", "План доставки"},
            {"tncSupplyEntity.endActual", "Получение кладовщика"},
            {"tncSupplyEntity.endActualShort", "Получение"},

            {"tncSupplyItemEntity", "Позиция заявки на поставку ТМЦ"},
            {"tncSupplyItemEntity.name", "#"},
            {"tncSupplyItemEntity.tnc", "ТМЦ"},
            {"tncSupplyItemEntity.count", "Кол-во"},
            {"tncSupplyItemEntity.units", "Ед. изм."},

            {"tncSupplyList.title", "Заявки на поставку ТМЦ"},

            {"tncSupplyScreen.title", "Заявка на поставку ТМЦ"},
            {"tncSupplyScreen.editText", "Редактирование заявки на поставку ТМЦ"},
            {"tncSupplyScreen.addText", "Добавление заявки на поставку ТМЦ"},
            {"tncSupplyScreen.table.items", "Позиции"},
            {"tncSupplyScreen.table.requests", "Связанные заявки"},
            {"tncSupplyScreen.success.save", "Заявка на поставку ТМЦ успешно создана"},
            {"tncSupplyScreen.success.edit", "Заявка на поставку ТМЦ успешно изменена"},
            {"tncSupplyScreen.error.title", "Неверные данные"},

            {"tncSupplyItemScreen.title", "Позиция заявки на поставку ТМЦ"},
            {"tncSupplyItemScreen.editText", "Редактирование позиции заявки на поставку ТМЦ"},
            {"tncSupplyItemScreen.addText", "Добавление позиции заявки на поставку ТМЦ"},
            {"tncSupplyItemScreen.error.title", "Неверные данные"},
            {"tncSupplyItemScreen.success.save", "Позиция заявки на поставку ТМЦ успешно создана"},
            {"tncSupplyItemScreen.success.edit", "Позиция заявки на поставку ТМЦ успешно изменена"}
    };

    public static Object[][] getContents() {
        return contents;
    }
}
