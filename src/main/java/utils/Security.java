package utils;

import entity.Person;
import entity.PrivilegeAction;
import entity.PrivilegeActionId;
import entity.Role;
import org.hibernate.mapping.Collection;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.util.*;


public class Security {

    public static Person getCurrentUser() {
        return (Person) SessionUtil.getSessionVariable("user");
    }

    public static boolean hasAnyPrivilegeAction(PrivilegeAction... privs) {
        Person user = getCurrentUser();
        List<PrivilegeAction> privilegeActions = Arrays.asList(privs);
        if (user != null) {
            for (Role role : user.getRoles()) {
                if (!Collections.disjoint(role.getPrivilegeAction(), privilegeActions)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Map<String, Boolean> getUserPrivilegeAction(String screenName) {
        long startTime = System.currentTimeMillis();

        Map<String, Boolean> userPA = new HashMap<>();
        switch (screenName) {
            case ("personScreen"):

                userPA.put("editR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personEdit", "READ")))
                );
                userPA.put("editW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personEdit", "WRITE")))
                );

                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personAdd", "READ")))
                );
                userPA.put("addW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personAdd", "WRITE")))
                );

                userPA.put("activeR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personActive", "READ")))
                );
                userPA.put("activeW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personActive", "WRITE")))
                );

                userPA.put("emailR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personEmail", "READ")))
                );
                userPA.put("emailW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personEmail", "WRITE")))
                );

                userPA.put("firstNameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personFirstName", "READ")))
                );
                userPA.put("firstNameW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personFirstName", "WRITE")))
                );

                userPA.put("lastNameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personLastName", "READ")))
                );
                userPA.put("lastNameW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personLastName", "WRITE")))
                );

                userPA.put("loginR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personLastName", "READ")),
                                new PrivilegeAction(new PrivilegeActionId("personAdd", "WRITE")))
                );
                userPA.put("loginW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personLastName", "WRITE")),
                                new PrivilegeAction(new PrivilegeActionId("personAdd", "WRITE")))
                );

                userPA.put("passwordR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personPassword", "READ")),
                                new PrivilegeAction(new PrivilegeActionId("personAdd", "WRITE")))

                );
                userPA.put("passwordW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personPassword", "WRITE")),
                                new PrivilegeAction(new PrivilegeActionId("personAdd", "WRITE")))
                );

                userPA.put("rolesR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personRoles", "READ")))
                );
                userPA.put("rolesW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personRoles", "WRITE")))
                );
                break;

            case ("personList"):
                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personAdd", "READ")))
                );
                userPA.put("addW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personAdd", "WRITE")))
                );

                userPA.put("activeR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personActive", "READ")))
                );

                userPA.put("emailR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personEmail", "READ")))
                );

                userPA.put("firstNameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personFirstName", "READ")))
                );

                userPA.put("lastNameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personLastName", "READ")))
                );

                userPA.put("loginR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personLastName", "READ")),
                                new PrivilegeAction(new PrivilegeActionId("personAdd", "WRITE")))
                );
                break;
            case ("roleList"):
                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleAdd", "READ")))
                );
                userPA.put("addW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleAdd", "WRITE")))
                );

                userPA.put("idR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleId", "READ")))
                );

                userPA.put("nameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleName", "READ")))
                );

                userPA.put("descriptionR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleDescription", "READ")))
                );
                break;
            case ("roleScreen"):
                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleAdd", "READ")))
                );
                userPA.put("addW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleAdd", "WRITE")))
                );

                userPA.put("deleteR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleDelete", "READ")))
                );
                userPA.put("deleteW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleDelete", "WRITE")))
                );

                userPA.put("editR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleEdit", "READ")))
                );
                userPA.put("editW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleEdit", "WRITE")))
                );

                userPA.put("idR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleId", "READ")),
                                new PrivilegeAction(new PrivilegeActionId("roleAdd", "WRITE")))
                );
                userPA.put("idW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleId", "WRITE")),
                                new PrivilegeAction(new PrivilegeActionId("roleAdd", "WRITE")))
                );

                userPA.put("nameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleName", "READ")),
                                new PrivilegeAction(new PrivilegeActionId("roleAdd", "WRITE")))
                );
                userPA.put("nameW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleName", "WRITE")),
                                new PrivilegeAction(new PrivilegeActionId("roleAdd", "WRITE")))
                );

                userPA.put("descriptionR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleDescription", "READ")))
                );
                userPA.put("descriptionW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleDescription", "WRITE")))
                );

                userPA.put("privilegesR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("rolePrivileges", "READ")))
                );
                userPA.put("privilegesW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("rolePrivileges", "WRITE")))
                );
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Security Time: " + elapsedTime);
        return userPA;
    }

}



