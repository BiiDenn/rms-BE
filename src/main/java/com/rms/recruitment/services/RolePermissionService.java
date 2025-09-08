package com.rms.recruitment.services;

import com.rms.recruitment.enums.PermissionType;
import com.rms.recruitment.enums.RoleType;

import java.util.List;
import java.util.Set;

public interface RolePermissionService {
    Set<PermissionType> getPermissionsForRole(RoleType role);

    Set<String> getPermissionCodesForRole(RoleType role);

    boolean hasPermission(RoleType role, PermissionType permission);

    boolean hasPermission(String roleCode, String permissionCode);

    List<RoleType> getRolesWithInterviewPermission();
}