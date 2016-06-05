--liquibase formatted sql
--changeset author:yaroand endDelimiter:;

insert into action(id, name, description)
values('WRITE', 'Запись', '');

insert into action(id, name, description)
values('READ', 'Чтение', '');



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
values('personAdd', 'WRITE');

insert into privilege(id, name, description, pos)
values('personEdit', 'Пользователь - редактировать', '', 1002);
insert into privilege_action(privilege, action)
values('personEdit', 'READ');
insert into privilege_action(privilege, action)
values('personEdit', 'WRITE');

insert into privilege(id, name, description, pos)
values('personLastName', 'Пользователь - Поле - Фамилия', '', 1003);
insert into privilege_action(privilege, action)
values('personLastName', 'READ');
insert into privilege_action(privilege, action)
values('personLastName', 'WRITE');

insert into privilege(id, name, description, pos)
values('personFirstName', 'Пользователь - Поле - Имя', '', 1004);
insert into privilege_action(privilege, action)
values('personFirstName', 'READ');
insert into privilege_action(privilege, action)
values('personFirstName', 'WRITE');

insert into privilege(id, name, description, pos)
values('personEmail', 'Пользователь - Поле - Email', '', 1005);
insert into privilege_action(privilege, action)
values('personEmail', 'READ');
insert into privilege_action(privilege, action)
values('personEmail', 'WRITE');

insert into privilege(id, name, description, pos)
values('personLogin', 'Пользователь - Поле - Логин', '', 1006);
insert into privilege_action(privilege, action)
values('personLogin', 'READ');
insert into privilege_action(privilege, action)
values('personLogin', 'WRITE');

insert into privilege(id, name, description, pos)
values('personPassword', 'Пользователь - Поле - Пароль', '', 1007);
insert into privilege_action(privilege, action)
values('personPassword', 'READ');
insert into privilege_action(privilege, action)
values('personPassword', 'WRITE');

insert into privilege(id, name, description, pos)
values('personActive', 'Пользователь - Поле - Активен', '', 1008);
insert into privilege_action(privilege, action)
values('personActive', 'READ');
insert into privilege_action(privilege, action)
values('personActive', 'WRITE');

insert into privilege(id, name, description, pos)
values('personRoles', 'Пользователь - Поле - Роли', '', 1009);
insert into privilege_action(privilege, action)
values('personRoles', 'READ');
insert into privilege_action(privilege, action)
values('personRoles', 'WRITE');

--role
insert into privilege(id, name, description, pos)
values('roleAdd', 'Роль - добавить', '', 2000);
insert into privilege_action(privilege, action)
values('roleAdd', 'READ');
insert into privilege_action(privilege, action)
values('roleAdd', 'WRITE');

insert into privilege(id, name, description, pos)
values('roleDelete', 'Роль - удалить', '', 2001);
insert into privilege_action(privilege, action)
values('roleDelete', 'READ');
insert into privilege_action(privilege, action)
values('roleDelete', 'WRITE');

insert into privilege(id, name, description, pos)
values('roleEdit', 'Роль - редактировать', '', 2002);
insert into privilege_action(privilege, action)
values('roleEdit', 'READ');
insert into privilege_action(privilege, action)
values('roleEdit', 'WRITE');

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

insert into privilege(id, name, description, pos)
values('roleDescription', 'Роль - Поле - Описание', '', 2005);
insert into privilege_action(privilege, action)
values('roleDescription', 'READ');
insert into privilege_action(privilege, action)
values('roleDescription', 'WRITE');

insert into privilege(id, name, description, pos)
values('rolePrivileges', 'Роль - Поле - Привилегии', '', 2006);
insert into privilege_action(privilege, action)
values('rolePrivileges', 'READ');
insert into privilege_action(privilege, action)
values('rolePrivileges', 'WRITE');


--nomenclature
insert into privilege(id, name, description, pos)
values('nomenclatureAdd', 'Номенклатура - добавить', '', 3000);
insert into privilege_action(privilege, action)
values('nomenclatureAdd', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureAdd', 'WRITE');

insert into privilege(id, name, description, pos)
values('nomenclatureEdit', 'Номенклатура - редактировать', '', 3001);
insert into privilege_action(privilege, action)
values('nomenclatureEdit', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureEdit', 'WRITE');

insert into privilege(id, name, description, pos)
values('nomenclatureName', 'Номенклатура - Поле - Наименование', '', 3002);
insert into privilege_action(privilege, action)
values('nomenclatureName', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureName', 'WRITE');

insert into privilege(id, name, description, pos)
values('nomenclatureDescription', 'Номенклатура - Поле - Описание', '', 3003);
insert into privilege_action(privilege, action)
values('nomenclatureDescription', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureDescription', 'WRITE');

insert into privilege(id, name, description, pos)
values('nomenclatureSketches', 'Номенклатура - Поле - Эскизы', '', 3004);
insert into privilege_action(privilege, action)
values('nomenclatureSketches', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureSketches', 'WRITE');

insert into privilege(id, name, description, pos)
values('nomenclatureDrawings', 'Номенклатура - Поле - Чертежи', '', 3005);
insert into privilege_action(privilege, action)
values('nomenclatureDrawings', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureDrawings', 'WRITE');

insert into privilege(id, name, description, pos)
values('nomenclatureMaterial', 'Номенклатура - Поле - Материал', '', 3006);
insert into privilege_action(privilege, action)
values('nomenclatureMaterial', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureMaterial', 'WRITE');

insert into privilege(id, name, description, pos)
values('nomenclatureGib', 'Номенклатура - Поле - Гиб', '', 3007);
insert into privilege_action(privilege, action)
values('nomenclatureGib', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureGib', 'WRITE');

insert into privilege(id, name, description, pos)
values('nomenclatureReady', 'Номенклатура - Поле - Готово', '', 3008);
insert into privilege_action(privilege, action)
values('nomenclatureReady', 'READ');
insert into privilege_action(privilege, action)
values('nomenclatureReady', 'WRITE');

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
values('orderAdd', 'WRITE');

insert into privilege(id, name, description, pos)
values('orderEdit', 'Заказ - редактировать', '', 4001);
insert into privilege_action(privilege, action)
values('orderEdit', 'READ');
insert into privilege_action(privilege, action)
values('orderEdit', 'WRITE');

insert into privilege(id, name, description, pos)
values('orderDelete', 'Заказ - удалить', '', 4002);
insert into privilege_action(privilege, action)
values('orderDelete', 'READ');
insert into privilege_action(privilege, action)
values('orderDelete', 'WRITE');

insert into privilege(id, name, description, pos)
values('orderName', 'Заказ - Поле - Номер', '', 4003);
insert into privilege_action(privilege, action)
values('orderName', 'READ');
insert into privilege_action(privilege, action)
values('orderName', 'WRITE');

insert into privilege(id, name, description, pos)
values('orderCustomer', 'Заказ - Поле - Заказчик', '', 4004);
insert into privilege_action(privilege, action)
values('orderCustomer', 'READ');
insert into privilege_action(privilege, action)
values('orderCustomer', 'WRITE');

insert into privilege(id, name, description, pos)
values('orderNomenclature', 'Заказ - Поле - Номенклатура', '', 4005);
insert into privilege_action(privilege, action)
values('orderNomenclature', 'READ');
insert into privilege_action(privilege, action)
values('orderNomenclature', 'WRITE');

insert into privilege(id, name, description, pos)
values('orderCount', 'Заказ - Поле - Кол-во', '', 4006);
insert into privilege_action(privilege, action)
values('orderCount', 'READ');
insert into privilege_action(privilege, action)
values('orderCount', 'WRITE');

insert into privilege(id, name, description, pos)
values('orderResponsible', 'Заказ - Поле - Ответственный', '', 4009);
insert into privilege_action(privilege, action)
values('orderResponsible', 'READ');
insert into privilege_action(privilege, action)
values('orderResponsible', 'WRITE');

insert into privilege(id, name, description, pos)
values('orderStart', 'Заказ - Поле - Запуск', '', 4010);
insert into privilege_action(privilege, action)
values('orderStart', 'READ');
insert into privilege_action(privilege, action)
values('orderStart', 'WRITE');

insert into privilege(id, name, description, pos)
values('orderDocDate', 'Заказ - Поле - Документация', '', 4011);
insert into privilege_action(privilege, action)
values('orderDocDate', 'READ');
insert into privilege_action(privilege, action)
values('orderDocDate', 'WRITE');

insert into privilege(id, name, description, pos)
values('orderDeveloper', 'Заказ - Поле - Разработка', '', 4012);
insert into privilege_action(privilege, action)
values('orderDeveloper', 'READ');
insert into privilege_action(privilege, action)
values('orderDeveloper', 'WRITE');

insert into privilege(id, name, description, pos)
values('orderEndPlan', 'Заказ - Поле - План сдачи', '', 4013);
insert into privilege_action(privilege, action)
values('orderEndPlan', 'READ');
insert into privilege_action(privilege, action)
values('orderEndPlan', 'WRITE');

insert into privilege(id, name, description, pos)
values('orderEndActual', 'Заказ - Поле - Продукция сдана', '', 4014);
insert into privilege_action(privilege, action)
values('orderEndActual', 'READ');
insert into privilege_action(privilege, action)
values('orderEndActual', 'WRITE');