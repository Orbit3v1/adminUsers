--MySQL
create database appOwner;

--MS SQL
create database appOwner;
exec sp_configure 'contained database authentication', 1;
reconfigure;

alter database appOwner
set containment = partial;


use appOwner;
create user owner with password = 'Orbit3v!';
ALTER USER owner WITH DEFAULT_SCHEMA = dbo;

GRANT ALTER ON SCHEMA::dbo TO owner;
GRANT all to owner;
sp_addrolemember db_owner,owner;