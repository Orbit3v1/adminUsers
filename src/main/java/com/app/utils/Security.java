package com.app.utils;

import com.app.entity.Person;
import com.app.entity.PrivilegeAction;
import com.app.entity.PrivilegeActionId;
import com.app.entity.Role;

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
//        long startTime = System.currentTimeMillis();

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
                break;
            case ("nomenclatureList"):
                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureAdd", "READ")))
                );
                userPA.put("addW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureAdd", "WRITE")))
                );
                break;
            case ("nomenclatureScreen"):
                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureAdd", "READ")))
                );
                userPA.put("addW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureAdd", "WRITE")))
                );

                userPA.put("editR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureEdit", "READ")))
                );
                userPA.put("editW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureEdit", "WRITE")))
                );

                userPA.put("nameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureName", "READ")),
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureAdd", "WRITE")))
                );
                userPA.put("nameW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureName", "WRITE")),
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureAdd", "WRITE")))
                );

                userPA.put("descriptionR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureDescription", "READ")))
                );
                userPA.put("descriptionW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureDescription", "WRITE")))
                );

                userPA.put("sketchesR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureSketches", "READ")))
                );
                userPA.put("sketchesW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureSketches", "WRITE")))
                );

                userPA.put("drawingsR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureDrawings", "READ")))
                );
                userPA.put("drawingsW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureDrawings", "WRITE")))
                );

                userPA.put("materialR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureMaterial", "READ")))
                );
                userPA.put("materialW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureMaterial", "WRITE")))
                );

                userPA.put("gibR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureGib", "READ")))
                );
                userPA.put("gibW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureGib", "WRITE")))
                );

                userPA.put("readyR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureReady", "READ")))
                );
                userPA.put("readyW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureReady", "WRITE")))
                );
                break;
            case ("orderScreen"):
                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderAdd", "READ")))
                );
                userPA.put("addW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderAdd", "WRITE")))
                );

                userPA.put("deleteR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderDelete", "READ")))
                );
                userPA.put("deleteW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderDelete", "WRITE")))
                );

                userPA.put("editR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderEdit", "READ")))
                );
                userPA.put("editW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderEdit", "WRITE")))
                );

                userPA.put("nameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderName", "READ")),
                                new PrivilegeAction(new PrivilegeActionId("orderAdd", "WRITE")))
                );
                userPA.put("nameW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderName", "WRITE")),
                                new PrivilegeAction(new PrivilegeActionId("orderAdd", "WRITE")))
                );

                userPA.put("customerR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderCustomer", "READ")))
                );
                userPA.put("customerW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderCustomer", "WRITE")))
                );

                userPA.put("nomenclatureR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderNomenclature", "READ")))
                );
                userPA.put("nomenclatureW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderNomenclature", "WRITE")))
                );

                userPA.put("countR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderCount", "READ")))
                );
                userPA.put("countW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderCount", "WRITE")))
                );

                userPA.put("responsibleR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderResponsible", "READ")))
                );
                userPA.put("responsibleW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderResponsible", "WRITE")))
                );

                userPA.put("startR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderStart", "READ")))
                );
                userPA.put("startW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderStart", "WRITE")))
                );

                userPA.put("docDateR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderDocDate", "READ")))
                );
                userPA.put("docDateW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderDocDate", "WRITE")))
                );

                userPA.put("developerR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderDeveloper", "READ")))
                );
                userPA.put("developerW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderDeveloper", "WRITE")))
                );

                userPA.put("endPlanR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderEndPlan", "READ")))
                );
                userPA.put("endPlanW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderEndPlan", "WRITE")))
                );

                userPA.put("endActualR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderEndActual", "READ")))
                );
                userPA.put("endActualW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderEndActual", "WRITE")))
                );

                break;

            case ("orderList"):
                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderAdd", "READ")))
                );
                userPA.put("addW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderAdd", "WRITE")))
                );

                userPA.put("nameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderName", "READ")),
                                new PrivilegeAction(new PrivilegeActionId("orderAdd", "WRITE")))
                );

                userPA.put("customerR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderCustomer", "READ")))
                );

                userPA.put("nomenclatureR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderNomenclature", "READ")))
                );

                userPA.put("countR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderCount", "READ")))
                );

                userPA.put("responsibleR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderResponsible", "READ")))
                );

                userPA.put("startR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderStart", "READ")))
                );

                userPA.put("docDateR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderDocDate", "READ")))
                );

                userPA.put("developerR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderDeveloper", "READ")))
                );

                userPA.put("endPlanR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderEndPlan", "READ")))
                );

                userPA.put("endActualR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderEndActual", "READ")))
                );
                userPA.put("endActualW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderEndActual", "WRITE")))
                );

                break;
            case("menu"): {
                userPA.put("graphMenuR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("graphMenu", "READ")))
                );

                userPA.put("adminMenuR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("adminMenu", "READ")))
                );

                userPA.put("adminMenuPersonR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("adminMenuPerson", "READ")))
                );

                userPA.put("adminMenuRoleR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("adminMenuRole", "READ")))
                );

                userPA.put("adminMenuNomenclatureR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("adminMenuNomenclature", "READ")))
                );

                userPA.put("graphicMenuProductionR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("graphicMenuProduction", "READ")))
                );
                break;
            }

        }

//        long stopTime = System.currentTimeMillis();
//        long elapsedTime = stopTime - startTime;
//        System.out.println("Security Time: " + elapsedTime);
        return userPA;
    }

}



