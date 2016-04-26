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

    public static Person getCurrentUser(){
        return (Person) SessionUtil.getSessionVariable("user");
    }

    public static boolean hasAnyPrivilegeAction(PrivilegeAction ... privs){
        Person user = getCurrentUser();
        List<PrivilegeAction> privilegeActions = Arrays.asList(privs);
        if(user != null){
            for(Role role : user.getRoles()){
                if(!Collections.disjoint(role.getPrivilegeAction(), privilegeActions)){
                    return true;
                }
            }
        }
        return false;
    }

    public static Map<String, Boolean> getUserPrivilegeAction(String screenName){
        Map<String, Boolean> userPA = new HashMap<>();
        switch (screenName){
            case("headerMenu"):
                addUserPA(userPA, "graphMenu", "READ");
                addUserPA(userPA, "adminMenu", "READ");
                break;
            case("personScreen"):
                addUserPA(userPA, "personActive", "READ");
                addUserPA(userPA, "personDelete", "READ");
                addUserPA(userPA, "personDelete", "WRITE");
                addUserPA(userPA, "personEdit", "READ");
                addUserPA(userPA, "personEdit", "WRITE");
                addUserPA(userPA, "personEmail", "READ");
                addUserPA(userPA, "personFirstName", "READ");
                addUserPA(userPA, "personLastName", "READ");
                addUserPA(userPA, "personLogin", "READ");
                addUserPA(userPA, "personPassword", "READ");
                addUserPA(userPA, "personRoles", "READ");
                break;
            case("personList"):
                addUserPA(userPA, "personActive", "READ");
                addUserPA(userPA, "personEdit", "READ");
                addUserPA(userPA, "personEdit", "WRITE");
                addUserPA(userPA, "personEmail", "READ");
                addUserPA(userPA, "personFirstName", "READ");
                addUserPA(userPA, "personLastName", "READ");
                addUserPA(userPA, "personLogin", "READ");
                addUserPA(userPA, "personPassword", "READ");
                addUserPA(userPA, "personRoles", "READ");
                break;
        }
        return userPA;
    }

    private static void addUserPA(Map<String, Boolean> map, String privilegeId, String actionId){
        String key = privilegeId + "_" + actionId;
        PrivilegeAction pa = new PrivilegeAction(new PrivilegeActionId(privilegeId, actionId));
        map.put(key, hasAnyPrivilegeAction(pa));
    }

}



