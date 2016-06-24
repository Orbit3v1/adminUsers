package com.app.utils;

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
            {"nomenclatureEntity.material", "Матер."},
            {"nomenclatureEntity.gib", "Гиб"},
            {"nomenclatureEntity.ready", "Готово"},

            {"orderEntity.name", "#"},
            {"orderEntity.customer", "Заказчик"},
            {"orderEntity.responsible", "Ответственный"},
            {"orderEntity.start", "Запуск"},

            {"orderItemEntity.name", "#"},
            {"orderItemEntity.nomenclature", "Номенклатура"},
            {"orderItemEntity.count", "Кол-во"},
            {"orderItemEntity.docDate", "Документация"},
            {"orderItemEntity.developer", "Разработка"},
            {"orderItemEntity.endPlan", "План сдачи"},
            {"orderItemEntity.endActual", "Продукция сдана"},

            {"componentEntity.name", "Наименование"},

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
            {"roleScreen.privilege.write", "Добавить"},
            {"roleScreen.privilege.edit", "Редактировать"},
            {"roleScreen.privilege.execute", "Выполнить"},
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
            {"nomenclatureScreen.components", "Комплектующие"},
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

            {"orderListFilter.saveSuccess", "Фильтр сохранен"},
            {"orderListFilter.loadSuccess", "Фильтр загружен"},

            {"orderScreen.title", "Заказ"},
            {"orderScreen.table.orderItems", "Позиции"},
            {"orderScreen.editText", "Редактирование заказа"},
            {"orderScreen.addText", "Добавление заказа"},
            {"orderScreen.error.title", "Неверные данные"},
            {"orderScreen.success.save", "Заказ успешно создан"},
            {"orderScreen.success.edit", "Заказ успешно изменен"},
            {"orderScreen.error.nameDuplicate", "Такое же имя есть у другого заказа"},
            {"orderScreen.error.emptyItems", "Добавте позиции"},

            {"orderItemScreen.title", "Позиция заказа"},
            {"orderItemScreen.editText", "Редактирование позиции"},
            {"orderItemScreen.addText", "Добавление позиции"},
            {"orderItemScreen.error.title", "Неверные данные"},
            {"orderItemScreen.success.save", "Позиция успешно создана"},
            {"orderItemScreen.success.edit", "Позиция успешно изменена"},

            {"order.setEnd", "Сдать"},

            {"loginScreen.login", "Логин"},
            {"loginScreen.password", "Пароль"},
            {"loginScreen.error", "Неверный логин или пароль"},
            {"loginScreen.inactive", "Ваш пользователь заблокирован"},

            {"button.add", "Добавить"},
            {"button.cancel", "Отмена"},
            {"button.save", "Сохранить"},
            {"button.saveAndExit", "Сохранить и выйти"},
            {"button.edit", "Изменить"},
            {"button.editAndExit", "Изменить и выйти"},
            {"button.delete", "Удалить"},
            {"button.delete.message", "Вы действительно хотите удалить запись?"},
            {"button.login", "Войти"},
            {"button.logout", "Выйти"},
            {"button.upload", "Загрузить"},
            {"button.find", "Поиск"},
            {"button.clearFilter", "Сброс"},
            {"button.saveFilter", "Сохранить"},
            {"button.loadFilter", "Загрузить"},


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
            {"error.notNumber", "Поле должно быть числом"},

            {"menu.header.admin", "Администрирование"},
            {"menu.header.graphic", "Графики"},
            {"menu.header.home", "Вход"},

            {"menu.admin.user", "Пользователи"},
            {"menu.admin.role", "Роли"},
            {"menu.admin.nomenclature", "Номенклатуры"},

            {"menu.graphic.production", "Производство"}
    };
}
