package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class CarRequestSB {

    private static Object[][] contents = {
            {"carRequestEntity", "Позиция заявки на автомобиль"},
            {"carRequestEntity.name", "#"},
            {"carRequestEntity.creator", "Ответственный"},
            {"carRequestEntity.responsible", "Водитель"},
            {"carRequestEntity.start", "Дата заявки"},
            {"carRequestEntity.endActual", "Доставлено"},
            {"carRequestEntity.addressFrom", "Адрес погрузки"},
            {"carRequestEntity.addressTo", "Адрес доставки"},
            {"carRequestEntity.receiverName", "Получатель"},
            {"carRequestEntity.receiverPhone", "Тел. Получателя"},
            {"carRequestEntity.payment", "Оплата"},
            {"carRequestEntity.description", "Доп. Инфо."},
            {"carRequestEntity.priority", "Приоритет"},
            {"carRequestEntity.attachment", "Файл"},

            {"carRequestList.title", "График заявок на автомобиль"},

            {"carRequestScreen.title", "Заявок на автомобиль"},
            {"carRequestScreen.editText", "Редактирование заявоки на автомобиль"},
            {"carRequestScreen.addText", "Добавление заявоки на автомобиль"},
    };

    public static Object[][] getContents(){
        return contents;
    }

}
