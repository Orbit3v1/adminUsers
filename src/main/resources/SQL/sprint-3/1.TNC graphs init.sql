create table tnc_order(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  name varchar(100) not null,
  creator int,
  responsible int,
  start datetime,
  endPlan datetime,

  CONSTRAINT tnc_order_FK1 FOREIGN KEY (creator) REFERENCES person(id),
  CONSTRAINT tnc_order_FK2 FOREIGN KEY (responsible) REFERENCES person(id)
);

create index tnc_order_I1 on tnc_order(creator);
create index tnc_order_I2 on tnc_order(responsible);
create index tnc_order_I3 on tnc_order(name);


create table tnc_order_item(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  name varchar(100) not null,
  calc_tnc int not null,
  cnt int,
  reason varchar(2000),
  endPlan datetime,
  endActual datetime,
  status varchar(100),

  CONSTRAINT tnc_order_item_FK1 FOREIGN KEY (calc_tnc) REFERENCES calc_tnc(id)
);

create index tnc_order_item_I1 on tnc_order_item(calc_tnc);
create index tnc_order_item_I2 on tnc_order_item(name);
create index tnc_order_item_U1 on tnc_order_item(calc_tnc, name);

create table tncOrderListFilter(
  id int not null primary key,
  version int default 0,
  name varchar(500),
  tnc varchar(500),
  creator varchar(500),
  developer varchar(500),

  startL date,
  startH date,
  endPlanL date,
  endPlanH date,
  endActualL date,
  endActualH date,

  state varchar(500),
  sort varchar(500),

  CONSTRAINT tncOrderListFilter_FK1 FOREIGN KEY (id) REFERENCES person(id)
);