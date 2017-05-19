package com.app.security;

import com.app.data.entity.PrivilegeAction;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

/**
 * Created by ayaroslavtsev on 31.01.2017.
 */
public class CarRequestScreenPC implements PrivilegeChecker  {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAdd", "EXECUTE"))
        );

        userPA.put("editR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestEdit", "READ"))
        );
        userPA.put("editEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestEdit", "EXECUTE"))
        );

        userPA.put("deleteR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestDelete", "READ"))
        );
        userPA.put("deleteEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestDelete", "EXECUTE"))
        );

        userPA.put("nameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestName", "READ"))
        );
        userPA.put("nameW", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestName", "WRITE"))
        );
        userPA.put("nameE", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestName", "EDIT"))
        );

        userPA.put("responsibleR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestResponsible", "READ"))
        );
        userPA.put("responsibleW", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestResponsible", "WRITE"))
        );
        userPA.put("responsibleE", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestResponsible", "EDIT"))
        );

        userPA.put("addressFromR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAddressFrom", "READ"))
        );
        userPA.put("addressFromW", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAddressFrom", "WRITE"))
        );
        userPA.put("addressFromE", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAddressFrom", "EDIT"))
        );

        userPA.put("addressToR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAddressTo", "READ"))
        );
        userPA.put("addressToW", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAddressTo", "WRITE"))
        );
        userPA.put("addressToE", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAddressTo", "EDIT"))
        );

        userPA.put("receiverNameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestReceiverName", "READ"))
        );
        userPA.put("receiverNameW", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestReceiverName", "WRITE"))
        );
        userPA.put("receiverNameE", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestReceiverName", "EDIT"))
        );

        userPA.put("receiverPhoneR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestReceiverPhone", "READ"))
        );
        userPA.put("receiverPhoneW", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestReceiverPhone", "WRITE"))
        );
        userPA.put("receiverPhoneE", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestReceiverPhone", "EDIT"))
        );

        userPA.put("paymentR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestPayment", "READ"))
        );
        userPA.put("paymentW", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestPayment", "WRITE"))
        );
        userPA.put("paymentE", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestPayment", "EDIT"))
        );

        userPA.put("descriptionR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestDescription", "READ"))
        );
        userPA.put("descriptionW", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestDescription", "WRITE"))
        );
        userPA.put("descriptionE", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestDescription", "EDIT"))
        );

        userPA.put("attachmentR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAttachment", "READ"))
        );
        userPA.put("attachmentW", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAttachment", "WRITE"))
        );
        userPA.put("attachmentE", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestAttachment", "EDIT"))
        );

        userPA.put("creatorR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestCreator", "READ"))
        );
        userPA.put("creatorW", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestCreator", "WRITE"))
        );
        userPA.put("creatorE", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestCreator", "EDIT"))
        );

        userPA.put("priorityR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestPriority", "READ"))
        );
        userPA.put("priorityW", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestPriority", "WRITE"))
        );
        userPA.put("priorityE", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestPriority", "EDIT"))
        );

        userPA.put("startR", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestStart", "READ"))
        );
        userPA.put("startW", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestStart", "WRITE"))
        );
        userPA.put("startE", hasAnyPrivilegeAction(
                        new PrivilegeAction("carRequestStart", "EDIT"))
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

        return userPA;
    }
}
