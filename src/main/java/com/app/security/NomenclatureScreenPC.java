package com.app.security;

import com.app.data.entity.PrivilegeAction;
import com.app.data.entity.PrivilegeActionId;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

public class NomenclatureScreenPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        Map<String, Boolean> userPA = new HashMap<>();

        userPA.put("addR", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureAdd", "READ"))
        );
        userPA.put("addEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureAdd", "EXECUTE"))
        );

        userPA.put("editR", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureEdit", "READ"))
        );
        userPA.put("editEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureEdit", "EXECUTE"))
        );

        userPA.put("deleteR", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureDelete", "READ"))
        );
        userPA.put("deleteEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureDelete", "EXECUTE"))
        );

        userPA.put("nameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureName", "READ"),
                        new PrivilegeAction("nomenclatureAdd", "WRITE"))
        );
        userPA.put("nameW", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureName", "WRITE"))
        );
        userPA.put("nameE", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureName", "EDIT"))
        );

        userPA.put("descriptionR", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureDescription", "READ"))
        );
        userPA.put("descriptionW", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureDescription", "WRITE"))
        );
        userPA.put("descriptionE", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureDescription", "EDIT"))
        );

        userPA.put("sketchesR", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureSketches", "READ"))
        );
        userPA.put("sketchesW", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureSketches", "WRITE"))
        );
        userPA.put("sketchesE", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureSketches", "EDIT"))
        );

        userPA.put("drawingsR", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureDrawings", "READ"))
        );
        userPA.put("drawingsW", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureDrawings", "WRITE"))
        );
        userPA.put("drawingsE", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureDrawings", "EDIT"))
        );

        userPA.put("materialR", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureMaterial", "READ"))
        );
        userPA.put("materialW", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureMaterial", "WRITE"))
        );
        userPA.put("materialE", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureMaterial", "EDIT"))
        );

        userPA.put("gibR", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureGib", "READ"))
        );
        userPA.put("gibW", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureGib", "WRITE"))
        );
        userPA.put("gibE", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureGib", "EDIT"))
        );

        userPA.put("readyR", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureReady", "READ"))
        );
        userPA.put("readyW", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureReady", "WRITE"))
        );
        userPA.put("readyE", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureReady", "EDIT"))
        );

        userPA.put("componentAddR", hasAnyPrivilegeAction(
                        new PrivilegeAction("componentAdd", "READ"))
        );
        userPA.put("componentAddEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("componentAdd", "EXECUTE"))
        );

        userPA.put("componentEditR", hasAnyPrivilegeAction(
                        new PrivilegeAction("componentEdit", "READ"))
        );
        userPA.put("componentEditEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("componentEdit", "EXECUTE"))
        );

        userPA.put("componentDeleteR", hasAnyPrivilegeAction(
                        new PrivilegeAction("componentDelete", "READ"))
        );
        userPA.put("componentDeleteEx", hasAnyPrivilegeAction(
                        new PrivilegeAction("componentDelete", "EXECUTE"))
        );

        userPA.put("componentNameR", hasAnyPrivilegeAction(
                        new PrivilegeAction("componentName", "READ"),
                        new PrivilegeAction("componentAdd", "WRITE"))
        );
        userPA.put("componentNameW", hasAnyPrivilegeAction(
                        new PrivilegeAction("componentName", "WRITE"))
        );
        userPA.put("componentNameE", hasAnyPrivilegeAction(
                        new PrivilegeAction("componentName", "EDIT"))
        );

        userPA.put("specificationR", hasAnyPrivilegeAction(
                        new PrivilegeAction("nomenclatureSpecification", "READ"))
        );

        return userPA;
    }
}
