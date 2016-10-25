package com.app.security;

import com.app.data.entity.PrivilegeAction;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

public class MenuPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("graphMenuR", hasAnyPrivilegeAction(
                        new PrivilegeAction("graphMenu", "READ"))
        );

        userPA.put("adminMenuR", hasAnyPrivilegeAction(
                        new PrivilegeAction("adminMenu", "READ"))
        );

        userPA.put("calcMenuR", hasAnyPrivilegeAction(
                        new PrivilegeAction("calcMenu", "READ"))
        );

        userPA.put("adminMenuPersonR", hasAnyPrivilegeAction(
                        new PrivilegeAction("adminMenuPerson", "READ"))
        );

        userPA.put("adminMenuRoleR", hasAnyPrivilegeAction(
                        new PrivilegeAction("adminMenuRole", "READ"))
        );

        userPA.put("adminMenuNomenclatureR", hasAnyPrivilegeAction(
                        new PrivilegeAction("adminMenuNomenclature", "READ"))
        );

        userPA.put("graphicMenuProductionR", hasAnyPrivilegeAction(
                        new PrivilegeAction("graphicMenuProduction", "READ"))
        );
        userPA.put("graphicMenuSpecificationR", hasAnyPrivilegeAction(
                        new PrivilegeAction("graphicMenuSpecification", "READ"))
        );

        return userPA;
    }
}
