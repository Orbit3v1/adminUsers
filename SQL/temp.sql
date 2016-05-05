create table attachment(
  id int not null primary key AUTO_INCREMENT,
  version int default 0,
  name varchar(500) not null,
  content LONGBLOB not null,
  size int,
  type varchar(100)
);