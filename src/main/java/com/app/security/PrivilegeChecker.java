package com.app.security;

import java.util.Map;


public interface PrivilegeChecker {
    public Map<String, Boolean> getPrivileges();
}
