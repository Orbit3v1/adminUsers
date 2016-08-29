alter table specification
alter column discount varchar(50)

drop index specification_U1 on specification;

alter table specification
alter column subName varchar(50);

create unique index specification_U1 on specification(name, subName);


update DATABASECHANGELOG
set md5sum = '7:ebd73079ffad8c099647fde9bed9f2e1'
where filename = 'SQL/sprint-2/1.specification init.sql'
