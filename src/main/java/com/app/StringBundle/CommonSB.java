package com.app.StringBundle;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class CommonSB {
    private static Object[][] contents = {
            {"listFilter.saveSuccess", "Фильтр сохранен"},
            {"listFilter.loadSuccess", "Фильтр загружен"},
            {"listFilter.findSuccess", "Поиск закончен"},

            {"button.add", "Добавить"},
            {"button.cancel", "Отмена"},
            {"button.save", "Сохранить"},
            {"button.saveAndExit", "Сохранить и выйти"},
            {"button.edit", "Изменить"},
            {"button.editAndExit", "Изменить и выйти"},
            {"button.delete", "Удалить"},
            {"button.delete.message", "Вы действительно хотите удалить запись?"},
            {"button.revert.confirmation", "Отменить"},
            {"button.revert.message", "Вы действительно хотите отменить операцию?"},
            {"button.delete.confirmation", "Удаление"},
            {"button.login", "Войти"},
            {"button.logout", "Выйти"},
            {"button.upload", "Загрузить"},
            {"button.find", "Поиск"},
            {"button.clearFilter", "Сброс"},
            {"button.saveFilter", "Сохранить"},
            {"button.loadFilter", "Загрузить"},
            {"button.export.excel", "Excel"},
            {"button.refresh1C", "Обновить из 1С"},
            {"button.change", "Изменить"},
            {"button.copy", "Копировать"},
            {"button.move", "Переместить"},
            {"button.copy/move", "Копировать/Переместить"},
            {"button.create", "Сформировать"},
            {"button.setEnd", "Сдать"},
            {"button.select", "Выбрать"},

            {"button.calc.addProduct", "+ деталь"},
            {"button.calc.addTNC", "+ материал"},
            {"button.calc.addWork", "+ тех. опер."},
            {"button.calc.addFunction", "+ функция"},
            {"button.calc.calculate", "Рассчитать"},
            {"button.calc.inParameters", "Вх. параметры"},

            {"datePattern", "dd.MM.yyyy"},
            {"dateTimePattern", "dd.MM.yyyy HH:mm"},

            {"pickList.copyAllControlLabel", "Добавить все"},
            {"pickList.copyControlLabel", "Добавить"},
            {"pickList.removeAllControlLabel", "Удалить все"},
            {"pickList.removeControlLabel", "Удалить"},

            {"table.edit", "-"},
            {"table.emptyMessage", "Нет данных"},

            {"success.delete", "Запись удаленна"},
            {"success.save", "Запись сохраненна"},
            {"success.refresh1C", "Обновленние успешно завершено"},
            {"success.copy", "Строка успешно скопирована"},
            {"success.move", "Строка успешно перемещена"},

            {"error.data", "Неверные данные"},
            {"error.notNull", "Поле не может быть пустым"},
            {"error.entityWasChanged", "Запись была удалена или изменена другим пользователем"},
            {"error.exception", "Ошибка приложения"},
            {"error.notNumber", "Поле должно быть числом"},
            {"error.delete", "Сущность используется в заказах или ТЗ. Разорвите все связи перед удалением"},
            {"error.calc.delete", "Сущность используется в расчете. Разорвите все связи перед удалением"},
            {"error.delete.products", "В разделе есть изделия. Сначала удалите все изделия"},
            {"error.refresh1C", "Ошибка при обновлении из 1С"},
            {"error.copy", "Ошибка при копировании"},
            {"error.move", "Ошибка при перемещении"},
            {"error.dateFormat", "Неверная дата"},
            {"error.emptyItems", "Добавте позиции"},
            {"error.nameDuplicate", "Такое же имя есть у другой записи"},
            {"error.namesIsNotUnique", "Наименования должны быть уникальными"},

            {"error.pageNotFound", "Страница не найдена"},
            {"error.error", "Ошибка приложения"},
            {"error.expired", "Сессия устарела"},

            {"string.copy", "Копировать"},
            {"string.move", "Переместить"},

    };

    public static Object[][] getContents(){
        return contents;
    }
}
