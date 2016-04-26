select CONCAT('addUserPA(userPA, "', privilege, '", "', action ,'");')
from privilege_action
order by privilege, action;