insert into action(id, name, description)
values('WRITE', 'Запись', '');

insert into action(id, name, description)
values('READ', 'Чтение', '');

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

-- insert into privilege(id, name, description, pos)
-- values('personAdd', 'Пользователь - добавить', '', 1000);
-- insert into privilege_action(privilege, action)
-- values('personAdd', 'READ');
-- insert into privilege_action(privilege, action)
-- values('personAdd', 'WRITE');

insert into privilege(id, name, description, pos)
values('personDelete', 'Пользователь - удалить', '', 1001);
insert into privilege_action(privilege, action)
values('personDelete', 'READ');
insert into privilege_action(privilege, action)
values('personDelete', 'WRITE');

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

insert into privilege(id, name, description, pos)
values('personFirstName', 'Пользователь - Поле - Имя', '', 1004);
insert into privilege_action(privilege, action)
values('personFirstName', 'READ');

insert into privilege(id, name, description, pos)
values('personEmail', 'Пользователь - Поле - Email', '', 1005);
insert into privilege_action(privilege, action)
values('personEmail', 'READ');

insert into privilege(id, name, description, pos)
values('personLogin', 'Пользователь - Поле - Логин', '', 1006);
insert into privilege_action(privilege, action)
values('personLogin', 'READ');

insert into privilege(id, name, description, pos)
values('personPassword', 'Пользователь - Поле - Пароль', '', 1007);
insert into privilege_action(privilege, action)
values('personPassword', 'READ');

insert into privilege(id, name, description, pos)
values('personActive', 'Пользователь - Поле - Активен', '', 1008);
insert into privilege_action(privilege, action)
values('personActive', 'READ');

insert into privilege(id, name, description, pos)
values('personRoles', 'Пользователь - Поле - Роли', '', 1009);
insert into privilege_action(privilege, action)
values('personRoles', 'READ');

