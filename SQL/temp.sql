
create table nomenclature_attachment(
  version int default 0,
  nomenclature int not null,
  attachment int not null,
  type varchar(100),
  PRIMARY KEY(nomenclature, attachment),
  CONSTRAINT nomenclature_attachment_FK1 FOREIGN KEY (nomenclature) REFERENCES nomenclature(id) ON DELETE CASCADE,
  CONSTRAINT nomenclature_attachment_FK2 FOREIGN KEY (attachment) REFERENCES attachment(id) ON DELETE CASCADE
);


create table nomenclature_attachment(
  id int not null primary key AUTO_INCREMENT,
  version int default 0,
  nomenclature int not null,
  attachment int not null,
  type varchar(100),
  CONSTRAINT nomenclature_attachment_FK1 FOREIGN KEY (nomenclature) REFERENCES nomenclature(id) ON DELETE CASCADE,
  CONSTRAINT nomenclature_attachment_FK2 FOREIGN KEY (attachment) REFERENCES attachment(id) ON DELETE CASCADE
);

create index nomenclature_attachment on nomenclature_attachment(nomenclature);