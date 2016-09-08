--drop table calc_tnc;
--drop table calc_work;

create table calc_tnc(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  name varchar(500) not null,
  detailName  varchar(500),
  unitsFrom varchar(50),
  unitsTo varchar(50),
  ratio numeric,
  price numeric
);

create unique index calc_tnc_U1 on calc_tnc(name);

create table calc_work(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  name varchar(500) not null,
  detail  varchar(500),
  price numeric
);

create unique index calc_work_U1 on calc_work(name);


create table calc_product(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  parentId int,
  type varchar(500),
  name varchar(500),
  detail  varchar(500),
  height numeric,
  width numeric,
  length numeric,
  formula varchar(4000),
  calc_tnc int,
  calc_work int

  CONSTRAINT calc_product_FK1 FOREIGN KEY (parentId) REFERENCES calc_product(id),
  CONSTRAINT calc_product_FK2 FOREIGN KEY (calc_tnc) REFERENCES calc_tnc(id),
  CONSTRAINT calc_product_FK3 FOREIGN KEY (calc_work) REFERENCES calc_work(id),
);

create index calc_product_I1 on calc_product(parentId);