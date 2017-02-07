--drop table calc_tnc;
--drop table calc_work;


create table calc_tnc(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  link1C binary(16),
  name varchar(500),
  detailName  varchar(500),
  unitsFrom varchar(50),
  unitsTo varchar(50),
  ratio numeric(19, 4),
  price numeric(19, 2),
  name_inner varchar(500),
  limit_low int,
  limit_high int,
  balance int
);

create index calc_tnc_I1 on calc_tnc(name);
create index calc_tnc_I2 on calc_tnc(name_inner);

create table tnc_attachment(
  id int IDENTITY(1,1) not null primary key,
  calc_tnc int not null,
  attachment int not null,
  type varchar(100),
  CONSTRAINT tnc_attachment_FK1 FOREIGN KEY (calc_tnc) REFERENCES calc_tnc(id) ON DELETE CASCADE,
  CONSTRAINT tnc_attachment_FK2 FOREIGN KEY (attachment) REFERENCES attachment(id) ON DELETE CASCADE
);

create unique index tnc_attachment_u1 on tnc_attachment(calc_tnc, attachment);

create table tnc_link(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  calc_tnc int not null,
  link varchar(500) not null,
  CONSTRAINT tnc_link_FK1 FOREIGN KEY (calc_tnc) REFERENCES calc_tnc(id) ON DELETE CASCADE,
);

create index tnc_link_u1 on tnc_link(calc_tnc);

create table calc_work(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  name varchar(500) not null,
  description  varchar(500),
  price numeric(19, 2)
);

create unique index calc_work_U1 on calc_work(name);


create table calc_product(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  parentId int,
  type varchar(500),
  name varchar(500),
  detail  varchar(500),
  description varchar(2000),
  height varchar(500),
  width varchar(500),
  length varchar(500),
  count varchar(500),
  heightAlias varchar(500),
  widthAlias varchar(500),
  lengthAlias varchar(500),
  countAlias varchar(500),
  formula varchar(4000),
  calc_tnc int,
  calc_work int

  CONSTRAINT calc_product_FK1 FOREIGN KEY (parentId) REFERENCES calc_product(id),
  CONSTRAINT calc_product_FK2 FOREIGN KEY (calc_tnc) REFERENCES calc_tnc(id),
  CONSTRAINT calc_product_FK3 FOREIGN KEY (calc_work) REFERENCES calc_work(id),
);

create index calc_product_I1 on calc_product(parentId);

create table calc_function(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  name varchar(500) not null,
  description varchar(4000),
  code varchar(4000)
);

create unique index calc_function_U1 on calc_function(name);


create table calc_productInParams(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  calc_product int,
  name varchar(500) not null,
  description varchar(4000),
  value varchar(4000),

  CONSTRAINT calc_productInParams_FK1 FOREIGN KEY (calc_product) REFERENCES calc_product(id),
);

create index calc_productInParams_I1 on calc_productInParams(calc_product, name);