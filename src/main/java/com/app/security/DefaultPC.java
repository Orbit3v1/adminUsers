package com.app.security;

import com.app.data.entity.PrivilegeAction;
import com.app.data.entity.PrivilegeActionId;

import java.util.HashMap;
import java.util.Map;

import static com.app.security.Security.hasAnyPrivilegeAction;

public class DefaultPC implements PrivilegeChecker {
    @Override
    public Map<String, Boolean> getPrivileges() {
        return new HashMap<>();
    }
}
