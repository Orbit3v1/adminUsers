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