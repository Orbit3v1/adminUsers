package com.app.security;

import com.app.data.entity.PrivilegeAction;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

public class TNCSupplyItemScreenPC implements PrivilegeChecker{
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItemAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItemAdd", "EXECUTE"))
        );

        userPA.put("deleteR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItemDelete", "READ"))
        );
        userPA.put("deleteEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItemDelete", "EXECUTE"))
        );

        userPA.put("editR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItemEdit", "READ"))
        );
        userPA.put("editEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItemEdit", "EXECUTE"))
        );


        userPA.put("itemR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItem", "READ"))
        );
        userPA.put("itemW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItem", "WRITE"))
        );
        userPA.put("itemE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItem", "EDIT"))
        );

        return userPA;
    }
}
