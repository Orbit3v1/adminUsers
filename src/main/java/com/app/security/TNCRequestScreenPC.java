package com.app.security;

import com.app.data.entity.PrivilegeAction;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;


public class TNCRequestScreenPC implements PrivilegeChecker {
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
        userPA.put("nameW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestName", "WRITE"))
        );
        userPA.put("nameE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestName", "EDIT"))
        );

        userPA.put("creatorR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestCreator", "READ"))
        );
        userPA.put("creatorW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestCreator", "WRITE"))
        );
        userPA.put("creatorE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestCreator", "EDIT"))
        );

        userPA.put("responsibleR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestResponsible", "READ"))
        );
        userPA.put("responsibleW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestResponsible", "WRITE"))
        );
        userPA.put("responsibleE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestResponsible", "EDIT"))
        );

        userPA.put("startR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestStart", "READ"))
        );
        userPA.put("startW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestStart", "WRITE"))
        );
        userPA.put("startE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestStart", "EDIT"))
        );

        userPA.put("endPlanR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestEndPlan", "READ"))
        );
        userPA.put("endPlanW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestEndPlan", "WRITE"))
        );
        userPA.put("endPlanE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestEndPlan", "EDIT"))
        );

        userPA.put("addItemR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemAdd", "READ"))
        );
        userPA.put("addItemEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemAdd", "EXECUTE"))
        );

        userPA.put("deleteItemR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemDelete", "READ"))
        );
        userPA.put("deleteItemEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemDelete", "EXECUTE"))
        );

        userPA.put("TNCRequestItemName", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemName", "READ"))
        );

        userPA.put("itemTncR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemTNC", "READ"))
        );

        userPA.put("itemCountR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemCount", "READ"))
        );

        userPA.put("tncLimitLowR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemTNC", "READ"))
        );

        userPA.put("tncLimitHighR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemTNC", "READ"))
        );

        userPA.put("itemStateR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemStatus", "READ"))
        );

        userPA.put("itemEndPlanR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemEndPlan", "READ"))
        );

        userPA.put("itemEndActualR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemEndActual", "READ"))
        );

        userPA.put("itemReasonR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemReason", "READ"))
        );

        return userPA;
    }

}
