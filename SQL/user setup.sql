--3
insert into person(id, first_name, last_name, email, active, login, password)
values(4, 'Андрей', 'Ярославцев', 'duke007@ukr.net', 'Y', 'yaroand', 'c4ca4238a0b923820dcc509a6f75849b');

insert into person(id, first_name, last_name, email, active, login, password)
values(10, 'Иван', 'Иванов', 'ivan@test.com', 'Y', 'ivan', 'c4ca4238a0b923820dcc509a6f75849b');

insert into role(id, name, description) values('ADMIN', 'Администратор', 'Имеет все права');
insert into role(id, name, description) values('USER', 'Сварщик', 'Права только на чтение');

insert into role_privilege_action(role, privilege, action)
select 'ADMIN',
  privilege,
  action
from privilege_action;

insert into role_privilege_action(role, privilege, action)
select 'USER',
  privilege,
  action
from privilege_action
where action = 'READ';

insert into person_role(person, role) values(4, 'ADMIN');
insert into person_role(person, role) values(10, 'USER');
