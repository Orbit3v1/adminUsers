package com.app.security;

import com.app.data.entity.PrivilegeAction;


import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

public class SpecificationScreenPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationAdd", "EXECUTE"))
        );

        userPA.put("deleteR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDelete", "READ"))
        );
        userPA.put("deleteEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDelete", "EXECUTE"))
        );

        userPA.put("editR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationEdit", "READ"))
        );
        userPA.put("editEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationEdit", "EXECUTE"))
        );

        userPA.put("nameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationName", "READ"))
        );
        userPA.put("nameW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationName", "WRITE"))
        );
        userPA.put("nameE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationName", "EDIT"))
        );

        userPA.put("descriptionR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDescription", "READ"))
        );
        userPA.put("descriptionW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDescription", "WRITE"))
        );
        userPA.put("descriptionE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDescription", "EDIT"))
        );

        userPA.put("startR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationStart", "READ"))
        );
        userPA.put("startW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationStart", "WRITE"))
        );
        userPA.put("startE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationStart", "EDIT"))
        );

        userPA.put("typeR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationType", "READ"))
        );
        userPA.put("typeW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationType", "WRITE"))
        );
        userPA.put("typeE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationType", "EDIT"))
        );

        userPA.put("nomenclatureR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationNomenclature", "READ"))
        );
        userPA.put("nomenclatureW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationNomenclature", "WRITE"))
        );
        userPA.put("nomenclatureE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationNomenclature", "EDIT"))
        );

        userPA.put("priceR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationPrice", "READ"))
        );
        userPA.put("priceW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationPrice", "WRITE"))
        );
        userPA.put("priceE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationPrice", "EDIT"))
        );

        userPA.put("discountR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDiscount", "READ"))
        );
        userPA.put("discountW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDiscount", "WRITE"))
        );
        userPA.put("discountE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDiscount", "EDIT"))
        );

        userPA.put("responseDateR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationResponseDate", "READ"))
        );
        userPA.put("responseDateW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationResponseDate", "WRITE"))
        );
        userPA.put("responseDateE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationResponseDate", "EDIT"))
        );

        userPA.put("developerR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDeveloper", "READ"))
        );
        userPA.put("developerW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDeveloper", "WRITE"))
        );
        userPA.put("developerE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDeveloper", "EDIT"))
        );

        userPA.put("responsibleR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationResponsible", "READ"))
        );
        userPA.put("responsibleW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationResponsible", "WRITE"))
        );
        userPA.put("responsibleE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationResponsible", "EDIT"))
        );

        userPA.put("sketchesR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationSketches", "READ"))
        );
        userPA.put("sketchesW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationSketches", "WRITE"))
        );
        userPA.put("sketchesE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationSketches", "EDIT"))
        );

        userPA.put("mainSizeR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationMainSize", "READ"))
        );
        userPA.put("mainSizeW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationMainSize", "WRITE"))
        );
        userPA.put("mainSizeE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationMainSize", "EDIT"))
        );

        userPA.put("detailSizeR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDetailSize", "READ"))
        );
        userPA.put("detailSizeW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDetailSize", "WRITE"))
        );
        userPA.put("detailSizeE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationDetailSize", "EDIT"))
        );

        userPA.put("pressureR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationPressure", "READ"))
        );
        userPA.put("pressureW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationPressure", "WRITE"))
        );
        userPA.put("pressureE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationPressure", "EDIT"))
        );

        userPA.put("additionalR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationAdditional", "READ"))
        );
        userPA.put("additionalW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationAdditional", "WRITE"))
        );
        userPA.put("additionalE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationAdditional", "EDIT"))
        );

        userPA.put("workDaysR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationWorkDays", "READ"))
        );
        userPA.put("workDaysW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationWorkDays", "WRITE"))
        );
        userPA.put("workDaysE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationWorkDays", "EDIT"))
        );

        userPA.put("checkedR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationChecked", "READ"))
        );
        userPA.put("checkedW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationChecked", "WRITE"))
        );
        userPA.put("checkedE", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationChecked", "EDIT"))
        );

        userPA.put("approveR", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationApprove", "READ"))
        );
        userPA.put("approveW", hasAnyPrivilegeAction(
                        new PrivilegeAction("specificationApprove", "WRITE"))
        );

        return userPA;
    }
}
