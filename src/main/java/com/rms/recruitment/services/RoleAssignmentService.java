package com.rms.recruitment.services;

import com.rms.recruitment.dto.RoleAssignmentRequest;
import com.rms.recruitment.enums.RoleType;
import com.rms.recruitment.models.Roles;

import java.util.List;
import java.util.Optional;

public interface RoleAssignmentService {
    Roles assignRole(RoleAssignmentRequest request);

    Roles updateRole(Integer accountId, RoleType newRoleType, String description);

    void removeRole(Integer accountId);

    Optional<Roles> getRoleByAccountId(Integer accountId);

    List<Roles> getRolesByType(RoleType roleType);

    List<Roles> getAllRoles();
}