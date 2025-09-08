package com.rms.recruitment.services.Impl;

import com.rms.recruitment.enums.PermissionType;
import com.rms.recruitment.enums.RoleType;
import com.rms.recruitment.services.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    private final Map<RoleType, Set<PermissionType>> rolePermissions = new HashMap<>();

    public RolePermissionServiceImpl() {
        initializeRolePermissions();
    }

    private void initializeRolePermissions() {
        // Admin - có tất cả quyền
        rolePermissions.put(RoleType.ADMIN, EnumSet.allOf(PermissionType.class));

        // Recruiter - xử lý tuyển dụng + phỏng vấn
        rolePermissions.put(RoleType.RECRUITER, EnumSet.of(
                PermissionType.HANDLE_RECRUITMENT_REQUESTS,
                PermissionType.PROCESS_RECRUITMENT,
                PermissionType.MANAGE_CANDIDATES,
                PermissionType.CONDUCT_INTERVIEW,
                PermissionType.EVALUATE_CANDIDATES));

        // HRD - quản lý + phỏng vấn
        rolePermissions.put(RoleType.HRD, EnumSet.of(
                PermissionType.MANAGE_RECRUITMENT_REQUESTS,
                PermissionType.MANAGE_RECRUITMENT_TEAM,
                PermissionType.MANAGE_RECRUITMENT_POLICIES,
                PermissionType.MANAGE_CANDIDATES,
                PermissionType.CONDUCT_INTERVIEW,
                PermissionType.EVALUATE_CANDIDATES));

        // Requestor - gửi yêu cầu + phỏng vấn
        rolePermissions.put(RoleType.REQUESTOR, EnumSet.of(
                PermissionType.SUBMIT_RECRUITMENT_REQUEST,
                PermissionType.SELECT_CANDIDATES,
                PermissionType.CONDUCT_INTERVIEW,
                PermissionType.EVALUATE_CANDIDATES));

        // Interviewer - chỉ phỏng vấn
        rolePermissions.put(RoleType.INTERVIEWER, EnumSet.of(
                PermissionType.CONDUCT_INTERVIEW,
                PermissionType.EVALUATE_CANDIDATES));
    }

    @Override
    public Set<PermissionType> getPermissionsForRole(RoleType role) {
        return rolePermissions.getOrDefault(role, Collections.emptySet());
    }

    @Override
    public Set<String> getPermissionCodesForRole(RoleType role) {
        return getPermissionsForRole(role).stream()
                .map(PermissionType::getCode)
                .collect(java.util.stream.Collectors.toSet());
    }

    @Override
    public boolean hasPermission(RoleType role, PermissionType permission) {
        return getPermissionsForRole(role).contains(permission);
    }

    @Override
    public boolean hasPermission(String roleCode, String permissionCode) {
        try {
            RoleType role = RoleType.fromCode(roleCode);
            PermissionType permission = PermissionType.fromCode(permissionCode);
            return hasPermission(role, permission);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public List<RoleType> getRolesWithInterviewPermission() {
        return Arrays.asList(RoleType.RECRUITER, RoleType.HRD, RoleType.REQUESTOR, RoleType.INTERVIEWER);
    }
}
