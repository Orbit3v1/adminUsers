package com.app.security;

import com.app.data.entity.PrivilegeAction;
import com.app.data.entity.PrivilegeActionId;

import java.util.HashMap;
import java.util.Map;
import static com.app.security.Security.hasAnyPrivilegeAction;

/**
 * Created by ayaroslavtsev on 24.10.2016.
 */
public class PersonScreenPC implements PrivilegeChecker{
    @Override
    public Map<String, Boolean> getPrivileges() {

        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("editR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personEdit", "READ"))
        );
        userPA.put("editEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("personEdit", "EXECUTE"))
        );

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("personAdd", "EXECUTE"))
        );

        userPA.put("deleteR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personDelete", "READ"))
        );
        userPA.put("deleteEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("personDelete", "EXECUTE"))
        );

        userPA.put("activeR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personActive", "READ"))
        );
        userPA.put("activeW", hasAnyPrivilegeAction(
                        new PrivilegeAction("personActive", "WRITE"))
        );
        userPA.put("activeE", hasAnyPrivilegeAction(
                        new PrivilegeAction("personActive", "EDIT"))
        );

        userPA.put("emailR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personEmail", "READ"))
        );
        userPA.put("emailW", hasAnyPrivilegeAction(
                        new PrivilegeAction("personEmail", "WRITE"))
        );
        userPA.put("emailE", hasAnyPrivilegeAction(
                        new PrivilegeAction("personEmail", "EDIT"))
        );

        userPA.put("firstNameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personFirstName", "READ"))
        );
        userPA.put("firstNameW", hasAnyPrivilegeAction(
                        new PrivilegeAction("personFirstName", "WRITE"))
        );
        userPA.put("firstNameE", hasAnyPrivilegeAction(
                        new PrivilegeAction("personFirstName", "EDIT"))
        );

        userPA.put("lastNameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personLastName", "READ"))
        );
        userPA.put("lastNameW", hasAnyPrivilegeAction(
                        new PrivilegeAction("personLastName", "WRITE"))
        );
        userPA.put("lastNameE", hasAnyPrivilegeAction(
                        new PrivilegeAction("personLastName", "EDIT"))
        );

        userPA.put("loginR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personLastName", "READ"))
        );
        userPA.put("loginW", hasAnyPrivilegeAction(
                        new PrivilegeAction("personLastName", "WRITE"))
        );
        userPA.put("loginE", hasAnyPrivilegeAction(
                        new PrivilegeAction("personLastName", "EDIT"))
        );

        userPA.put("passwordR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personPassword", "READ"),
                        new PrivilegeAction("personAdd", "WRITE"))

        );
        userPA.put("passwordW", hasAnyPrivilegeAction(
                        new PrivilegeAction("personPassword", "WRITE"))
        );
        userPA.put("passwordE", hasAnyPrivilegeAction(
                        new PrivilegeAction("personPassword", "EDIT"))
        );

        userPA.put("rolesR", hasAnyPrivilegeAction(
                        new PrivilegeAction("personRoles", "READ"))
        );
        userPA.put("rolesW", hasAnyPrivilegeAction(
                        new PrivilegeAction("personRoles", "WRITE"))
        );
        userPA.put("rolesE", hasAnyPrivilegeAction(
                        new PrivilegeAction("personRoles", "EDIT"))
        );

        return userPA;
    }
}
