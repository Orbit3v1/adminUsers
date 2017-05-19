package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 18.05.2017.
 */
public class ServiceRequestSB {
    private static Object[][] contents = {
            {"serviceRequestEntity", "Заявка на сервис"},
            {"serviceRequestEntity.name", "#"},
            {"serviceRequestEntity.creator", "Ответственный"},
            {"serviceRequestEntity.responsible", "Исполнитель"},
            {"serviceRequestEntity.start", "Дата заявки"},
            {"serviceRequestEntity.endActual", "Доставлено"},
            {"serviceRequestEntity.warrantyNumber", "№ гарантии"},
            {"serviceRequestEntity.counterparty", "Контрагент"},
            {"serviceRequestEntity.contactName", "Конт. Лицо"},
            {"serviceRequestEntity.contactPhone", "Тел. Конт. Лица"},
            {"serviceRequestEntity.payment", "Оплата"},
            {"serviceRequestEntity.description", "Причина вызова"},
            {"serviceRequestEntity.result", "Рез. выезда"},

            {"serviceRequestList.title", "График заявок на сервис"},
            {"serviceRequestList.contact", "Конт. Лицо"},
            {"serviceRequestList.time", "T"},

            {"serviceRequestScreen.title", "Заявка на сервис"},
            {"serviceRequestScreen.editText", "Редактирование заявки на сервис"},
            {"serviceRequestScreen.addText", "Добавление заявки на сервис"},
            {"serviceRequestScreen.success.save", "Заявка успешно создана"},
            {"serviceRequestScreen.success.edit", "Заявка успешно изменена"}
    };

    public static Object[][] getContents(){
        return contents;
    }
    
}
