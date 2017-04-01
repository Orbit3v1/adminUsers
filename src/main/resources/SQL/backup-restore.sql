--1) backup
BACKUP DATABASE [appOwner]
TO DISK = 'C:\work\backup\appOwner.bak'
  WITH FORMAT,
    MEDIANAME = 'MyBackups',
    NAME = 'Full backup of my database'

--from cmd
SQLCMD -S (local) -U sa -P Orbit3v1 -Q "BACKUP DATABASE appOwner TO DISK = 'C:\work\backup\appOwner.bak' WITH FORMAT, MEDIANAME = 'MyBackups', NAME = 'Full backup of my database'"

--2) restore
--login
SQLCMD -S (local)

-- run the following query:
RESTORE FILELISTONLY FROM DISK='D:\application\backup\appOwner_1.bak';
go


--use the following query to restore a database to a new location.
RESTORE DATABASE [appOwner]
FROM DISK='D:\application\backup\appOwner_1.bak'
WITH
MOVE 'appOwner' TO 'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\appOwner.mdf',
MOVE 'appOwner_log' TO 'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\appOwner_log.ldf';
go

--if have problems with user 'owner' connection then need to recreate user:

drop user owner;
drop LOGIN owner;

USE master;
CREATE LOGIN owner WITH PASSWORD=N'Orbit3v!', DEFAULT_DATABASE=[master], CHECK_EXPIRATION=OFF, CHECK_POLICY=ON;

use appOwner;
CREATE USER owner FOR LOGIN owner;

ALTER USER owner WITH DEFAULT_SCHEMA = dbo;

GRANT ALTER ON SCHEMA::dbo TO owner;
GRANT all to owner;
EXEC sp_addrolemember db_owner,owner;

EXEC sp_addrolemember N'db_datareader', owner;
EXEC sp_addrolemember N'db_datawriter', owner;
go
