--drop TRIGGER tr_tnc_supply

CREATE TRIGGER tr_tnc_supply ON tnc_supply
	FOR UPDATE
AS 
BEGIN
	IF @@ROWCOUNT                        = 0 
     RETURN;
  
  DECLARE @request_items               CURSOR
	DECLARE @tnc_request_item            int
             
  SET @request_items                   = CURSOR FOR
     SELECT DISTINCT
            tsrm.tnc_request_item
       FROM 
            tnc_supply_request_item    tsrm
       JOIN
            tnc_supply_item            tsim
         ON tsrm.tnc_supply_item       = tsim.id
       JOIN 
            inserted                   ined
         ON ined.id                    = tsim.tnc_supply  
  
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