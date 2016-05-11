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
            {"personEntity.roles", "Список ролей"},
            {"personEntity.login", "Логин"},
            {"personEntity.password", "Пароль"},

            {"roleEntity.id", "Id"},
            {"roleEntity.name", "Имя"},
            {"roleEntity.description", "Описание"},

            {"nomenclatureEntity.name", "Наименование"},
            {"nomenclatureEntity.description", "Описание"},

            {"orderEntity.name", "#"},
            {"orderEntity.customer", "Заказчик"},
            {"orderEntity.nomenclature", "Номенклатура"},
            {"orderEntity.count", "Кол-во"},
            {"orderEntity.material", "Матер."},
            {"orderEntity.gib", "Гиб"},
            {"orderEntity.responsible", "Ответственный"},
            {"orderEntity.start", "Запуск"},
            {"orderEntity.docDate", "Документация"},
            {"orderEntity.developer", "Разработка"},
            {"orderEntity.endPlan", "План сдачи"},
            {"orderEntity.endActual", "Продукция сдана"},

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
            {"personScreen.success.edit", "Пользователь успешно изменен"},

            {"roleList.title", "Список ролей"},

            {"roleScreen.title", "Роль"},
            {"roleScreen.editText", "Редактирование роли"},
            {"roleScreen.addText", "Добавление роли"},
            {"roleScreen.privilege.name", "Имя"},
            {"roleScreen.privilege.read", "Чтение"},
            {"roleScreen.privilege.write", "Запись"},
            {"roleScreen.table.privilege", "Привилегии"},
            {"roleScreen.error.title", "Неверные данные"},
            {"roleScreen.error.idDuplicate", "Такой же id есть у другой роли"},
            {"roleScreen.error.nameDuplicate", "Такое же имя есть у другой роли"},
            {"roleScreen.error.idPattern", "Допустимы только буквы латинского алфавита, цифры или символы _-"},
            {"roleScreen.success.save", "Роль успешно создана"},
            {"roleScreen.success.edit", "Роль успешно изменена"},

            {"nomenclatureList.title", "Список номенклатуры"},

            {"nomenclatureScreen.title", "Номенклатура"},
            {"nomenclatureScreen.task", "Тех задание"},
            {"nomenclatureScreen.drawing", "Чертежи"},
            {"nomenclatureScreen.sketchUpload", "Добавить эскиз"},
            {"nomenclatureScreen.sketches", "Эскизы"},
            {"nomenclatureScreen.editText", "Редактирование номенклатуры"},
            {"nomenclatureScreen.addText", "Добавление номенклатуры"},
            {"nomenclatureScreen.error.title", "Неверные данные"},
            {"nomenclatureScreen.error.nameDuplicate", "Такое же наименование есть у другой номенклатуры"},
            {"nomenclatureScreen.success.save", "Номенклатура успешно создана"},
            {"nomenclatureScreen.success.edit", "Номенклатура успешно изменена"},

            {"orderList.title", "Производство"},
            {"orderList.header", "График производства"},

            {"orderScreen.title", "Заказ"},
            {"orderScreen.editText", "Редактирование заказа"},
            {"orderScreen.addText", "Добавление заказа"},

            {"loginScreen.login", "Логин"},
            {"loginScreen.password", "Пароль"},
            {"loginScreen.error", "Неверный логин или пароль"},
            {"loginScreen.inactive", "Ваш пользователь заблокирован"},

            {"button.add", "Добавить"},
            {"button.cancel", "Отмена"},
            {"button.save", "Сохранить"},
            {"button.saveAndExit", "Сохранить и выйти"},
            {"button.delete", "Удалить"},
            {"button.delete.message", "Вы действительно хотите удалить запись?"},
            {"button.login", "Войти"},
            {"button.logout", "Выйти"},
            {"button.upload", "Загрузить"},
            {"button.find", "Поиск"},

            {"datePattern", "yyyy-MM-dd"},
            {"dateTimePattern", "yyyy-MM-dd HH:mm"},

            {"pickList.copyAllControlLabel", "Добавить все"},
            {"pickList.copyControlLabel", "Добавить"},
            {"pickList.removeAllControlLabel", "Удалить все"},
            {"pickList.removeControlLabel", "Удалить"},

            {"table.edit", "-"},

            {"error.notNull", "Поле не может быть пустым"},
            {"error.pageNotFound", "Страница не найдена"},
            {"error.entityWasChanged", "Запись была удалена или изменена другим пользователем"},
            {"error.exception", "Ошибка приложения"},

            {"menu.header.admin", "Администрирование"},
            {"menu.header.graphic", "Графики"},
            {"menu.header.home", "Вход"},

            {"menu.admin.user", "Пользователи"},
            {"menu.admin.role", "Роли"},
            {"menu.admin.nomenclature", "Номенклатуры"},

            {"menu.graphic.production", "Производство"}
    };
}
