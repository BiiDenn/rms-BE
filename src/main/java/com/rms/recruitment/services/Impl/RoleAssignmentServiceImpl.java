package com.rms.recruitment.services.Impl;

import com.rms.recruitment.dto.request.RoleAssignmentRequest;
import com.rms.recruitment.enums.RoleType;
import com.rms.recruitment.models.Roles;
import com.rms.recruitment.repositories.RoleRepository;
import com.rms.recruitment.services.RoleAssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleAssignmentServiceImpl implements RoleAssignmentService {

    private final RoleRepository roleRepository;

    public RoleAssignmentServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Roles assignRole(RoleAssignmentRequest request) {
        // Check if account exists first
        if (request.getAccountId() == null) {
            throw new RuntimeException("Account ID is required");
        }

        // Check if role already exists for this account
        Optional<Roles> existingRole = roleRepository.findByAccountId(request.getAccountId());
        if (existingRole.isPresent()) {
            throw new RuntimeException("Account already has a role assigned");
        }

        // Create new role assignment
        Roles role = Roles.builder()
                .accountId(request.getAccountId())
                .roleType(request.getRoleType())
                .name(request.getRoleType().getCode())
                .description(request.getDescription() != null ? request.getDescription()
                        : request.getRoleType().getDescription())
                .build();

        try {
            return roleRepository.save(role);
        } catch (Exception e) {
            throw new RuntimeException("Failed to assign role. Account ID " + request.getAccountId()
                    + " may not exist in database. Error: " + e.getMessage());
        }
    }

    @Override
    public Roles updateRole(Integer accountId, RoleType newRoleType, String description) {
        Roles existingRole = roleRepository.findByAccountId(accountId)
                .orElseThrow(() -> new RuntimeException("No role found for account ID: " + accountId));

        existingRole.setRoleType(newRoleType);
        existingRole.setName(newRoleType.getCode());
        if (description != null) {
            existingRole.setDescription(description);
        }

        return roleRepository.save(existingRole);
    }

    @Override
    public void removeRole(Integer accountId) {
        Roles role = roleRepository.findByAccountId(accountId)
                .orElseThrow(() -> new RuntimeException("No role found for account ID: " + accountId));

        roleRepository.delete(role);
    }

    @Override
    public Optional<Roles> getRoleByAccountId(Integer accountId) {
        return roleRepository.findByAccountId(accountId);
    }

    @Override
    public List<Roles> getRolesByType(RoleType roleType) {
        return roleRepository.findByRoleType(roleType);
    }

    @Override
    public List<Roles> getAllRoles() {
        return roleRepository.findAll();
    }
}
