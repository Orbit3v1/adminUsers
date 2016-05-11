
create table orders(
  id int not null primary key AUTO_INCREMENT,
  name varchar(100) not null,
  version int default 0,
  customer varchar(500),
  nomenclature int not null,
  cnt int,
  material varchar(500),
  gib varchar(500),
  responsible int,
  start datetime,
  docDate datetime,
  developer int,
  endPlan datetime,
  endActual datetime,
  state varchar(50),
  
  CONSTRAINT orders_FK1 FOREIGN KEY (nomenclature) REFERENCES nomenclature(id),
  CONSTRAINT orders_FK2 FOREIGN KEY (responsible) REFERENCES person(id),
  CONSTRAINT orders_FK3 FOREIGN KEY (developer) REFERENCES person(id)
);

create index orders_I1 on orders(nomenclature);
create index orders_I2 on orders(responsible);
create index orders_I3 on orders(developer);
create unique index orders_U1 on orders(name);
