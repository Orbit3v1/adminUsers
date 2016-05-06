create table nomenclature(
  id int not null primary key AUTO_INCREMENT,
  version int default 0,
  name varchar(500) not null,
  description varchar(4000)
);

create unique index nomenclature_U1 on nomenclature(name);