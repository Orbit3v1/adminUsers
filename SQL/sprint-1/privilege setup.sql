--liquibase formatted sql
--changeset author:yaroand endDelimiter:;

insert into action(id, name, description)
values('WRITE', 'Запись', '');

insert into action(id, name, description)
values('READ', 'Чтение', '');

insert into action(id, name, description)
values('EDIT', 'Редактирование', '');

insert into action(id, name, description)
values('EXECUTE', 'Выполнить', '');


--Menu
insert into privilege(id, name, description, pos)
values('graphMenu', 'Главное меню - графики', '', 1);
insert into privilege_action(privilege, action)
values('graphMenu', 'READ');

insert into privilege(id, name, description, pos)
values('adminMenu', 'Главное меню - администрирование', '', 2);
insert into privilege_action(privilege, action)
values('adminMenu', 'READ');

insert into privilege(id, name, description, pos)
values('adminMenuPerson', 'Админ меню - пользователи', '', 100);
insert into privilege_action(privilege, action)
values('adminMenuPerson', 'READ');

insert into privilege(id, name, description, pos)
values('adminMenuRole', 'Админ меню - роли', '', 101);
insert into privilege_action(privilege, action)
values('adminMenuRole', 'READ');

insert into privilege(id, name, description, pos)
values('adminMenuNomenclature', 'Админ меню - номенклатуры', '', 102);
insert into privilege_action(privilege, action)
values('adminMenuNomenclature', 'READ');

--Person
insert into privilege(id, name, description, pos)
values('personAdd', 'Пользователь - добавить', '', 1000);
insert into privilege_action(privilege, action)
values('personAdd', 'READ');
insert into privilege_action(privilege, action)
values('personAdd', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('personEdit', 'Пользователь - редактировать', '', 1002);
insert into privilege_action(privilege, action)
values('personEdit', 'READ');
insert into privilege_action(privilege, action)
values('personEdit', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('personLastName', 'Пользователь - Поле - Фамилия', '', 1003);
insert into privilege_action(privilege, action)
values('personLastName', 'READ');
insert into privilege_action(privilege, action)
values('personLastName', 'WRITE');
insert into privilege_action(privilege, action)
values('personLastName', 'EDIT');

insert into privilege(id, name, description, pos)
values('personFirstName', 'Пользователь - Поле - Имя', '', 1004);
insert into privilege_action(privilege, action)
values('personFirstName', 'READ');
insert into privilege_action(privilege, action)
values('personFirstName', 'WRITE');
insert into privilege_action(privilege, action)
values('personFirstName', 'EDIT');

insert into privilege(id, name, description, pos)
values('personEmail', 'Пользователь - Поле - Email', '', 1005);
insert into privilege_action(privilege, action)
values('personEmail', 'READ');
insert into privilege_action(privilege, action)
values('personEmail', 'WRITE');
insert into privilege_action(privilege, action)
values('personEmail', 'EDIT');

insert into privilege(id, name, description, pos)
values('personLogin', 'Пользователь - Поле - Логин', '', 1006);
insert into privilege_action(privilege, action)
values('personLogin', 'READ');
insert into privilege_action(privilege, action)
values('personLogin', 'WRITE');
insert into privilege_action(privilege, action)
values('personLogin', 'EDIT');

insert into privilege(id, name, description, pos)
values('personPassword', 'Пользователь - Поле - Пароль', '', 1007);
insert into privilege_action(privilege, action)
values('personPassword', 'READ');
insert into privilege_action(privilege, action)
values('personPassword', 'WRITE');
insert into privilege_action(privilege, action)
values('personPassword', 'EDIT');

insert into privilege(id, name, description, pos)
values('personActive', 'Пользователь - Поле - Активен', '', 1008);
insert into privilege_action(privilege, action)
values('personActive', 'READ');
insert into privilege_action(privilege, action)
values('personActive', 'WRITE');
insert into privilege_action(privilege, action)
values('personActive', 'EDIT');

insert into privilege(id, name, description, pos)
values('personRoles', 'Пользователь - Поле - Роли', '', 1009);
insert into privilege_action(privilege, action)
values('personRoles', 'READ');
insert into privilege_action(privilege, action)
values('personRoles', 'WRITE');
insert into privilege_action(privilege, action)
values('personRoles', 'EDIT');

--role
insert into privilege(id, name, description, pos)
values('roleAdd', 'Роль - добавить', '', 2000);
insert into privilege_action(privilege, action)
values('roleAdd', 'READ');
insert into privilege_action(privilege, action)
values('roleAdd', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('roleDelete', 'Роль - удалить', '', 2001);
insert into privilege_action(privilege, action)
values('roleDelete', 'READ');
insert into privilege_action(privilege, action)
values('roleDelete', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('roleEdit', 'Роль - редактировать', '', 2002);
insert into privilege_action(privilege, action)
values('roleEdit', 'READ');
insert into privilege_action(privilege, action)
values('roleEdit', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('roleId', 'Роль - Поле - Id', '', 2003);
insert into privilege_action(privilege, action)
values('roleId', 'READ');
insert into privilege_action(privilege, action)
values('roleId', 'WRITE');

insert into privilege(id, name, description, pos)
values('roleName', 'Роль - Поле - Имя', '', 2004);
insert into privilege_action(privilege, action)
values('roleName', 'READ');
insert into privilege_action(privilege, action)
values('roleName', 'WRITE');
insert into privilege_action(privilege, action)
values('roleName', 'EDIT');

insert into privilege(id, name, description, pos)
values('roleDescription', 'Роль - Поле - Описание', '', 2005);
insert into privilege_action(privilege, action)
values('roleDescription', 'READ');
insert into privilege_action(privilege, action)
values('roleDescription', 'WRITE');
insert into privilege_action(privilege, action)
values('roleDescription', 'EDIT');

insert into privilege(id, name, description, pos)
values('rolePrivileges', 'Роль - Поле - Привилегии', '', 2006);
insert into privilege_action(privilege, action)
values('rolePrivileges', 'READ');
insert into privilege_action(privilege, action)
values('rolePrivileges', 'WRITE');
insert into privilege_action(privilege, action)
values('rolePrivileges', 'EDIT');


--nomenclature
insert into privilege(id, name, description, pos)
values('nomenclatureAdd', 'Номенклатура - добавить', '', 3000);
insert into privilege_action(privilege, action)
values('nomenclatureAdd', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureAdd', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('nomenclatureEdit', 'Номенклатура - редактировать', '', 3001);
insert into privilege_action(privilege, action)
values('nomenclatureEdit', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureEdit', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('nomenclatureName', 'Номенклатура - Поле - Наименование', '', 3002);
insert into privilege_action(privilege, action)
values('nomenclatureName', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureName', 'WRITE');
insert into privilege_action(privilege, action)
values('nomenclatureName', 'EDIT');

insert into privilege(id, name, description, pos)
values('nomenclatureDescription', 'Номенклатура - Поле - Описание', '', 3003);
insert into privilege_action(privilege, action)
values('nomenclatureDescription', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureDescription', 'WRITE');
insert into privilege_action(privilege, action)
values('nomenclatureDescription', 'EDIT');

insert into privilege(id, name, description, pos)
values('nomenclatureSketches', 'Номенклатура - Поле - Эскизы', '', 3004);
insert into privilege_action(privilege, action)
values('nomenclatureSketches', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureSketches', 'WRITE');
insert into privilege_action(privilege, action)
values('nomenclatureSketches', 'EDIT');

insert into privilege(id, name, description, pos)
values('nomenclatureDrawings', 'Номенклатура - Поле - Чертежи', '', 3005);
insert into privilege_action(privilege, action)
values('nomenclatureDrawings', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureDrawings', 'WRITE');
insert into privilege_action(privilege, action)
values('nomenclatureDrawings', 'EDIT');

insert into privilege(id, name, description, pos)
values('nomenclatureMaterial', 'Номенклатура - Поле - Материал', '', 3006);
insert into privilege_action(privilege, action)
values('nomenclatureMaterial', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureMaterial', 'WRITE');
insert into privilege_action(privilege, action)
values('nomenclatureMaterial', 'EDIT');

insert into privilege(id, name, description, pos)
values('nomenclatureGib', 'Номенклатура - Поле - Гиб', '', 3007);
insert into privilege_action(privilege, action)
values('nomenclatureGib', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureGib', 'WRITE');
insert into privilege_action(privilege, action)
values('nomenclatureGib', 'EDIT');

insert into privilege(id, name, description, pos)
values('nomenclatureReady', 'Номенклатура - Поле - Готово', '', 3008);
insert into privilege_action(privilege, action)
values('nomenclatureReady', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureReady', 'WRITE');
insert into privilege_action(privilege, action)
values('nomenclatureReady', 'EDIT');

-- order

insert into privilege(id, name, description, pos)
values('graphicMenuProduction', 'Графики меню - производство', '', 200);
insert into privilege_action(privilege, action)
values('graphicMenuProduction', 'READ');

insert into privilege(id, name, description, pos)
values('orderAdd', 'Заказ - добавить', '', 4000);
insert into privilege_action(privilege, action)
values('orderAdd', 'READ');
insert into privilege_action(privilege, action)
values('orderAdd', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('orderEdit', 'Заказ - редактировать', '', 4001);
insert into privilege_action(privilege, action)
values('orderEdit', 'READ');
insert into privilege_action(privilege, action)
values('orderEdit', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('orderDelete', 'Заказ - удалить', '', 4002);
insert into privilege_action(privilege, action)
values('orderDelete', 'READ');
insert into privilege_action(privilege, action)
values('orderDelete', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('orderName', 'Заказ - Поле - Номер', '', 4003);
insert into privilege_action(privilege, action)
values('orderName', 'READ');
insert into privilege_action(privilege, action)
values('orderName', 'WRITE');
insert into privilege_action(privilege, action)
values('orderName', 'EDIT');

insert into privilege(id, name, description, pos)
values('orderCustomer', 'Заказ - Поле - Заказчик', '', 4004);
insert into privilege_action(privilege, action)
values('orderCustomer', 'READ');
insert into privilege_action(privilege, action)
values('orderCustomer', 'WRITE');
insert into privilege_action(privilege, action)
values('orderCustomer', 'EDIT');

insert into privilege(id, name, description, pos)
values('orderResponsible', 'Заказ - Поле - Ответственный', '', 4005);
insert into privilege_action(privilege, action)
values('orderResponsible', 'READ');
insert into privilege_action(privilege, action)
values('orderResponsible', 'WRITE');
insert into privilege_action(privilege, action)
values('orderResponsible', 'EDIT');

insert into privilege(id, name, description, pos)
values('orderStart', 'Заказ - Поле - Запуск', '', 4006);
insert into privilege_action(privilege, action)
values('orderStart', 'READ');
insert into privilege_action(privilege, action)
values('orderStart', 'WRITE');
insert into privilege_action(privilege, action)
values('orderStart', 'EDIT');

-- orderItem

insert into privilege(id, name, description, pos)
values('orderItemAdd', 'Позиция заказа - добавить', '', 5000);
insert into privilege_action(privilege, action)
values('orderItemAdd', 'READ');
insert into privilege_action(privilege, action)
values('orderItemAdd', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('orderItemEdit', 'Позиция заказа - редактировать', '', 5001);
insert into privilege_action(privilege, action)
values('orderItemEdit', 'READ');
insert into privilege_action(privilege, action)
values('orderItemEdit', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('orderItemDelete', 'Позиция заказа - удалить', '', 5002);
insert into privilege_action(privilege, action)
values('orderItemDelete', 'READ');
insert into privilege_action(privilege, action)
values('orderItemDelete', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('orderItemName', 'Позиция заказа - Поле - #', '', 5003);
insert into privilege_action(privilege, action)
values('orderItemName', 'READ');
insert into privilege_action(privilege, action)
values('orderItemName', 'WRITE');
insert into privilege_action(privilege, action)
values('orderItemName', 'EDIT');

insert into privilege(id, name, description, pos)
values('orderItemNomenclature', 'Позиция заказа - Поле - Номенклатура', '', 5004);
insert into privilege_action(privilege, action)
values('orderItemNomenclature', 'READ');
insert into privilege_action(privilege, action)
values('orderItemNomenclature', 'WRITE');
insert into privilege_action(privilege, action)
values('orderItemNomenclature', 'EDIT');

insert into privilege(id, name, description, pos)
values('orderItemCount', 'Позиция заказа - Поле - Кол-во', '', 5005);
insert into privilege_action(privilege, action)
values('orderItemCount', 'READ');
insert into privilege_action(privilege, action)
values('orderItemCount', 'WRITE');
insert into privilege_action(privilege, action)
values('orderItemCount', 'EDIT');

insert into privilege(id, name, description, pos)
values('orderItemDocDate', 'Позиция заказа - Поле - Документация', '', 5006);
insert into privilege_action(privilege, action)
values('orderItemDocDate', 'READ');
insert into privilege_action(privilege, action)
values('orderItemDocDate', 'WRITE');
insert into privilege_action(privilege, action)
values('orderItemDocDate', 'EDIT');

insert into privilege(id, name, description, pos)
values('orderItemDeveloper', 'Позиция заказа - Поле - Разработка', '', 5007);
insert into privilege_action(privilege, action)
values('orderItemDeveloper', 'READ');
insert into privilege_action(privilege, action)
values('orderItemDeveloper', 'WRITE');
insert into privilege_action(privilege, action)
values('orderItemDeveloper', 'EDIT');

insert into privilege(id, name, description, pos)
values('orderItemEndPlan', 'Позиция заказа - Поле - План сдачи', '', 5008);
insert into privilege_action(privilege, action)
values('orderItemEndPlan', 'READ');
insert into privilege_action(privilege, action)
values('orderItemEndPlan', 'WRITE');
insert into privilege_action(privilege, action)
values('orderItemEndPlan', 'EDIT');

insert into privilege(id, name, description, pos)
values('orderItemEndActual', 'Позиция заказа - Поле - Продукция сдана', '', 5009);
insert into privilege_action(privilege, action)
values('orderItemEndActual', 'READ');
insert into privilege_action(privilege, action)
values('orderItemEndActual', 'WRITE');

insert into privilege(id, name, description, pos)
values('orderItemInWork', 'Позиция заказа - Статус - В работе', '', 5020);
insert into privilege_action(privilege, action)
values('orderItemInWork', 'READ');

insert into privilege(id, name, description, pos)
values('orderItemFinished', 'Позиция заказа - Статус - Сдано', '', 5021);
insert into privilege_action(privilege, action)
values('orderItemFinished', 'READ');

-- Component

insert into privilege(id, name, description, pos)
values('componentAdd', 'Комплектующие - добавить', '', 6000);
insert into privilege_action(privilege, action)
values('componentAdd', 'READ');
insert into privilege_action(privilege, action)
values('componentAdd', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('componentEdit', 'Комплектующие - редактировать', '', 6001);
insert into privilege_action(privilege, action)
values('componentEdit', 'READ');
insert into privilege_action(privilege, action)
values('componentEdit', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('componentDelete', 'Комплектующие - удалить', '', 6002);
insert into privilege_action(privilege, action)
values('componentDelete', 'READ');
insert into privilege_action(privilege, action)
values('componentDelete', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('componentName', 'Комплектующие - Поле - Название', '', 6003);
insert into privilege_action(privilege, action)
values('componentName', 'READ');
insert into privilege_action(privilege, action)
values('componentName', 'WRITE');
insert into privilege_action(privilege, action)
values('componentName', 'EDIT');