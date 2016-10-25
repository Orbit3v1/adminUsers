package com.app.security;

import com.app.data.entity.PrivilegeAction;
import com.app.data.entity.PrivilegeActionId;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;


public class OrderListPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderAdd", "EXECUTE"))
        );

        userPA.put("nameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderName", "READ"))
        );

        userPA.put("customerR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderCustomer", "READ"))
        );

        userPA.put("nomenclatureR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemNomenclature", "READ"))
        );

        userPA.put("countR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemCount", "READ"))
        );

        userPA.put("responsibleR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderResponsible", "READ"))
        );

        userPA.put("startR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderStart", "READ"))
        );

        userPA.put("docDateR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemDocDate", "READ"))
        );
        userPA.put("docDateE", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemDocDate", "EDIT"))
        );

        userPA.put("developerR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemDeveloper", "READ"))
        );
        userPA.put("developerE", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemDeveloper", "EDIT"))
        );

        userPA.put("endPlanR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemEndPlan", "READ"))
        );
        userPA.put("endPlanE", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemEndPlan", "EDIT"))
        );

        userPA.put("endActualR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemEndActual", "READ"))
        );
        userPA.put("endActualW", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemEndActual", "WRITE"))
        );

        userPA.put("materialR", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureMaterial", "READ"))
        );

        userPA.put("gibR", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureGib", "READ"))
        );

        userPA.put("paidR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaid", "READ"))
        );

        userPA.put("accessInWork", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemInWork", "READ"))
        );
        userPA.put("accessFinished", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemFinished", "READ"))
        );

        return userPA;
    }
}
