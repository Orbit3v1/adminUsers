package com.app.security;

import com.app.data.entity.PrivilegeAction;
import com.app.data.entity.PrivilegeActionId;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

public class PersonListPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("personAdd", "EXECUTE"))
        );

        userPA.put("activeR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personActive", "READ"))
        );

        userPA.put("emailR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personEmail", "READ"))
        );

        userPA.put("firstNameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personFirstName", "READ"))
        );

        userPA.put("lastNameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personLastName", "READ"))
        );

        userPA.put("loginR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personLastName", "READ"))
        );

        return userPA;
    }
}
