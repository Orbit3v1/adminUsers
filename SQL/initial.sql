create database appOwner;
use appOwner;

create table appOwner.person(
  id int primary key AUTO_INCREMENT,
  first_name varchar(500),
  last_name varchar(500),
  email varchar(50),
  state varchar(50));

create table appOwner.role(
  id varchar(50) primary key,
  name varchar(50),
  description varchar(500)
);

create table appOwner.privilege(
  id varchar(50) primary key,
  name varchar(50),
  description varchar(500)
);

create table appOwner.person_role(
  person int,
  role varchar(50),
  PRIMARY KEY(person, role),
  CONSTRAINT person_role_FK1 FOREIGN KEY (person) REFERENCES person(id) ON DELETE CASCADE,
  CONSTRAINT person_role_FK2 FOREIGN KEY (role) REFERENCES role(id) ON DELETE CASCADE
);

create index person_role_i1 on person_role(role);

create table appOwner.role_privilege(
  role varchar(50),
  privilege varchar(50),
  PRIMARY KEY(role, privilege),
  CONSTRAINT role_privilege_FK1 FOREIGN KEY (role) REFERENCES role(id) ON DELETE CASCADE,
  CONSTRAINT role_privilege_FK2 FOREIGN KEY (privilege) REFERENCES privilege(id) ON DELETE CASCADE
);

create index role_privilege_i1 on role_privilege(privilege);  