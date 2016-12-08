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
);

create index tnc_request_item_I1 on tnc_request_item(calc_tnc);
create index tnc_request_item_I2 on tnc_request_item(name);
create index tnc_request_item_I3 on tnc_request_item(tnc_request);
create index tnc_request_item_U1 on tnc_request_item(tnc_request, name);

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

  CONSTRAINT tncRequestListFilter_FK1 FOREIGN KEY (id) REFERENCES person(id)
);