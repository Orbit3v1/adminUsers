package com.app.security;

import com.app.data.entity.PrivilegeAction;
import com.app.data.entity.PrivilegeActionId;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

public class RoleListPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleAdd", "EXECUTE"))
        );

        userPA.put("idR", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleId", "READ"))
        );

        userPA.put("nameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleName", "READ"))
        );

        userPA.put("descriptionR", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleDescription", "READ"))
        );

        return userPA;
    }
}
