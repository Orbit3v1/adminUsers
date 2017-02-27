package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class SpecificationSB {
    private static Object[][] contents = {
            {"specificationEntity.name", "#"},
            {"specificationEntity.innerName", "Вн. наименование"},
            {"specificationEntity.description", "Назначение изделия"},
            {"specificationEntity.nomenclature", "Номенклатура"},
            {"specificationEntity.responsible", "Ответственный"},
            {"specificationEntity.start", "Дата рег."},
            {"specificationEntity.price", "Цена"},
            {"specificationEntity.discount", "Скидка"},
            {"specificationEntity.type", "Катег."},
            {"specificationEntity.developer", "Разработка"},
            {"specificationEntity.responseDate", "Ответ на ТЗ"},
            {"specificationEntity.mainSize", "Габаритные размеры"},
            {"specificationEntity.detailSize", "Присоединительные, установочные, обязательные размеры, мм"},
            {"specificationEntity.pressure", "Нагрузка на элементы конструкции, кг"},
            {"specificationEntity.additional", "Дополнительные требования к конструкции"},
            {"specificationEntity.workDays", "Ориентировочный срок изготовления, раб. дни"},
            {"specificationEntity.approved", "Дата подписи"},
            {"specificationEntity.approvedBy", "Подписал"},
            {"specificationEntity.checked", "Проверенно"},
            {"specificationEntity.material", "Матер."},

            {"specificationList.title", "Реестр ТЗ"},
            {"specificationList.header", "Реестр ТЗ"},

            {"specificationScreen.title", "ТЗ"},
            {"specificationScreen.sketches", "Эскизы"},
            {"specificationScreen.approve", "Подпись"},
            {"specificationScreen.approveB", "Подписать"},
            {"specificationScreen.editText", "Редактирование ТЗ"},
            {"specificationScreen.addText", "Добавление ТЗ"},
            {"specificationScreen.error.title", "Неверные данные"},
            {"specificationScreen.error.nameDuplicate", "Такое же наименование есть у другого ТЗ, сохраниете ТЗ еще раз"},
            {"specificationScreen.success.save", "ТЗ успешно создано"},
            {"specificationScreen.success.edit", "ТЗ успешно изменено"},
            {"specificationScreen.error.nomenclatureDuplicate", "Для номенклатуры уже есть другое ТЗ"}
    };

    public static Object[][] getContents() {
        return contents;
    }
}
