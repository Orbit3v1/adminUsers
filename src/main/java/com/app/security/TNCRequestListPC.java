package com.app.security;

import com.app.data.entity.PrivilegeAction;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

public class TNCRequestListPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestAdd", "EXECUTE"))
        );

        userPA.put("deleteR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestDelete", "READ"))
        );
        userPA.put("deleteEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestDelete", "EXECUTE"))
        );

        userPA.put("editR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestEdit", "READ"))
        );
        userPA.put("editEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestEdit", "EXECUTE"))
        );

        userPA.put("nameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestName", "READ"))
        );

        userPA.put("tncR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemTNC", "READ"))
        );

        userPA.put("countR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemCount", "READ"))
        );

        userPA.put("tncLimitLowR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemTNC", "READ"))
        );

        userPA.put("tncLimitHighR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemTNC", "READ"))
        );

        userPA.put("creatorR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestCreator", "READ"))
        );

        userPA.put("stateR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemStatus", "READ"))
        );

        userPA.put("startR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestStart", "READ"))
        );

        userPA.put("endPlanR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestEndPlan", "READ"))
        );

        userPA.put("endActualR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemEndActual", "READ"))
        );

        return userPA;
    }
}
