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