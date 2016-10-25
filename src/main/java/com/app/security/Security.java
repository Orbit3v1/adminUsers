package com.app.security;

import com.app.data.entity.Person;
import com.app.data.entity.PrivilegeAction;
import com.app.data.entity.PrivilegeActionId;
import com.app.data.entity.Role;
import com.app.utils.SessionUtil;

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

        PrivilegeCheckerFactory pcFactory = PrivilegeCheckerFactory.getFactory();
        PrivilegeChecker pc = pcFactory.getChecker(screenName);
        return pc.getPrivileges();

//        long stopTime = System.currentTimeMillis();
//        long elapsedTime = stopTime - startTime;
//        System.out.println("Security Time: " + elapsedTime);
    }

}



