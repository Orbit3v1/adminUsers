--MySQL
create database appOwner;

--MS SQL
USE master;
CREATE LOGIN owner WITH PASSWORD=N'Orbit3v!', DEFAULT_DATABASE=[master], CHECK_EXPIRATION=OFF, CHECK_POLICY=ON;

create database appOwner;
exec sp_configure 'contained database authentication', 1;
reconfigure;

alter database appOwner
set containment = partial;


use appOwner;
CREATE USER owner FOR LOGIN owner;
ALTER USER owner WITH DEFAULT_SCHEMA = dbo;

GRANT ALTER ON SCHEMA::dbo TO owner;
GRANT all to owner;
sp_addrolemember db_owner,owner;

EXEC sp_addrolemember N'db_datareader', owner;
EXEC sp_addrolemember N'db_datawriter', owner;

--db for 1C

create database owner1C
go
alter database owner1C
set containment = partial
go

use owner1C
go

create user owner1C with password = 'owner1C'
go
ALTER USER owner1C WITH DEFAULT_SCHEMA = dbo
go

GRANT ALTER ON SCHEMA::dbo TO owner1C
go
GRANT all to owner1C;
go
sp_addrolemember db_owner,owner1C
go

CREATE USER owner FOR LOGIN owner;
ALTER USER owner WITH DEFAULT_SCHEMA = dbo;

GRANT ALTER ON SCHEMA::dbo TO owner;
GRANT all to owner;
sp_addrolemember db_owner,owner;

EXEC sp_addrolemember N'db_datareader', owner;
EXEC sp_addrolemember N'db_datawriter', owner;


alter database owner1C set single_user with rollback immediate
go
--тут загружаем базу из конфигуратора 1с
alter database owner1C set multi_user
go