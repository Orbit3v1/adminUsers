package com.app.security;


import com.app.data.entity.PrivilegeAction;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

public class TNCSupplyListPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyAdd", "EXECUTE"))
        );

        userPA.put("deleteR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyDelete", "READ"))
        );
        userPA.put("deleteEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyDelete", "EXECUTE"))
        );

        userPA.put("editR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyEdit", "READ"))
        );
        userPA.put("editEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyEdit", "EXECUTE"))
        );

        userPA.put("nameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyName", "READ"))
        );

        userPA.put("itemR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItem", "READ"))
        );

        userPA.put("providerR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyProvider", "READ"))
        );

        userPA.put("accountNumberR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyAccountNumber", "READ"))
        );

        userPA.put("deliveryTypeR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyDeliveryType", "READ"))
        );

        userPA.put("startR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyStart", "READ"))
        );

        userPA.put("endPlanR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyEndPlan", "READ"))
        );

        userPA.put("endActualR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyEndActual", "READ"))
        );

        userPA.put("paymentDateR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyPaymentDate", "READ"))
        );

        return userPA;
    }
}
