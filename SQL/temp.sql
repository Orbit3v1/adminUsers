
create table nomenclature_attachment(
  id int not null primary key AUTO_INCREMENT,
  nomenclature int not null,
  attachment int not null,
  type varchar(100),
  CONSTRAINT nomenclature_attachment_FK1 FOREIGN KEY (nomenclature) REFERENCES nomenclature(id) ON DELETE CASCADE,
  CONSTRAINT nomenclature_attachment_FK2 FOREIGN KEY (attachment) REFERENCES attachment(id) ON DELETE CASCADE
);

create unique index nomenclature_attachment_u1 on nomenclature_attachment(nomenclature, attachment);