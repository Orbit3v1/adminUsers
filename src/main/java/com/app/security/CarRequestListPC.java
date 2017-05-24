package com.app.security;

import com.app.data.entity.PrivilegeAction;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class CarRequestListPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAdd", "EXECUTE"))
        );

        userPA.put("nameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestName", "READ"))
        );

        userPA.put("responsibleR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestResponsible", "READ"))
        );

        userPA.put("addressFromR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAddressFrom", "READ"))
        );

        userPA.put("addressToR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAddressTo", "READ"))
        );

        userPA.put("receiverNameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestReceiverName", "READ"))
        );

        userPA.put("receiverPhoneR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestReceiverPhone", "READ"))
        );

        userPA.put("paymentR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestPayment", "READ"))
        );

        userPA.put("descriptionR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestDescription", "READ"))
        );

        userPA.put("attachmentR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAttachment", "READ"))
        );

        userPA.put("creatorR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestCreator", "READ"))
        );

        userPA.put("priorityR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestPriority", "READ"))
        );

        userPA.put("startR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestStart", "READ"))
        );

        userPA.put("endActualR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestEndActual", "READ"))
        );
        userPA.put("endActualW", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestEndActual", "WRITE"))
        );
        userPA.put("endActualE", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestEndActual", "EDIT"))
        );

        userPA.put("declarationR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestDeclaration", "READ"))
        );

        return userPA;
    }
}
