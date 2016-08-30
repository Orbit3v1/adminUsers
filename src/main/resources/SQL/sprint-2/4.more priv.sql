insert into privilege(id, name, description, pos)
values('specificationSketches', 'ТЗ - Поле - Эскизы', '', 7018);
insert into privilege_action(privilege, action)
values('specificationSketches', 'READ');
insert into privilege_action(privilege, action)
values('specificationSketches', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationSketches', 'EDIT');


insert into privilege(id, name, description, pos)
values('specificationDescription', 'ТЗ - Поле - Назначение изделия', '', 7019);
insert into privilege_action(privilege, action)
values('specificationDescription', 'READ');
insert into privilege_action(privilege, action)
values('specificationDescription', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationDescription', 'EDIT');