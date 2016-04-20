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
            {"personList.table.lastName", "Фамилия"},
            {"personList.table.firstName", "Имя"},
            {"personList.table.email", "Email"},
            {"personList.table.state", "Статус"},
    };
}
