--liquibase formatted sql
--changeset author:yaroand endDelimiter:;

insert into person(id, first_name, last_name, email, active, login, password)
values(4, 'Андрей', 'Ярославцев', 'duke007@ukr.net', 'Y', 'yaroand', 'c4ca4238a0b923820dcc509a6f75849b');


insert into role(id, name, description) values('ADMIN', 'Администратор', 'Имеет все права');

insert into role_privilege_action(role, privilege, action)
select 'ADMIN',
  privilege,
  action
from privilege_action;

insert into person_role(person, role) values(4, 'ADMIN');