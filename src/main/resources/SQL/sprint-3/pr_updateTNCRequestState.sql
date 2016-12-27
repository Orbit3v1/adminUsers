CREATE /*ALTER*/ PROCEDURE updateTNCRequestState @id int
AS
BEGIN
  DECLARE @currentState              varchar(50)
  DECLARE @updateState               varchar(50)

  DECLARE @arrivedCNT                int
  DECLARE @paidCNT                   int
  DECLARE @accountCNT                int

  SELECT @currentState               = status
    FROM tnc_request_item
   WHERE id                          = @id

  SELECT
         @arrivedCNT                 = SUM(CASE WHEN tcsy.endActual is not null THEN 1 ELSE 0 END)
       , @paidCNT                    = SUM(CASE WHEN tcsy.paymentDate is not null THEN 1 ELSE 0 END)
       , @accountCNT                 = COUNT(*)
    FROM
         tnc_supply_request_item     tsrm
    JOIN
         tnc_supply_item             tsim
      ON tsrm.tnc_supply_item        = tsim.id
    JOIN
         tnc_supply                  tcsy
      ON tsim.tnc_supply             = tcsy.id
   WHERE
         tsrm.tnc_request_item       = @id

  IF @arrivedCNT                     > 0
     SET @updateState                = 'ARRIVED'
  ELSE IF @paidCNT                   > 0
     SET @updateState                = 'PAID'
  ELSE IF @accountCNT                > 0
     SET @updateState                = 'ACCOUNT'
  ELSE
     SET @updateState                = 'IN_WORK'

  UPDATE tnc_request_item
     SET
         status                     = @updateState
       , version                    = version + 1
   WHERE
         id = @id
     AND status                     != @updateState
END