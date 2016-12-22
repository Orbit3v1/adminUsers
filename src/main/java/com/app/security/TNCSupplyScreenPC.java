package com.app.security;

import com.app.data.entity.PrivilegeAction;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

public class TNCSupplyScreenPC implements PrivilegeChecker{
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
        userPA.put("nameW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyName", "WRITE"))
        );
        userPA.put("nameE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyName", "EDIT"))
        );

        userPA.put("addItemR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItemAdd", "READ"))
        );
        userPA.put("addItemEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItemAdd", "EXECUTE"))
        );

        userPA.put("deleteItemR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItemDelete", "READ"))
        );
        userPA.put("deleteItemEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItemDelete", "EXECUTE"))
        );

        userPA.put("itemR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItem", "READ"))
        );
        userPA.put("itemW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItem", "WRITE"))
        );
        userPA.put("itemE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyItem", "EDIT"))
        );

        userPA.put("providerR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyProvider", "READ"))
        );
        userPA.put("providerW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyProvider", "WRITE"))
        );
        userPA.put("providerE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyProvider", "EDIT"))
        );

        userPA.put("accountNumberR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyAccountNumber", "READ"))
        );
        userPA.put("accountNumberW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyAccountNumber", "WRITE"))
        );
        userPA.put("accountNumberE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyAccountNumber", "EDIT"))
        );

        userPA.put("deliveryTypeR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyDeliveryType", "READ"))
        );
        userPA.put("deliveryTypeW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyDeliveryType", "WRITE"))
        );
        userPA.put("deliveryTypeE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyDeliveryType", "EDIT"))
        );

        userPA.put("startR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyStart", "READ"))
        );
        userPA.put("startW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyStart", "WRITE"))
        );
        userPA.put("startE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyStart", "EDIT"))
        );

        userPA.put("endPlanR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyEndPlan", "READ"))
        );
        userPA.put("endPlanW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyEndPlan", "WRITE"))
        );
        userPA.put("endPlanE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyEndPlan", "EDIT"))
        );

        userPA.put("endActualR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyEndActual", "READ"))
        );
        userPA.put("endActualW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyEndActual", "WRITE"))
        );
        userPA.put("endActualE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyEndActual", "EDIT"))
        );

        userPA.put("paymentDateR", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyPaymentDate", "READ"))
        );
        userPA.put("paymentDateW", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyPaymentDate", "WRITE"))
        );
        userPA.put("paymentDateE", hasAnyPrivilegeAction(
                        new PrivilegeAction("TNCSupplyPaymentDate", "EDIT"))
        );

        return userPA;
    }
}
