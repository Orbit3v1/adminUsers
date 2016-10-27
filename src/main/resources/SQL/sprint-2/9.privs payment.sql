insert into privilege(id, name, description, pos)
values('orderPrice', 'Заказ - Поле - Цена', '', 4008);
insert into privilege_action(privilege, action)
values('orderPrice', 'READ');
insert into privilege_action(privilege, action)
values('orderPrice', 'WRITE');
insert into privilege_action(privilege, action)
values('orderPrice', 'EDIT');

insert into privilege(id, name, description, pos)
values('orderPaid', 'Заказ - Поле - Оплаченно %', '', 4009);
insert into privilege_action(privilege, action)
values('orderPaid', 'READ');


insert into privilege(id, name, description, pos)
values('orderPayment', 'Оплата заказа', '', 5500);
insert into privilege_action(privilege, action)
values('orderPayment', 'READ');

insert into privilege(id, name, description, pos)
values('orderPaymentAdd', 'Оплата заказа - добавить', '', 5501);
insert into privilege_action(privilege, action)
values('orderPaymentAdd', 'READ');
insert into privilege_action(privilege, action)
values('orderPaymentAdd', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('orderPaymentEdit', 'Оплата заказа - редактировать', '', 5502);
insert into privilege_action(privilege, action)
values('orderPaymentEdit', 'READ');
insert into privilege_action(privilege, action)
values('orderPaymentEdit', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('orderPaymentDelete', 'Оплата заказа - удалить', '', 5503);
insert into privilege_action(privilege, action)
values('orderPaymentDelete', 'READ');
insert into privilege_action(privilege, action)
values('orderPaymentDelete', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('orderPaymentDescription', 'Оплата заказа - Поле - Описание', '', 5504);
insert into privilege_action(privilege, action)
values('orderPaymentDescription', 'READ');
insert into privilege_action(privilege, action)
values('orderPaymentDescription', 'WRITE');
insert into privilege_action(privilege, action)
values('orderPaymentDescription', 'EDIT');

insert into privilege(id, name, description, pos)
values('orderPaymentAmount', 'Оплата заказа - Поле - Сумма', '', 5505);
insert into privilege_action(privilege, action)
values('orderPaymentAmount', 'READ');
insert into privilege_action(privilege, action)
values('orderPaymentAmount', 'WRITE');
insert into privilege_action(privilege, action)
values('orderPaymentAmount', 'EDIT');

insert into privilege(id, name, description, pos)
values('orderPaymentDate', 'Оплата заказа - Поле - Дата', '', 5506);
insert into privilege_action(privilege, action)
values('orderPaymentDate', 'READ');
insert into privilege_action(privilege, action)
values('orderPaymentDate', 'WRITE');
insert into privilege_action(privilege, action)
values('orderPaymentDate', 'EDIT');