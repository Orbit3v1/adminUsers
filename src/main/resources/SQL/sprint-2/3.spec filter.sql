create table specificationListFilter(
  id int not null primary key,
  version int default 0,
  name varchar(500),
  type varchar(500),
  nomenclature varchar(500),
  price varchar(500),
  discount varchar(500),
  responsible varchar(500),
  developer varchar(500),

  start date,
  responseDate date,

  sort varchar(500),

  CONSTRAINT specificationListFilter_FK1 FOREIGN KEY (id) REFERENCES person(id) ON DELETE CASCADE
);


ALTER TABLE orderListFilter
   DROP CONSTRAINT orderListFilter_FK1;

ALTER TABLE orderListFilter
   ADD CONSTRAINT orderListFilter_FK1
   FOREIGN KEY (id) REFERENCES person(id) ON DELETE CASCADE;

