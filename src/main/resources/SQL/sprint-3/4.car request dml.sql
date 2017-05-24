insert into privilege(id, name, description, pos)
values('graphicMenuCarRequest', 'Графики меню - Заявки на автомобиль', '', 215);
insert into privilege_action(privilege, action)
values('graphicMenuCarRequest', 'READ');


insert into privilege(id, name, description, pos)
values('carRequestAdd', 'Заявка на автомобиль - добавить', '', 13000);
insert into privilege_action(privilege, action)
values('carRequestAdd', 'READ');
insert into privilege_action(privilege, action)
values('carRequestAdd', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('carRequestEdit', 'Заявка на автомобиль - редактировать', '', 13001);
insert into privilege_action(privilege, action)
values('carRequestEdit', 'READ');
insert into privilege_action(privilege, action)
values('carRequestEdit', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('carRequestDelete', 'Заявка на автомобиль - удалить', '', 13002);
insert into privilege_action(privilege, action)
values('carRequestDelete', 'READ');
insert into privilege_action(privilege, action)
values('carRequestDelete', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('carRequestName', 'Заявка на автомобиль - Поле - Наименование', '', 13003);
insert into privilege_action(privilege, action)
values('carRequestName', 'READ');
insert into privilege_action(privilege, action)
values('carRequestName', 'WRITE');
insert into privilege_action(privilege, action)
values('carRequestName', 'EDIT');

insert into privilege(id, name, description, pos)
values('carRequestResponsible', 'Заявка на автомобиль - Поле - Водитель', '', 13004);
insert into privilege_action(privilege, action)
values('carRequestResponsible', 'READ');
insert into privilege_action(privilege, action)
values('carRequestResponsible', 'WRITE');
insert into privilege_action(privilege, action)
values('carRequestResponsible', 'EDIT');

insert into privilege(id, name, description, pos)
values('carRequestAddressFrom', 'Заявка на автомобиль - Поле - Адрес погрузки', '', 13005);
insert into privilege_action(privilege, action)
values('carRequestAddressFrom', 'READ');
insert into privilege_action(privilege, action)
values('carRequestAddressFrom', 'WRITE');
insert into privilege_action(privilege, action)
values('carRequestAddressFrom', 'EDIT');

insert into privilege(id, name, description, pos)
values('carRequestAddressTo', 'Заявка на автомобиль - Поле - Адрес доставки', '', 13006);
insert into privilege_action(privilege, action)
values('carRequestAddressTo', 'READ');
insert into privilege_action(privilege, action)
values('carRequestAddressTo', 'WRITE');
insert into privilege_action(privilege, action)
values('carRequestAddressTo', 'EDIT');

insert into privilege(id, name, description, pos)
values('carRequestReceiverName', 'Заявка на автомобиль - Поле - Получатель', '', 13007);
insert into privilege_action(privilege, action)
values('carRequestReceiverName', 'READ');
insert into privilege_action(privilege, action)
values('carRequestReceiverName', 'WRITE');
insert into privilege_action(privilege, action)
values('carRequestReceiverName', 'EDIT');

insert into privilege(id, name, description, pos)
values('carRequestReceiverPhone', 'Заявка на автомобиль - Поле - Тел. Получателя', '', 13008);
insert into privilege_action(privilege, action)
values('carRequestReceiverPhone', 'READ');
insert into privilege_action(privilege, action)
values('carRequestReceiverPhone', 'WRITE');
insert into privilege_action(privilege, action)
values('carRequestReceiverPhone', 'EDIT');

insert into privilege(id, name, description, pos)
values('carRequestPayment', 'Заявка на автомобиль - Поле - Оплата', '', 13009);
insert into privilege_action(privilege, action)
values('carRequestPayment', 'READ');
insert into privilege_action(privilege, action)
values('carRequestPayment', 'WRITE');
insert into privilege_action(privilege, action)
values('carRequestPayment', 'EDIT');

insert into privilege(id, name, description, pos)
values('carRequestDescription', 'Заявка на автомобиль - Поле - Доп. Инфо.', '', 13010);
insert into privilege_action(privilege, action)
values('carRequestDescription', 'READ');
insert into privilege_action(privilege, action)
values('carRequestDescription', 'WRITE');
insert into privilege_action(privilege, action)
values('carRequestDescription', 'EDIT');

insert into privilege(id, name, description, pos)
values('carRequestAttachment', 'Заявка на автомобиль - Поле - Файл', '', 13011);
insert into privilege_action(privilege, action)
values('carRequestAttachment', 'READ');
insert into privilege_action(privilege, action)
values('carRequestAttachment', 'WRITE');
insert into privilege_action(privilege, action)
values('carRequestAttachment', 'EDIT');

insert into privilege(id, name, description, pos)
values('carRequestCreator', 'Заявка на автомобиль - Поле - Ответственный', '', 13012);
insert into privilege_action(privilege, action)
values('carRequestCreator', 'READ');
insert into privilege_action(privilege, action)
values('carRequestCreator', 'WRITE');
insert into privilege_action(privilege, action)
values('carRequestCreator', 'EDIT');

insert into privilege(id, name, description, pos)
values('carRequestPriority', 'Заявка на автомобиль - Поле - Приоритет', '', 13013);
insert into privilege_action(privilege, action)
values('carRequestPriority', 'READ');
insert into privilege_action(privilege, action)
values('carRequestPriority', 'WRITE');
insert into privilege_action(privilege, action)
values('carRequestPriority', 'EDIT');

insert into privilege(id, name, description, pos)
values('carRequestStart', 'Заявка на автомобиль - Поле - Дата заявки', '', 13014);
insert into privilege_action(privilege, action)
values('carRequestStart', 'READ');
insert into privilege_action(privilege, action)
values('carRequestStart', 'WRITE');
insert into privilege_action(privilege, action)
values('carRequestStart', 'EDIT');

insert into privilege(id, name, description, pos)
values('carRequestEndActual', 'Заявка на автомобиль - Поле - Доставлено', '', 13015);
insert into privilege_action(privilege, action)
values('carRequestEndActual', 'READ');
insert into privilege_action(privilege, action)
values('carRequestEndActual', 'WRITE');
insert into privilege_action(privilege, action)
values('carRequestEndActual', 'EDIT');

insert into privilege(id, name, description, pos)
values('carRequestDeclaration', 'Заявка на автомобиль - Поле - № декларации', '', 13016);
insert into privilege_action(privilege, action)
values('carRequestDeclaration', 'READ');
insert into privilege_action(privilege, action)
values('carRequestDeclaration', 'WRITE');
insert into privilege_action(privilege, action)
values('carRequestDeclaration', 'EDIT');