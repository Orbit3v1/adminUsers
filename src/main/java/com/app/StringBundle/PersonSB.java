package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class PersonSB {
    private static Object[][] contents = {
            {"personEntity.lastName", "Фамилия"},
            {"personEntity.firstName", "Имя"},
            {"personEntity.email", "Email"},
            {"personEntity.active", "Активен"},
            {"personEntity.roles", "Список ролей"},
            {"personEntity.login", "Логин"},
            {"personEntity.password", "Пароль"},

            {"personList.title", "Список пользователей"},

            {"personScreen.title", "Пользователь"},
            {"personScreen.editText", "Редактирование пользователя"},
            {"personScreen.addText", "Добавление пользователя"},
            {"personScreen.roles.available", "Доступно"},
            {"personScreen.roles.selected", "Выбрано"},
            {"personScreen.error.title", "Неверные данные"},
            {"personScreen.error.emailDuplicate", "Такой же email есть у другого пользователя"},
            {"personScreen.error.loginDuplicate", "Такой же логин есть у другого пользователя"},
            {"personScreen.success.save", "Пользователь успешно создан"},
            {"personScreen.success.edit", "Пользователь успешно изменен"}
    };

    public static Object[][] getContents(){
        return contents;
    }
}
