package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class RoleSB {
    private static Object[][] contents = {
            {"roleEntity.id", "Id"},
            {"roleEntity.name", "Имя"},
            {"roleEntity.description", "Описание"},

            {"roleList.title", "Список ролей"},

            {"roleScreen.title", "Роль"},
            {"roleScreen.editText", "Редактирование роли"},
            {"roleScreen.addText", "Добавление роли"},
            {"roleScreen.privilege.name", "Имя"},
            {"roleScreen.privilege.read", "Чтение"},
            {"roleScreen.privilege.write", "Добавить"},
            {"roleScreen.privilege.edit", "Редактировать"},
            {"roleScreen.privilege.execute", "Выполнить"},
            {"roleScreen.table.privilege", "Привилегии"},
            {"roleScreen.error.title", "Неверные данные"},
            {"roleScreen.error.idDuplicate", "Такой же id есть у другой роли"},
            {"roleScreen.error.nameDuplicate", "Такое же имя есть у другой роли"},
            {"roleScreen.error.idPattern", "Допустимы только буквы латинского алфавита, цифры или символы _-"},
            {"roleScreen.success.save", "Роль успешно создана"},
            {"roleScreen.success.edit", "Роль успешно изменена"}
    };

    public static Object[][] getContents(){
        return contents;
    }
}
