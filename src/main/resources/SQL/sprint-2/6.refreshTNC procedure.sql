CREATE PROCEDURE refreshTNC
AS   
UPDATE
       c
   SET   
       name                            = n._description
     , unitsFrom                       = s._description
     , price                           = p._fld8268
     , version                         = c.version + 1
  FROM 
       calc_tnc                        c
  JOIN 
       owner1C.dbo._Reference66        n
    ON n._IDRRef                       = c.link1C
  JOIN owner1C.dbo._Reference46        s    
    ON n._fld1029rref                  = s._IDRRef
  LEFT 
  JOIN owner1C.dbo._InfoRg8264         p
    ON p._fld8266rref                  = n._IDRRef 
 WHERE  
       c.name                          != n._description COLLATE DATABASE_DEFAULT
    OR c.unitsFrom                     != s._description COLLATE DATABASE_DEFAULT
    OR IsNull(c.price, -1)             != IsNull(p._fld8268, -1)
;
