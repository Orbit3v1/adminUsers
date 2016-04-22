package utils;

import java.util.ListResourceBundle;

/**
 * Created by Andrey on 20.04.2016.
 */
public class StringsBundle_ru_RU extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            {"personEntity.lastName", "Фамилия"},
            {"personEntity.firstName", "Имя"},
            {"personEntity.email", "Email"},
            {"personEntity.active", "Активен"},

            {"personList.title", "Список пользователей"},
            {"personScreen.title", "Пользователь"},
            {"personScreen.error.title", "Неверные данные"},
            {"personScreen.error.emailDuplicate", "Такой же email есть у другого пользователя"},

            {"button.add", "Добавить"},
            {"button.cancel", "Отмена"},
            {"button.save", "Сохранить"},
            {"button.saveAndExit", "Сохранить и выйти"},

            {"table.edit", "-"},
    };
}
