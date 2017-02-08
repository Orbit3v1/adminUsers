create table car_request(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  name varchar(100),
  creator int,
  responsible int,
  start datetime,
  endActual datetime,

  addressFrom varchar(2000),
  addressTo varchar(2000),
  receiverName varchar(500),
  receiverPhone varchar(500),
  payment varchar(500),
  description varchar(2000),
  priority int,

  CONSTRAINT car_request_FK1 FOREIGN KEY (creator) REFERENCES person(id),
  CONSTRAINT car_request_FK2 FOREIGN KEY (responsible) REFERENCES person(id)
);

create index car_request_I1 on tnc_request(creator);
create index car_request_I2 on tnc_request(responsible);
create index car_request_I3 on tnc_request(name);

create table car_request_attachment(
  id int IDENTITY(1,1) not null primary key,
  car_request int not null,
  attachment int not null,
  CONSTRAINT car_request_attachment_FK1 FOREIGN KEY (car_request) REFERENCES car_request(id) ON DELETE CASCADE,
  CONSTRAINT car_request_attachment_FK2 FOREIGN KEY (attachment) REFERENCES attachment(id) ON DELETE CASCADE
);

create unique index car_request_attachment_u1 on car_request_attachment(car_request, attachment);

create table carRequestListFilter(
  id int not null primary key,
  version int default 0,
  name varchar(500),
  creator varchar(500),
  responsible varchar(500),

  startL date,
  startH date,
  endActualL date,
  endActualH date,

  addressFrom varchar(500),
  addressTo varchar(500),

  receiverName varchar(500),
  receiverPhone varchar(500),
  payment varchar(500),
  description varchar(500),
  priority varchar(500),

  sort varchar(500),
  state varchar(500),

  CONSTRAINT carRequestListFilter_FK1 FOREIGN KEY (id) REFERENCES person(id)
);

