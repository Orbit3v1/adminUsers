alter table specification
alter column discount varchar(50)

drop index specification_U1 on specification;

alter table specification
alter column subName varchar(50);

create unique index specification_U1 on specification(name, subName);


alter table specification
add description varchar(4000);



insert into privilege(id, name, description, pos)
values('specificationDescription', 'ТЗ - Поле - Назначение изделия', '', 7019);
insert into privilege_action(privilege, action)
values('specificationDescription', 'READ');
insert into privilege_action(privilege, action)
values('specificationDescription', 'WRITE');
insert into privilege_action(privilege, action)
values('specificationDescription', 'EDIT');

update DATABASECHANGELOG
set md5sum = '7:a5a446d2358ea7eeed809d128c1defaa'
where filename = 'SQL/sprint-2/1.specification init.sql';


update DATABASECHANGELOG
set md5sum = '7:580a630d1a0093bad5a8e04982cb19f2'
where filename = 'SQL/sprint-2/4.more priv.sql';
