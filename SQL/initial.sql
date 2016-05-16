drop database appOwner;
create database appOwner;
use appOwner;

create table person(
  id int not null primary key AUTO_INCREMENT,
  version int default 0,
  first_name varchar(500),
  last_name varchar(500),
  email varchar(50),
  active varchar(1) default 'Y',
  login varchar(50) not null,
  password varchar(50) not null
);

create unique index person_U1 on person(email);
create unique index person_U2 on person(login);

create table role(
  id varchar(50) not null primary key,
  version int default 0,
  name varchar(50) not null,
  description varchar(500)
);

create unique index role_U1 on role(name);

create table privilege(
  id varchar(50) not null primary key,
  name varchar(50) not null,
  description varchar(500),
  pos int
);

create unique index privilege_U1 on privilege(name);

create table action(
  id varchar(50) not null primary key,
  name varchar(50) not null,
  description varchar(500)
);

create unique index action_U1 on action(name);


create table person_role(
  person int not null,
  role varchar(50) not null,
  PRIMARY KEY(person, role),
  CONSTRAINT person_role_FK1 FOREIGN KEY (person) REFERENCES person(id) ON DELETE CASCADE,
  CONSTRAINT person_role_FK2 FOREIGN KEY (role) REFERENCES role(id) ON DELETE CASCADE
);

create index person_role_i1 on person_role(role);

create table privilege_action(
  privilege varchar(50) not null,
  action varchar(50) not null,
  PRIMARY KEY(privilege, action),
  CONSTRAINT privilege_action_FK1 FOREIGN KEY (privilege) REFERENCES privilege(id) ON DELETE CASCADE,
  CONSTRAINT privilege_action_FK2 FOREIGN KEY (action) REFERENCES action(id) ON DELETE CASCADE
);

create index privilege_action_i1 on privilege_action(action);

create table role_privilege_action(
  role varchar(50) not null,
  privilege varchar(50) not null,
  action varchar(50) not null,
  PRIMARY KEY(role, privilege, action),
  CONSTRAINT role_privilege_action_FK1 FOREIGN KEY (role) REFERENCES role(id) ON DELETE CASCADE,
  CONSTRAINT role_privilege_action_FK2 FOREIGN KEY (privilege, action) REFERENCES privilege_action(privilege, action) ON DELETE CASCADE
);

create index role_privilege_action_i1 on role_privilege_action(privilege, action);


create table attachment(
  id int not null primary key AUTO_INCREMENT,
  version int default 0,
  name varchar(500) not null,
  content LONGBLOB not null,
  size int,
  type varchar(100)
);

create table nomenclature(
  id int not null primary key AUTO_INCREMENT,
  version int default 0,
  name varchar(500) not null,
  description varchar(4000)
);

create unique index nomenclature_U1 on nomenclature(name);


create table nomenclature_attachment(
  id int not null primary key AUTO_INCREMENT,
  nomenclature int not null,
  attachment int not null,
  type varchar(100),
  CONSTRAINT nomenclature_attachment_FK1 FOREIGN KEY (nomenclature) REFERENCES nomenclature(id) ON DELETE CASCADE,
  CONSTRAINT nomenclature_attachment_FK2 FOREIGN KEY (attachment) REFERENCES attachment(id) ON DELETE CASCADE
);

create unique index nomenclature_attachment_u1 on nomenclature_attachment(nomenclature, attachment);

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



