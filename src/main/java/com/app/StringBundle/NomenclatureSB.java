package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class NomenclatureSB {
    private static Object[][] contents = {
            {"nomenclatureEntity.name", "Наименование"},
            {"nomenclatureEntity.description", "Описание"},
            {"nomenclatureEntity.material", "Матер."},
            {"nomenclatureEntity.gib", "Гиб"},
            {"nomenclatureEntity.ready", "Готово"},
            {"nomenclatureEntity.specification", "ТЗ"},

            {"componentEntity.name", "Наименование"},

            {"nomenclatureList.title", "Список номенклатуры"},

            {"nomenclatureScreen.title", "Номенклатура"},
            {"nomenclatureScreen.task", "Тех задание"},
            {"nomenclatureScreen.drawing", "Чертежи"},
            {"nomenclatureScreen.components", "Комплектующие"},
            {"nomenclatureScreen.sketchUpload", "Добавить эскиз"},
            {"nomenclatureScreen.sketches", "Эскизы"},
            {"nomenclatureScreen.editText", "Редактирование номенклатуры"},
            {"nomenclatureScreen.addText", "Добавление номенклатуры"},
            {"nomenclatureScreen.error.title", "Неверные данные"},
            {"nomenclatureScreen.error.nameDuplicate", "Такое же наименование есть у другой номенклатуры"},
            {"nomenclatureScreen.success.save", "Номенклатура успешно создана"},
            {"nomenclatureScreen.success.edit", "Номенклатура успешно изменена"}
    };

    public static Object[][] getContents(){
        return contents;
    }
}
