package utils;

import entity.Person;
import entity.PrivilegeAction;
import entity.PrivilegeActionId;
import entity.Role;
import org.hibernate.mapping.Collection;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


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

}



