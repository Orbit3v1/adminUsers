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
RESTORE FILELISTONLY FROM DISK='d:\Business_Data.bak';


--Once we have these names, we will use the following query to restore a database to a new location.
RESTORE DATABASE [appOwner]
FROM DISK='d:\appOwner.bak'
WITH
MOVE 'Business_Data' TO 'D:\TSQL\Business_Data.mdf',
MOVE 'Business_Data_log' TO 'D:\TSQL\Business_Data_log.ldf';