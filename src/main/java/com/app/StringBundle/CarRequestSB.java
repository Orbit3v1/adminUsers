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
            {"carRequestList.receiver", "Конт. Лицо"},
            {"carRequestList.time", "T"},

            {"carRequestScreen.title", "Заявки на автомобиль"},
            {"carRequestScreen.editText", "Редактирование заявки на автомобиль"},
            {"carRequestScreen.addText", "Добавление заявки на автомобиль"},
            {"carRequestScreen.files", "Файлы"},
            {"carRequestScreen.success.save", "Заявка успешно создана"},
            {"carRequestScreen.success.edit", "Заявка успешно изменена"}
    };

    public static Object[][] getContents(){
        return contents;
    }

}
