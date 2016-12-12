package com.app.security;

import com.app.data.entity.PrivilegeAction;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

/**
 * Created by ayaroslavtsev on 12.12.2016.
 */
public class TNCRequestItemScreenPC implements PrivilegeChecker{
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemAdd", "EXECUTE"))
        );

        userPA.put("deleteR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemDelete", "READ"))
        );
        userPA.put("deleteEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemDelete", "EXECUTE"))
        );

        userPA.put("editR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemEdit", "READ"))
        );
        userPA.put("editEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemEdit", "EXECUTE"))
        );

        userPA.put("nameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemName", "READ"))
        );
        userPA.put("nameW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemName", "WRITE"))
        );
        userPA.put("nameE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemName", "EDIT"))
        );

        userPA.put("tncR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemTNC", "READ"))
        );
        userPA.put("tncE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemTNC", "WRITE"))
        );
        userPA.put("tncW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemTNC", "EDIT"))
        );

        userPA.put("countR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemCount", "READ"))
        );
        userPA.put("countW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemCount", "WRITE"))
        );
        userPA.put("countE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemCount", "EDIT"))
        );

        userPA.put("reasonR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemReason", "READ"))
        );
        userPA.put("reasonW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemReason", "WRITE"))
        );
        userPA.put("reasonE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemReason", "EDIT"))
        );

        userPA.put("endPlanR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemEndPlan", "READ"))
        );
        userPA.put("endPlanW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemEndPlan", "WRITE"))
        );
        userPA.put("endPlanE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCRequestItemEndPlan", "EDIT"))
        );

        return userPA;
    }


}
