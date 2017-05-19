insert into privilege(id, name, description, pos)
values('graphicMenuServiceRequest', 'Графики меню - Заявки на сервис', '', 216);
insert into privilege_action(privilege, action)
values('graphicMenuServiceRequest', 'READ');


insert into privilege(id, name, description, pos)
values('serviceRequestAdd', 'Заявка на сервис - добавить', '', 14000);
insert into privilege_action(privilege, action)
values('serviceRequestAdd', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestAdd', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('serviceRequestEdit', 'Заявка на сервис - редактировать', '', 14001);
insert into privilege_action(privilege, action)
values('serviceRequestEdit', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestEdit', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('serviceRequestDelete', 'Заявка на сервис - удалить', '', 14002);
insert into privilege_action(privilege, action)
values('serviceRequestDelete', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestDelete', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('serviceRequestName', 'Заявка на сервис - Поле - Наименование', '', 14003);
insert into privilege_action(privilege, action)
values('serviceRequestName', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestName', 'WRITE');
insert into privilege_action(privilege, action)
values('serviceRequestName', 'EDIT');

insert into privilege(id, name, description, pos)
values('serviceRequestResponsible', 'Заявка на сервис - Поле - Исполнитель', '', 14004);
insert into privilege_action(privilege, action)
values('serviceRequestResponsible', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestResponsible', 'WRITE');
insert into privilege_action(privilege, action)
values('serviceRequestResponsible', 'EDIT');

insert into privilege(id, name, description, pos)
values('serviceRequestWarrantyNumber', 'Заявка на сервис - Поле - № гарантии', '', 14005);
insert into privilege_action(privilege, action)
values('serviceRequestWarrantyNumber', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestWarrantyNumber', 'WRITE');
insert into privilege_action(privilege, action)
values('serviceRequestWarrantyNumber', 'EDIT');

insert into privilege(id, name, description, pos)
values('serviceRequestCounterparty', 'Заявка на сервис - Поле - Контрагент', '', 14006);
insert into privilege_action(privilege, action)
values('serviceRequestCounterparty', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestCounterparty', 'WRITE');
insert into privilege_action(privilege, action)
values('serviceRequestCounterparty', 'EDIT');

insert into privilege(id, name, description, pos)
values('serviceRequestСontactName', 'Заявка на сервис - Поле - Конт. Лицо', '', 14007);
insert into privilege_action(privilege, action)
values('serviceRequestСontactName', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestСontactName', 'WRITE');
insert into privilege_action(privilege, action)
values('serviceRequestСontactName', 'EDIT');

insert into privilege(id, name, description, pos)
values('serviceRequestСontactPhone', 'Заявка на сервис - Поле - Тел. Конт. Лица', '', 14008);
insert into privilege_action(privilege, action)
values('serviceRequestСontactPhone', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestСontactPhone', 'WRITE');
insert into privilege_action(privilege, action)
values('serviceRequestСontactPhone', 'EDIT');

insert into privilege(id, name, description, pos)
values('serviceRequestPayment', 'Заявка на сервис - Поле - Оплата', '', 14009);
insert into privilege_action(privilege, action)
values('serviceRequestPayment', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestPayment', 'WRITE');
insert into privilege_action(privilege, action)
values('serviceRequestPayment', 'EDIT');

insert into privilege(id, name, description, pos)
values('serviceRequestDescription', 'Заявка на сервис - Поле - Причина вызова', '', 14010);
insert into privilege_action(privilege, action)
values('serviceRequestDescription', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestDescription', 'WRITE');
insert into privilege_action(privilege, action)
values('serviceRequestDescription', 'EDIT');

insert into privilege(id, name, description, pos)
values('serviceRequestAddress', 'Заявка на сервис - Поле - Адрес', '', 14011);
insert into privilege_action(privilege, action)
values('serviceRequestAddress', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestAddress', 'WRITE');
insert into privilege_action(privilege, action)
values('serviceRequestAddress', 'EDIT');

insert into privilege(id, name, description, pos)
values('serviceRequestCreator', 'Заявка на сервис - Поле - Ответственный', '', 14012);
insert into privilege_action(privilege, action)
values('serviceRequestCreator', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestCreator', 'WRITE');
insert into privilege_action(privilege, action)
values('serviceRequestCreator', 'EDIT');

insert into privilege(id, name, description, pos)
values('serviceRequestStart', 'Заявка на сервис - Поле - Дата заявки', '', 14013);
insert into privilege_action(privilege, action)
values('serviceRequestStart', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestStart', 'WRITE');
insert into privilege_action(privilege, action)
values('serviceRequestStart', 'EDIT');

insert into privilege(id, name, description, pos)
values('serviceRequestEndActual', 'Заявка на сервис - Поле - Доставлено', '', 14014);
insert into privilege_action(privilege, action)
values('serviceRequestEndActual', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestEndActual', 'WRITE');
insert into privilege_action(privilege, action)
values('serviceRequestEndActual', 'EDIT');

insert into privilege(id, name, description, pos)
values('serviceRequestResult', 'Заявка на сервис - Поле - Рез. выезда', '', 14015);
insert into privilege_action(privilege, action)
values('serviceRequestResult', 'READ');
insert into privilege_action(privilege, action)
values('serviceRequestResult', 'WRITE');
insert into privilege_action(privilege, action)
values('serviceRequestResult', 'EDIT');