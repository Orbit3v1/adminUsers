package com.app.security;

import com.app.data.entity.PrivilegeAction;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

/**
 * Created by ayaroslavtsev on 18.05.2017.
 */
public class ServiceRequestListPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestAdd", "EXECUTE"))
        );

        userPA.put("nameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestName", "READ"))
        );
        userPA.put("nameW", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestName", "WRITE"))
        );
        userPA.put("nameE", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestName", "EDIT"))
        );

        userPA.put("responsibleR", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestResponsible", "READ"))
        );
        userPA.put("responsibleW", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestResponsible", "WRITE"))
        );
        userPA.put("responsibleE", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestResponsible", "EDIT"))
        );

        userPA.put("warrantyNumberR", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestWarrantyNumber", "READ"))
        );
        userPA.put("warrantyNumberW", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestWarrantyNumber", "WRITE"))
        );
        userPA.put("warrantyNumberE", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestWarrantyNumber", "EDIT"))
        );

        userPA.put("counterpartyR", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestCounterparty", "READ"))
        );
        userPA.put("counterpartyW", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestCounterparty", "WRITE"))
        );
        userPA.put("counterpartyE", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestCounterparty", "EDIT"))
        );

        userPA.put("contactNameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestСontactName", "READ"))
        );
        userPA.put("contactNameW", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestСontactName", "WRITE"))
        );
        userPA.put("contactNameE", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestСontactName", "EDIT"))
        );

        userPA.put("contactPhoneR", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestСontactPhone", "READ"))
        );
        userPA.put("contactPhoneW", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestСontactPhone", "WRITE"))
        );
        userPA.put("contactPhoneE", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestСontactPhone", "EDIT"))
        );

        userPA.put("paymentR", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestPayment", "READ"))
        );
        userPA.put("paymentW", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestPayment", "WRITE"))
        );
        userPA.put("paymentE", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestPayment", "EDIT"))
        );

        userPA.put("descriptionR", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestDescription", "READ"))
        );
        userPA.put("descriptionW", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestDescription", "WRITE"))
        );
        userPA.put("descriptionE", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestDescription", "EDIT"))
        );

        userPA.put("creatorR", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestCreator", "READ"))
        );
        userPA.put("creatorW", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestCreator", "WRITE"))
        );
        userPA.put("creatorE", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestCreator", "EDIT"))
        );

        userPA.put("resultR", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestResult", "READ"))
        );
        userPA.put("resultW", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestResult", "WRITE"))
        );
        userPA.put("resultE", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestResult", "EDIT"))
        );

        userPA.put("startR", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestStart", "READ"))
        );
        userPA.put("startW", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestStart", "WRITE"))
        );
        userPA.put("startE", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestStart", "EDIT"))
        );

        userPA.put("endActualR", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestEndActual", "READ"))
        );
        userPA.put("endActualW", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestEndActual", "WRITE"))
        );
        userPA.put("endActualE", hasAnyPrivilegeAction(
                        new PrivilegeAction("serviceRequestEndActual", "EDIT"))
        );

        return userPA;
    }
}
