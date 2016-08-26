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

    public static boolean hasAccess(Map<String, Boolean> userPA, String access){
        return userPA.containsKey(access) && userPA.get(access);
    }

    public static Map<String, Boolean> getUserPrivilegeAction(String screenName) {
//        long startTime = System.currentTimeMillis();

        Map<String, Boolean> userPA = new HashMap<>();
        switch (screenName) {
            case ("personScreen"):

                userPA.put("editR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personEdit", "READ")))
                );
                userPA.put("editEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personEdit", "EXECUTE")))
                );

                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personAdd", "READ")))
                );
                userPA.put("addEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personAdd", "EXECUTE")))
                );

                userPA.put("deleteR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personDelete", "READ")))
                );
                userPA.put("deleteEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personDelete", "EXECUTE")))
                );

                userPA.put("activeR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personActive", "READ")))
                );
                userPA.put("activeW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personActive", "WRITE")))
                );
                userPA.put("activeE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personActive", "EDIT")))
                );

                userPA.put("emailR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personEmail", "READ")))
                );
                userPA.put("emailW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personEmail", "WRITE")))
                );
                userPA.put("emailE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personEmail", "EDIT")))
                );

                userPA.put("firstNameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personFirstName", "READ")))
                );
                userPA.put("firstNameW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personFirstName", "WRITE")))
                );
                userPA.put("firstNameE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personFirstName", "EDIT")))
                );

                userPA.put("lastNameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personLastName", "READ")))
                );
                userPA.put("lastNameW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personLastName", "WRITE")))
                );
                userPA.put("lastNameE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personLastName", "EDIT")))
                );

                userPA.put("loginR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personLastName", "READ")))
                );
                userPA.put("loginW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personLastName", "WRITE")))
                );
                userPA.put("loginE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personLastName", "EDIT")))
                );

                userPA.put("passwordR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personPassword", "READ")),
                                new PrivilegeAction(new PrivilegeActionId("personAdd", "WRITE")))

                );
                userPA.put("passwordW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personPassword", "WRITE")))
                );
                userPA.put("passwordE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personPassword", "EDIT")))
                );

                userPA.put("rolesR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personRoles", "READ")))
                );
                userPA.put("rolesW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personRoles", "WRITE")))
                );
                userPA.put("rolesE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personRoles", "EDIT")))
                );
                break;

            case ("personList"):
                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personAdd", "READ")))
                );
                userPA.put("addEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("personAdd", "EXECUTE")))
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
                                new PrivilegeAction(new PrivilegeActionId("personLastName", "READ")))
                );
                break;
            case ("roleList"):
                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleAdd", "READ")))
                );
                userPA.put("addEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleAdd", "EXECUTE")))
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
                userPA.put("addEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleAdd", "EXECUTE")))
                );

                userPA.put("deleteR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleDelete", "READ")))
                );
                userPA.put("deleteEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleDelete", "EXECUTE")))
                );

                userPA.put("editR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleEdit", "READ")))
                );
                userPA.put("editEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleEdit", "EXECUTE")))
                );

                userPA.put("idR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleId", "READ")),
                                new PrivilegeAction(new PrivilegeActionId("roleAdd", "WRITE")))
                );
                userPA.put("idW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleId", "WRITE")))
                );
                userPA.put("idE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleId", "EDIT")))
                );

                userPA.put("nameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleName", "READ")),
                                new PrivilegeAction(new PrivilegeActionId("roleAdd", "WRITE")))
                );
                userPA.put("nameW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleName", "WRITE")))
                );
                userPA.put("nameE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleName", "EDIT")))
                );

                userPA.put("descriptionR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleDescription", "READ")))
                );
                userPA.put("descriptionW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleDescription", "WRITE")))
                );
                userPA.put("descriptionE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("roleDescription", "EDIT")))
                );

                userPA.put("privilegesR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("rolePrivileges", "READ")))
                );
                userPA.put("privilegesW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("rolePrivileges", "WRITE")))
                );
                userPA.put("privilegesE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("rolePrivileges", "EDIT")))
                );
                break;
            case ("nomenclatureList"):
                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureAdd", "READ")))
                );
                userPA.put("addEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureAdd", "EXECUTE")))
                );
                break;
            case ("nomenclatureScreen"):
                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureAdd", "READ")))
                );
                userPA.put("addEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureAdd", "EXECUTE")))
                );

                userPA.put("editR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureEdit", "READ")))
                );
                userPA.put("editEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureEdit", "EXECUTE")))
                );

                userPA.put("deleteR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureDelete", "READ")))
                );
                userPA.put("deleteEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureDelete", "EXECUTE")))
                );

                userPA.put("nameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureName", "READ")),
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureAdd", "WRITE")))
                );
                userPA.put("nameW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureName", "WRITE")))
                );
                userPA.put("nameE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureName", "EDIT")))
                );

                userPA.put("descriptionR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureDescription", "READ")))
                );
                userPA.put("descriptionW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureDescription", "WRITE")))
                );
                userPA.put("descriptionE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureDescription", "EDIT")))
                );

                userPA.put("sketchesR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureSketches", "READ")))
                );
                userPA.put("sketchesW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureSketches", "WRITE")))
                );
                userPA.put("sketchesE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureSketches", "EDIT")))
                );

                userPA.put("drawingsR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureDrawings", "READ")))
                );
                userPA.put("drawingsW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureDrawings", "WRITE")))
                );
                userPA.put("drawingsE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureDrawings", "EDIT")))
                );

                userPA.put("materialR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureMaterial", "READ")))
                );
                userPA.put("materialW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureMaterial", "WRITE")))
                );
                userPA.put("materialE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureMaterial", "EDIT")))
                );

                userPA.put("gibR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureGib", "READ")))
                );
                userPA.put("gibW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureGib", "WRITE")))
                );
                userPA.put("gibE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureGib", "EDIT")))
                );

                userPA.put("readyR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureReady", "READ")))
                );
                userPA.put("readyW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureReady", "WRITE")))
                );
                userPA.put("readyE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureReady", "EDIT")))
                );

                userPA.put("componentAddR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("componentAdd", "READ")))
                );
                userPA.put("componentAddEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("componentAdd", "EXECUTE")))
                );

                userPA.put("componentEditR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("componentEdit", "READ")))
                );
                userPA.put("componentEditEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("componentEdit", "EXECUTE")))
                );

                userPA.put("componentDeleteR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("componentDelete", "READ")))
                );
                userPA.put("componentDeleteEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("componentDelete", "EXECUTE")))
                );

                userPA.put("componentNameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("componentName", "READ")),
                                new PrivilegeAction(new PrivilegeActionId("componentAdd", "WRITE")))
                );
                userPA.put("componentNmeW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("componentName", "WRITE")))
                );
                userPA.put("componentNmeE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("componentName", "EDIT")))
                );
                break;
            case ("orderScreen"):
                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderAdd", "READ")))
                );
                userPA.put("addEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderAdd", "EXECUTE")))
                );

                userPA.put("deleteR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderDelete", "READ")))
                );
                userPA.put("deleteEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderDelete", "EXECUTE")))
                );

                userPA.put("editR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderEdit", "READ")))
                );
                userPA.put("editEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderEdit", "EXECUTE")))
                );

                userPA.put("nameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderName", "READ")),
                                new PrivilegeAction(new PrivilegeActionId("orderAdd", "WRITE")))
                );
                userPA.put("nameW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderName", "WRITE")))
                );
                userPA.put("nameE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderName", "EDIT")))
                );

                userPA.put("customerR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderCustomer", "READ")))
                );
                userPA.put("customerW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderCustomer", "WRITE")))
                );
                userPA.put("customerE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderCustomer", "EDIT")))
                );

                userPA.put("nomenclatureR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemNomenclature", "READ")))
                );

                userPA.put("countR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemCount", "READ")))
                );

                userPA.put("responsibleR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderResponsible", "READ")))
                );
                userPA.put("responsibleW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderResponsible", "WRITE")))
                );
                userPA.put("responsibleE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderResponsible", "EDIT")))
                );

                userPA.put("startR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderStart", "READ")))
                );
                userPA.put("startW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderStart", "WRITE")))
                );
                userPA.put("startE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderStart", "EDIT")))
                );

                userPA.put("docDateR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDocDate", "READ")))
                );

                userPA.put("developerR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDeveloper", "READ")))
                );

                userPA.put("endPlanItemR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEndPlan", "READ")))
                );

                userPA.put("endPlanR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderEndPlan", "READ")))
                );
                userPA.put("endPlanW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderEndPlan", "WRITE")))
                );

                userPA.put("endActualR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEndActual", "READ")))
                );
                userPA.put("endActualW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEndActual", "WRITE")))
                );

                userPA.put("addItemR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemAdd", "READ")))
                );
                userPA.put("addItemEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemAdd", "EXECUTE")))
                );

                userPA.put("itemNameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemName", "READ")))
                );

                userPA.put("deleteItemR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDelete", "READ")))
                );
                userPA.put("deleteItemEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDelete", "EXECUTE")))
                );

                userPA.put("accessInWork", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemInWork", "READ")))
                );
                userPA.put("accessFinished", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemFinished", "READ")))
                );

                break;

            case ("orderList"):
                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderAdd", "READ")))
                );
                userPA.put("addEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderAdd", "EXECUTE")))
                );

                userPA.put("nameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderName", "READ")))
                );

                userPA.put("customerR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderCustomer", "READ")))
                );

                userPA.put("nomenclatureR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemNomenclature", "READ")))
                );

                userPA.put("countR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemCount", "READ")))
                );

                userPA.put("responsibleR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderResponsible", "READ")))
                );

                userPA.put("startR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderStart", "READ")))
                );

                userPA.put("docDateR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDocDate", "READ")))
                );
                userPA.put("docDateE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDocDate", "EDIT")))
                );

                userPA.put("developerR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDeveloper", "READ")))
                );
                userPA.put("developerE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDeveloper", "EDIT")))
                );

                userPA.put("endPlanR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEndPlan", "READ")))
                );
                userPA.put("endPlanE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEndPlan", "EDIT")))
                );

                userPA.put("endActualR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEndActual", "READ")))
                );
                userPA.put("endActualW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEndActual", "WRITE")))
                );

                userPA.put("materialR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureMaterial", "READ")))
                );

                userPA.put("gibR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("nomenclatureGib", "READ")))
                );

                userPA.put("accessInWork", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemInWork", "READ")))
                );
                userPA.put("accessFinished", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemFinished", "READ")))
                );

                break;
            case("orderItemScreen"): {

                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemAdd", "READ")))
                );
                userPA.put("addEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemAdd", "EXECUTE")))
                );

                userPA.put("deleteR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDelete", "READ")))
                );
                userPA.put("deleteEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDelete", "EXECUTE")))
                );

                userPA.put("editR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEdit", "READ")))
                );
                userPA.put("editEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEdit", "EXECUTE")))
                );

                userPA.put("nameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemName", "READ")))
                );
                userPA.put("nameW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemName", "WRITE")))
                );
                userPA.put("nameE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemName", "EDIT")))
                );

                userPA.put("nomenclatureR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemNomenclature", "READ")))
                );
                userPA.put("nomenclatureW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemNomenclature", "WRITE")))
                );
                userPA.put("nomenclatureE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemNomenclature", "EDIT")))
                );

                userPA.put("countR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemCount", "READ")))
                );
                userPA.put("countW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemCount", "WRITE")))
                );
                userPA.put("countE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemCount", "EDIT")))
                );

                userPA.put("docDateR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDocDate", "READ")))
                );
                userPA.put("docDateW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDocDate", "WRITE")))
                );
                userPA.put("docDateE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDocDate", "EDIT")))
                );

                userPA.put("developerR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDeveloper", "READ")))
                );
                userPA.put("developerW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDeveloper", "WRITE")))
                );
                userPA.put("developerE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemDeveloper", "EDIT")))
                );

                userPA.put("endPlanR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEndPlan", "READ")))
                );
                userPA.put("endPlanW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEndPlan", "WRITE")))
                );
                userPA.put("endPlanE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEndPlan", "EDIT")))
                );

                userPA.put("endActualR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEndActual", "READ")))
                );
                userPA.put("endActualW", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEndActual", "WRITE")))
                );
                userPA.put("endActualE", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemEndActual", "EDIT")))
                );

                userPA.put("addItemR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemAdd", "READ")))
                );
                userPA.put("addItemEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("orderItemAdd", "EXECUTE")))
                );

            }
            case("specificationList"):{
                userPA.put("addR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("specificationAdd", "READ")))
                );
                userPA.put("addEx", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("specificationAdd", "EXECUTE")))
                );

                userPA.put("nameR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("specificationName", "READ")))
                );

                userPA.put("startR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("specificationStart", "READ")))
                );

                userPA.put("typeR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("specificationType", "READ")))
                );

                userPA.put("nomenclatureR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("specificationNomenclature", "READ")))
                );

                userPA.put("priceR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("specificationPrice", "READ")))
                );

                userPA.put("discountR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("specificationDiscount", "READ")))
                );

                userPA.put("responseDateR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("specificationResponseDate", "READ")))
                );

                userPA.put("developerR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("specificationDeveloper", "READ")))
                );

                userPA.put("responsibleR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("specificationResponsible", "READ")))
                );
            }
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
                userPA.put("graphicMenuSpecificationR", hasAnyPrivilegeAction(
                                new PrivilegeAction(new PrivilegeActionId("graphicMenuSpecification", "READ")))
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



