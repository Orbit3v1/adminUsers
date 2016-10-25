package com.app.security;

import com.app.data.entity.PrivilegeAction;
import com.app.data.entity.PrivilegeActionId;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;


public class OrderItemScreenPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

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

        return userPA;
    }
}
