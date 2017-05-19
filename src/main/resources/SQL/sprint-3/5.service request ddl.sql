create table service_request(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  name varchar(100),
  creator int,
  responsible int,
  start datetime,
  endActual datetime,

  warranty_number varchar(200),
  counterparty varchar(500),
  contactName varchar(500),
  contactPhone varchar(500),
  payment varchar(500),
  description varchar(2000),
  address varchar(2000),
  result varchar(500),

  CONSTRAINT service_request_FK1 FOREIGN KEY (creator) REFERENCES person(id),
  CONSTRAINT service_request_FK2 FOREIGN KEY (responsible) REFERENCES person(id)
);

create index service_request_I1 on service_request(creator);
create index service_request_I2 on service_request(responsible);
create index service_request_I3 on service_request(name);

create table serviceRequestListFilter(
  id int not null primary key,
  version int default 0,
  name varchar(500),
  creator varchar(500),
  responsible varchar(500),

  startL date,
  startH date,
  endActualL date,
  endActualH date,

  warranty_number varchar(500),
  counterparty varchar(500),
  contact varchar(500),
  payment varchar(500),
  description varchar(500),
  address varchar(500),
  result varchar(500),

  sort varchar(500),
  state varchar(500),

  CONSTRAINT serviceRequestListFilter_FK1 FOREIGN KEY (id) REFERENCES person(id)  ON DELETE CASCADE
);