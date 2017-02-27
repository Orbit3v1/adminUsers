create table specification(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  name varchar(500) not null,
  subName varchar(50),
  innerName varchar(500),
  description varchar(4000),
  nomenclature int,
  responsible int,
  start datetime,
  price varchar(100),
  discount varchar(50),
  type varchar(100),
  developer int,
  responseDate datetime,
  mainSize varchar(4000),
  detailSize varchar(4000),
  pressure varchar(4000),
  additional varchar(4000),
  workDays int,
  approved datetime,
  approvedBy int,
  checked varchar(1),
  material varchar(500),

  CONSTRAINT specification_FK1 FOREIGN KEY (nomenclature) REFERENCES nomenclature(id),
  CONSTRAINT specification_FK2 FOREIGN KEY (responsible) REFERENCES person(id),
  CONSTRAINT specification_FK3 FOREIGN KEY (developer) REFERENCES person(id),
  CONSTRAINT specification_FK4 FOREIGN KEY (approvedBy) REFERENCES person(id)
);

create unique index specification_U1 on specification(name, subName);
create index specification_I1 on specification(nomenclature);
create index specification_I2 on specification(responsible);
create index specification_I3 on specification(developer);


create table specification_attachment(
  id int IDENTITY(1,1) not null primary key,
  specification int not null,
  attachment int not null,
  CONSTRAINT specification_attachment_FK1 FOREIGN KEY (specification) REFERENCES specification(id) ON DELETE CASCADE,
  CONSTRAINT specification_attachment_FK2 FOREIGN KEY (attachment) REFERENCES attachment(id) ON DELETE CASCADE
);

create unique index specification_attachment_u1 on specification_attachment(specification, attachment);
