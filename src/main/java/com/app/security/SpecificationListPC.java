package com.app.security;

import com.app.data.entity.PrivilegeAction;
import com.app.data.entity.PrivilegeActionId;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

public class SpecificationListPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationAdd", "EXECUTE"))
        );

        userPA.put("nameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationName", "READ"))
        );

        userPA.put("startR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationStart", "READ"))
        );

        userPA.put("typeR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationType", "READ"))
        );

        userPA.put("nomenclatureR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationNomenclature", "READ"))
        );

        userPA.put("priceR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationPrice", "READ"))
        );

        userPA.put("discountR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDiscount", "READ"))
        );

        userPA.put("responseDateR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationResponseDate", "READ"))
        );

        userPA.put("developerR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDeveloper", "READ"))
        );

        userPA.put("responsibleR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationResponsible", "READ"))
        );

        return userPA;
    }
}
