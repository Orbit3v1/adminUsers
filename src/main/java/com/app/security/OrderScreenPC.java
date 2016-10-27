package com.app.security;

import com.app.data.entity.PrivilegeAction;
import com.app.data.entity.PrivilegeActionId;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;


public class OrderScreenPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderAdd", "EXECUTE"))
        );

        userPA.put("deleteR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderDelete", "READ"))
        );
        userPA.put("deleteEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderDelete", "EXECUTE"))
        );

        userPA.put("editR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderEdit", "READ"))
        );
        userPA.put("editEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderEdit", "EXECUTE"))
        );

        userPA.put("nameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderName", "READ"),
                        new PrivilegeAction("orderAdd", "WRITE"))
        );
        userPA.put("nameW", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderName", "WRITE"))
        );
        userPA.put("nameE", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderName", "EDIT"))
        );

        userPA.put("customerR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderCustomer", "READ"))
        );
        userPA.put("customerW", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderCustomer", "WRITE"))
        );
        userPA.put("customerE", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderCustomer", "EDIT"))
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
        userPA.put("responsibleW", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderResponsible", "WRITE"))
        );
        userPA.put("responsibleE", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderResponsible", "EDIT"))
        );

        userPA.put("startR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderStart", "READ"))
        );
        userPA.put("startW", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderStart", "WRITE"))
        );
        userPA.put("startE", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderStart", "EDIT"))
        );

        userPA.put("docDateR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemDocDate", "READ"))
        );

        userPA.put("developerR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemDeveloper", "READ"))
        );

        userPA.put("endPlanItemR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemEndPlan", "READ"))
        );

        userPA.put("endPlanR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderEndPlan", "READ"))
        );
        userPA.put("endPlanW", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderEndPlan", "WRITE"))
        );

        userPA.put("endActualR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemEndActual", "READ"))
        );
        userPA.put("endActualW", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemEndActual", "WRITE"))
        );

        userPA.put("priceR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPrice", "READ"))
        );
        userPA.put("priceW", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPrice", "WRITE"))
        );
        userPA.put("priceE", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPrice", "EDIT"))
        );

        userPA.put("paidR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaid", "READ"))
        );

        userPA.put("addItemR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemAdd", "READ"))
        );
        userPA.put("addItemEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemAdd", "EXECUTE"))
        );

        userPA.put("itemNameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemName", "READ"))
        );

        userPA.put("deleteItemR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemDelete", "READ"))
        );
        userPA.put("deleteItemEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderItemDelete", "EXECUTE"))
        );

        userPA.put("paymentR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPayment", "READ"))
        );

        userPA.put("addPaymentR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentAdd", "READ"))
        );
        userPA.put("addPaymentEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentAdd", "EXECUTE"))
        );

        userPA.put("deletePaymentR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentDelete", "READ"))
        );
        userPA.put("deletePaymentEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentDelete", "EXECUTE"))
        );

        userPA.put("editPaymentR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentEdit", "READ"))
        );
        userPA.put("editPaymentEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentEdit", "EXECUTE"))
        );

        userPA.put("paymentDescriptionR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentDescription", "READ"))
        );
        userPA.put("paymentDescriptionW", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentDescription", "WRITE"))
        );
        userPA.put("paymentDescriptionE", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentDescription", "EDIT"))
        );

        userPA.put("paymentAmountR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentAmount", "READ"))
        );
        userPA.put("paymentAmountW", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentAmount", "WRITE"))
        );
        userPA.put("paymentAmountE", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentAmount", "EDIT"))
        );

        userPA.put("paymentDateR", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentDate", "READ"))
        );
        userPA.put("paymentDateW", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentDate", "WRITE"))
        );
        userPA.put("paymentDateE", hasAnyPrivilegeAction(
                        new PrivilegeAction("orderPaymentDate", "EDIT"))
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
