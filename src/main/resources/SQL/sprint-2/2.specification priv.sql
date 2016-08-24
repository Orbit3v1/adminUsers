alter table privilege
alter column name varchar(250);

insert into privilege(id, name, description, pos)
values('specificationAdd', 'ТЗ - добавить', '', 7000);
insert into privilege_action(privilege, action)
values('specificationAdd', 'READ');
insert into privilege_action(privilege, action)
values('specificationAdd', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('specificationEdit', 'ТЗ - редактировать', '', 7001);
insert into privilege_action(privilege, action)
values('specificationEdit', 'READ');
insert into privilege_action(privilege, action)
values('specificationEdit', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('specificationDelete', 'ТЗ - удалить', '', 7002);
insert into privilege_action(privilege, action)
values('specificationDelete', 'READ');
insert into privilege_action(privilege, action)
values('specificationDelete', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('specificationName', 'ТЗ - Поле - #', '', 7003);
insert into privilege_action(privilege, action)
values('specificationName', 'READ');
insert into privilege_action(privilege, action)
values('specificationName', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationName', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationNomenclature', 'ТЗ - Поле - Номенклатура', '', 7004);
insert into privilege_action(privilege, action)
values('specificationNomenclature', 'READ');
insert into privilege_action(privilege, action)
values('specificationNomenclature', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationNomenclature', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationResponsible', 'ТЗ - Поле - Ответственный', '', 7005);
insert into privilege_action(privilege, action)
values('specificationResponsible', 'READ');
insert into privilege_action(privilege, action)
values('specificationResponsible', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationResponsible', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationStart', 'ТЗ - Поле - Дата рег.', '', 7006);
insert into privilege_action(privilege, action)
values('specificationStart', 'READ');
insert into privilege_action(privilege, action)
values('specificationStart', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationStart', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationPrice', 'ТЗ - Поле - Цена', '', 7007);
insert into privilege_action(privilege, action)
values('specificationPrice', 'READ');
insert into privilege_action(privilege, action)
values('specificationPrice', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationPrice', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationDiscount', 'ТЗ - Поле - Скидка', '', 7008);
insert into privilege_action(privilege, action)
values('specificationDiscount', 'READ');
insert into privilege_action(privilege, action)
values('specificationDiscount', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationDiscount', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationType', 'ТЗ - Поле - Катег.', '', 7009);
insert into privilege_action(privilege, action)
values('specificationType', 'READ');
insert into privilege_action(privilege, action)
values('specificationType', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationType', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationDeveloper', 'ТЗ - Поле - Разработка', '', 7010);
insert into privilege_action(privilege, action)
values('specificationDeveloper', 'READ');
insert into privilege_action(privilege, action)
values('specificationDeveloper', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationDeveloper', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationResponseDate', 'ТЗ - Поле - Ответ на ТЗ', '', 7011);
insert into privilege_action(privilege, action)
values('specificationResponseDate', 'READ');
insert into privilege_action(privilege, action)
values('specificationResponseDate', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationResponseDate', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationMainSize', 'ТЗ - Поле - Габаритные размеры', '', 7012);
insert into privilege_action(privilege, action)
values('specificationMainSize', 'READ');
insert into privilege_action(privilege, action)
values('specificationMainSize', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationMainSize', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationDetailSize', 'ТЗ - Поле - Присоединительные, установочные, обязательные размеры, мм', '', 7013);
insert into privilege_action(privilege, action)
values('specificationDetailSize', 'READ');
insert into privilege_action(privilege, action)
values('specificationDetailSize', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationDetailSize', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationPressure', 'ТЗ - Поле - Нагрузка на элементы конструкции, кг', '', 7014);
insert into privilege_action(privilege, action)
values('specificationPressure', 'READ');
insert into privilege_action(privilege, action)
values('specificationPressure', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationPressure', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationAdditional', 'ТЗ - Поле - Дополнительные требования к конструкции', '', 7015);
insert into privilege_action(privilege, action)
values('specificationAdditional', 'READ');
insert into privilege_action(privilege, action)
values('specificationAdditional', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationAdditional', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationWorkDays', 'ТЗ - Поле - Ориентировочный срок изготовления, раб. дни', '', 7016);
insert into privilege_action(privilege, action)
values('specificationWorkDays', 'READ');
insert into privilege_action(privilege, action)
values('specificationWorkDays', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationWorkDays', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationChecked', 'ТЗ - Поле - Проверенно', '', 7017);
insert into privilege_action(privilege, action)
values('specificationChecked', 'READ');
insert into privilege_action(privilege, action)
values('specificationChecked', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationChecked', 'EDIT');

insert into privilege(id, name, description, pos)
values('specificationApprove', 'ТЗ - Подписать', '', 7050);
insert into privilege_action(privilege, action)
values('specificationApprove', 'READ');
insert into privilege_action(privilege, action)
values('specificationApprove', 'WRITE');

insert into privilege(id, name, description, pos)
values('graphicMenuSpecification', 'Графики меню - ТЗ', '', 201);
insert into privilege_action(privilege, action)
values('graphicMenuSpecification', 'READ');
