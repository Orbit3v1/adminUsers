--drop table order_payment;

create table payment(
  id int IDENTITY(1,1) not null primary key,
  version int default 0,
  description  varchar(4000),
  amount numeric(19, 2),
  date datetime,
);

create table order_payment(
  orders int not null,
  payment int not null,
  PRIMARY KEY(orders, payment),
  CONSTRAINT order_payment_FK1 FOREIGN KEY (orders) REFERENCES orders(id) ON DELETE CASCADE,
  CONSTRAINT order_payment_FK2 FOREIGN KEY (payment) REFERENCES payment(id) ON DELETE CASCADE
);


alter table orders
add price numeric(19, 2)
;

alter table orders
add paid numeric(19, 2)
;