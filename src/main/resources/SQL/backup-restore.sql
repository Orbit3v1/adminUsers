--backup
BACKUP DATABASE [appOwner]
TO DISK = 'C:\work\backup\appOwner.bak'
  WITH FORMAT,
    MEDIANAME = 'MyBackups',
    NAME = 'Full backup of my database'

--from cmd
SQLCMD -S (local) -U sa -P Orbit3v1 -Q "BACKUP DATABASE appOwner TO DISK = 'C:\work\backup\appOwner.bak' WITH FORMAT, MEDIANAME = 'MyBackups', NAME = 'Full backup of my database'"

--restore
--Our first step will be to run the following query:
RESTORE FILELISTONLY FROM DISK='D:\application\backup\appOwner_3.bak';


--Once we have these names, we will use the following query to restore a database to a new location.
RESTORE DATABASE [appOwner]
FROM DISK='D:\application\backup\appOwner_3.bak'
WITH
MOVE 'appOwner' TO 'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\appOwner.mdf',
MOVE 'appOwner_log' TO 'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\appOwner_log.ldf';