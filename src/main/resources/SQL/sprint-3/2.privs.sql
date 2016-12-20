insert into privilege(id, name, description, pos)
values('graphicMenuTNCRequest', 'Графики меню - Заявки ТНЦ', '', 210);
insert into privilege_action(privilege, action)
values('graphicMenuTNCRequest', 'READ');

--------------

insert into privilege(id, name, description, pos)
values('TNCRequestAdd', 'Заявка ТНЦ - добавить', '', 8000);
insert into privilege_action(privilege, action)
values('TNCRequestAdd', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestAdd', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('TNCRequestEdit', 'Заявка ТНЦ - редактировать', '', 8001);
insert into privilege_action(privilege, action)
values('TNCRequestEdit', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestEdit', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('TNCRequestDelete', 'Заявка ТНЦ - удалить', '', 8002);
insert into privilege_action(privilege, action)
values('TNCRequestDelete', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestDelete', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('TNCRequestName', 'Заявка ТНЦ - Поле - Наименование', '', 8003);
insert into privilege_action(privilege, action)
values('TNCRequestName', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestName', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCRequestName', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCRequestCreator', 'Заявка ТНЦ - Поле - Затребовал', '', 8004);
insert into privilege_action(privilege, action)
values('TNCRequestCreator', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestCreator', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCRequestCreator', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCRequestResponsible', 'Заявка ТНЦ - Поле - Ответственный', '', 8005);
insert into privilege_action(privilege, action)
values('TNCRequestResponsible', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestResponsible', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCRequestResponsible', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCRequestStart', 'Заявка ТНЦ - Поле - Дата заявки', '', 8006);
insert into privilege_action(privilege, action)
values('TNCRequestStart', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestStart', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCRequestStart', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCRequestEndPlan', 'Заявка ТНЦ - Поле - План поставки', '', 8007);
insert into privilege_action(privilege, action)
values('TNCRequestEndPlan', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestEndPlan', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCRequestEndPlan', 'EDIT');

------------

insert into privilege(id, name, description, pos)
values('TNCRequestItemAdd', 'Заявка ТНЦ Позиция - добавить', '', 9000);
insert into privilege_action(privilege, action)
values('TNCRequestItemAdd', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestItemAdd', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('TNCRequestItemEdit', 'Заявка ТНЦ Позиция - редактировать', '', 9001);
insert into privilege_action(privilege, action)
values('TNCRequestItemEdit', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestItemEdit', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('TNCRequestItemDelete', 'Заявка ТНЦ Позиция - удалить', '', 9002);
insert into privilege_action(privilege, action)
values('TNCRequestItemDelete', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestItemDelete', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('TNCRequestItemName', 'Заявка ТНЦ Позиция - Поле - Наименование', '', 9003);
insert into privilege_action(privilege, action)
values('TNCRequestItemName', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestItemName', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCRequestItemName', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCRequestItemTNC', 'Заявка ТНЦ Позиция - Поле - ТНЦ', '', 9004);
insert into privilege_action(privilege, action)
values('TNCRequestItemTNC', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestItemTNC', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCRequestItemTNC', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCRequestItemCount', 'Заявка ТНЦ Позиция - Поле - Кол-во', '', 9005);
insert into privilege_action(privilege, action)
values('TNCRequestItemCount', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestItemCount', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCRequestItemCount', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCRequestItemReason', 'Заявка ТНЦ Позиция - Поле - Причина', '', 9006);
insert into privilege_action(privilege, action)
values('TNCRequestItemReason', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestItemReason', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCRequestItemReason', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCRequestItemEndPlan', 'Заявка ТНЦ Позиция - Поле - План поставки', '', 9007);
insert into privilege_action(privilege, action)
values('TNCRequestItemEndPlan', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestItemEndPlan', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCRequestItemEndPlan', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCRequestItemEndActual', 'Заявка ТНЦ Позиция - Поле - Поставка', '', 9008);
insert into privilege_action(privilege, action)
values('TNCRequestItemEndActual', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestItemEndActual', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCRequestItemEndActual', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCRequestItemStatus', 'Заявка ТНЦ Позиция - Поле - Статус', '', 9009);
insert into privilege_action(privilege, action)
values('TNCRequestItemStatus', 'READ');
insert into privilege_action(privilege, action)
values('TNCRequestItemStatus', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCRequestItemStatus', 'EDIT');


--------------

insert into privilege(id, name, description, pos)
values('graphicMenuTNCSupply', 'Графики меню - Заявки на поставку ТНЦ', '', 211);
insert into privilege_action(privilege, action)
values('graphicMenuTNCSupply', 'READ');

--------------

insert into privilege(id, name, description, pos)
values('TNCSupplyAdd', 'Заявка на поставку ТНЦ - добавить', '', 11000);
insert into privilege_action(privilege, action)
values('TNCSupplyAdd', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyAdd', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('TNCSupplyEdit', 'Заявка на поставку ТНЦ - редактировать', '', 11001);
insert into privilege_action(privilege, action)
values('TNCSupplyEdit', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyEdit', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('TNCSupplyDelete', 'Заявка на поставку ТНЦ - удалить', '', 11002);
insert into privilege_action(privilege, action)
values('TNCSupplyDelete', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyDelete', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('TNCSupplyName', 'Заявка на поставку ТНЦ - Поле - Наименование', '', 11003);
insert into privilege_action(privilege, action)
values('TNCSupplyName', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyName', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCSupplyName', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCSupplyProvider', 'Заявка на поставку ТНЦ - Поле - Поставщик', '', 10003);
insert into privilege_action(privilege, action)
values('TNCSupplyProvider', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyProvider', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCSupplyProvider', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCSupplyAccountNumber', 'Заявка на поставку ТНЦ - Поле - № счета', '', 11004);
insert into privilege_action(privilege, action)
values('TNCSupplyAccountNumber', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyAccountNumber', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCSupplyAccountNumber', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCSupplyDeliveryType', 'Заявка на поставку ТНЦ - Поле - Вид доставки', '', 11005);
insert into privilege_action(privilege, action)
values('TNCSupplyDeliveryType', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyDeliveryType', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCSupplyDeliveryType', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCSupplyStart', 'Заявка на поставку ТНЦ - Поле - Дата взятия счета', '', 11006);
insert into privilege_action(privilege, action)
values('TNCSupplyStart', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyStart', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCSupplyStart', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCSupplyPaymentDate', 'Заявка на поставку ТНЦ - Поле - Дата оплаты', '', 11007);
insert into privilege_action(privilege, action)
values('TNCSupplyPaymentDate', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyPaymentDate', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCSupplyPaymentDate', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCSupplyEndPlan', 'Заявка на поставку ТНЦ - Поле - Планируемая доставка', '', 11008);
insert into privilege_action(privilege, action)
values('TNCSupplyEndPlan', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyEndPlan', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCSupplyEndPlan', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCSupplyEndActual', 'Заявка на поставку ТНЦ - Поле - Получение кладовщика', '', 11009);
insert into privilege_action(privilege, action)
values('TNCSupplyEndActual', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyEndActual', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCSupplyEndActual', 'EDIT');

insert into privilege(id, name, description, pos)
values('TNCSupplyItem', 'Заявка на поставку ТНЦ - Поле - Позиция', '', 11010);
insert into privilege_action(privilege, action)
values('TNCSupplyItem', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyItem', 'WRITE');
insert into privilege_action(privilege, action)
values('TNCSupplyItem', 'EDIT');
------------

insert into privilege(id, name, description, pos)
values('TNCSupplyItemAdd', 'Позиция заявки на поставку ТНЦ - добавить', '', 12000);
insert into privilege_action(privilege, action)
values('TNCSupplyItemAdd', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyItemAdd', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('TNCSupplyItemEdit', 'Позиция заявки на поставку ТНЦ - редактировать', '', 12001);
insert into privilege_action(privilege, action)
values('TNCSupplyItemEdit', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyItemEdit', 'EXECUTE');

insert into privilege(id, name, description, pos)
values('TNCSupplyItemDelete', 'Позиция заявки на поставку ТНЦ - удалить', '', 12002);
insert into privilege_action(privilege, action)
values('TNCSupplyItemDelete', 'READ');
insert into privilege_action(privilege, action)
values('TNCSupplyItemDelete', 'EXECUTE');