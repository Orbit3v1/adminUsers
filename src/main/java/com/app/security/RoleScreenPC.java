package com.app.security;

import com.app.data.entity.PrivilegeAction;
import com.app.data.entity.PrivilegeActionId;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

public class RoleScreenPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleAdd", "EXECUTE"))
        );

        userPA.put("deleteR", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleDelete", "READ"))
        );
        userPA.put("deleteEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleDelete", "EXECUTE"))
        );

        userPA.put("editR", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleEdit", "READ"))
        );
        userPA.put("editEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleEdit", "EXECUTE"))
        );

        userPA.put("idR", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleId", "READ"),
                        new PrivilegeAction("roleAdd", "WRITE"))
        );
        userPA.put("idW", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleId", "WRITE"))
        );
        userPA.put("idE", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleId", "EDIT"))
        );

        userPA.put("nameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleName", "READ"),
                        new PrivilegeAction("roleAdd", "WRITE"))
        );
        userPA.put("nameW", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleName", "WRITE"))
        );
        userPA.put("nameE", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleName", "EDIT"))
        );

        userPA.put("descriptionR", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleDescription", "READ"))
        );
        userPA.put("descriptionW", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleDescription", "WRITE"))
        );
        userPA.put("descriptionE", hasAnyPrivilegeAction(
                        new PrivilegeAction("roleDescription", "EDIT"))
        );

        userPA.put("privilegesR", hasAnyPrivilegeAction(
                        new PrivilegeAction("rolePrivileges", "READ"))
        );
        userPA.put("privilegesW", hasAnyPrivilegeAction(
                        new PrivilegeAction("rolePrivileges", "WRITE"))
        );
        userPA.put("privilegesE", hasAnyPrivilegeAction(
                        new PrivilegeAction("rolePrivileges", "EDIT"))
        );

        return userPA;
    }
}
