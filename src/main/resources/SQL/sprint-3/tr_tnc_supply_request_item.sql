--DROP TRIGGER tr_tnc_supply_request_item;

CREATE TRIGGER tr_tnc_supply_request_item ON tnc_supply_request_item
	FOR INSERT, DELETE
AS 
BEGIN
	IF @@ROWCOUNT                        = 0 
     RETURN;
     
  DECLARE @request_items               CURSOR   
	DECLARE @tnc_request_item            int
  
  SET @request_items                   = CURSOR FOR
  SELECT ISNULL(i.tnc_request_item, d.tnc_request_item)
		FROM 
         inserted                      i
    FULL OUTER JOIN 
         deleted                       d
      ON i.tnc_request_item            = d.tnc_request_item
      
  OPEN @request_items
  FETCH NEXT FROM @request_items
  INTO @tnc_request_item 

  WHILE @@FETCH_STATUS = 0
  BEGIN
    EXEC updateTNCRequestState @id = @tnc_request_item       
    FETCH NEXT FROM @request_items
    INTO @tnc_request_item
  END; 

  CLOSE @request_items;
  DEALLOCATE @request_items;    
END;