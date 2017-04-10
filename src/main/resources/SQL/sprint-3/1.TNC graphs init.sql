create table tnc_request(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  name varchar(100) not null,
  creator int,
  responsible int,
  start datetime,
  endPlan datetime,
  
  CONSTRAINT tnc_request_FK1 FOREIGN KEY (creator) REFERENCES person(id),
  CONSTRAINT tnc_request_FK2 FOREIGN KEY (responsible) REFERENCES person(id)
);

create index tnc_request_I1 on tnc_request(creator);
create index tnc_request_I2 on tnc_request(responsible);
create index tnc_request_I3 on tnc_request(name);



create table tnc_request_item(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  tnc_request int not null,
  name varchar(100) not null,
  calc_tnc int not null,
  cnt int,
  reason varchar(2000),
  endPlan datetime,
  endActual datetime,
  status varchar(100),

  CONSTRAINT tnc_request_item_FK1 FOREIGN KEY (calc_tnc) REFERENCES calc_tnc(id)
  CONSTRAINT tnc_request_item_FK2 FOREIGN KEY (tnc_request) REFERENCES tnc_request(id)
);

create index tnc_request_item_I1 on tnc_request_item(calc_tnc);
create index tnc_request_item_I2 on tnc_request_item(name);
create index tnc_request_item_I3 on tnc_request_item(tnc_request);
create unique index tnc_request_item_U1 on tnc_request_item(tnc_request, name);

create table tncRequestListFilter(
  id int not null primary key,
  version int default 0,
  name varchar(500),
  tnc varchar(500),
  creator varchar(500),
  responsible varchar(500),

  startL date,
  startH date,
  endPlanL date,
  endPlanH date,
  endActualL date,
  endActualH date,

  state varchar(500),
  sort varchar(500),

  CONSTRAINT tncRequestListFilter_FK1 FOREIGN KEY (id) REFERENCES person(id) ON DELETE CASCADE
);


------
create table tnc_supply(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  name varchar(100) not null,
  provider varchar(500),
  account_number varchar(500),
  start datetime,
  paymentDate datetime,
  deliveryType varchar(500),
  endPlan datetime,
  endActual datetime
);

create unique index tnc_supply_U1 on tnc_supply(name);


create table tnc_supply_item(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  tnc_supply int not null,
  calc_tnc int not null,
  cnt int,

  CONSTRAINT tnc_supply_item_FK1 FOREIGN KEY (calc_tnc) REFERENCES calc_tnc(id),
  CONSTRAINT tnc_supply_item_FK2 FOREIGN KEY (tnc_supply) REFERENCES tnc_supply(id)
);

create index tnc_supply_item_I1 on tnc_supply_item(calc_tnc);
create index tnc_supply_item_I2 on tnc_supply_item(tnc_supply);


create table tnc_supply_request_item(
  tnc_supply_item int not null,
  tnc_request_item int not null,

  CONSTRAINT tnc_supply_request_item_FK1 FOREIGN KEY (tnc_supply_item) REFERENCES tnc_supply_item(id),
  CONSTRAINT tnc_supply_request_item_FK2 FOREIGN KEY (tnc_request_item) REFERENCES tnc_request_item(id)
);

create index tnc_supply_request_item_U1 on tnc_supply_request_item(tnc_supply_item, tnc_request_item);
create index tnc_supply_request_item_I1 on tnc_supply_request_item(tnc_request_item);


create table tncSupplyListFilter(
  id int not null primary key,
  version int default 0,
  name varchar(500),
  provider varchar(500),
  account_number varchar(500),
  tnc varchar(500),
  deliveryType varchar(500),

  startL date,
  startH date,
  endPlanL date,
  endPlanH date,
  endActualL date,
  endActualH date,
  paymentDateL date,
  paymentDateH date,

  sort varchar(500),

  CONSTRAINT tncSupplyListFilter_FK1 FOREIGN KEY (id) REFERENCES person(id) ON DELETE CASCADE
);