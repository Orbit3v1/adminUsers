create table tmp_res2(table_name varchar(50));

DECLARE @table_name VARCHAR(50)
DECLARE @statement NVARCHAR(max)

DECLARE db_cursor CURSOR
LOCAL FAST_FORWARD
FOR
Select table_name
From INFORMATION_SCHEMA.COLUMNS
where COLUMN_NAME = '_IDRRef'
OPEN db_cursor
FETCH NEXT FROM db_cursor INTO @table_name
WHILE @@FETCH_STATUS = 0
BEGIN

SET @statement = 'insert into tmp_res2  select ''' + @table_name + ''' table_name, count(*) cnt from ' + @table_name + ' where _IDRRef = 0xA94F6466B3050DDA11E3C08293C38C2E'

exec(@statement);

FETCH NEXT FROM db_cursor INTO @table_name
END
CLOSE db_cursor
DEALLOCATE db_cursor
go


select *
from tmp_res2