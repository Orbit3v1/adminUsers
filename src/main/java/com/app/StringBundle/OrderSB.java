package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class OrderSB {
    private static Object[][] contents = {
            {"orderEntity.name", "#"},
            {"orderEntity.customer", "Заказчик"},
            {"orderEntity.responsible", "Ответственный"},
            {"orderEntity.start", "Запуск"},
            {"orderEntity.endPlan", "План сдачи"},
            {"orderEntity.price", "Сумма заказа"},
            {"orderEntity.paid", "Оплачено %"},

            {"orderItemEntity.name", "#"},
            {"orderItemEntity.nomenclature", "Номенклатура"},
            {"orderItemEntity.count", "Кол-во"},
            {"orderItemEntity.docDate", "Документация"},
            {"orderItemEntity.developer", "Разработка"},
            {"orderItemEntity.endPlan", "План сдачи"},
            {"orderItemEntity.endActual", "Сдано"},

            {"orderList.title", "Производство"},
            {"orderList.header", "График производства"},
            {"orderList.paid", "Опл."},

            {"orderScreen.title", "Заказ"},
            {"orderScreen.table.orderItems", "Позиции"},
            {"orderScreen.table.payment", "Оплата"},
            {"orderScreen.payments", "Платежи"},
            {"orderScreen.editText", "Редактирование заказа"},
            {"orderScreen.addText", "Добавление заказа"},
            {"orderScreen.error.title", "Неверные данные"},
            {"orderScreen.success.save", "Заказ успешно создан"},
            {"orderScreen.success.edit", "Заказ успешно изменен"},
            {"orderScreen.error.nameDuplicate", "Такое же имя есть у другого заказа"},

            {"orderItemScreen.title", "Позиция заказа"},
            {"orderItemScreen.editText", "Редактирование позиции"},
            {"orderItemScreen.addText", "Добавление позиции"},
            {"orderItemScreen.error.title", "Неверные данные"},
            {"orderItemScreen.success.save", "Позиция успешно создана"},
            {"orderItemScreen.success.edit", "Позиция успешно изменена"},

            {"order.setEnd", "Сдать"}
    };

    public static Object[][] getContents(){
        return contents;
    }
}
