package com.rms.recruitment.controllers;

import com.rms.recruitment.dto.request.RoleAssignmentRequest;
import com.rms.recruitment.enums.PermissionType;
import com.rms.recruitment.enums.RoleType;
import com.rms.recruitment.models.Roles;
import com.rms.recruitment.services.RoleAssignmentService;
import com.rms.recruitment.services.RolePermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Role Management", description = "Role and permission management APIs")
public class RoleController {

    private final RolePermissionService rolePermissionService;
    private final RoleAssignmentService roleAssignmentService;

    public RoleController(RolePermissionService rolePermissionService, RoleAssignmentService roleAssignmentService) {
        this.rolePermissionService = rolePermissionService;
        this.roleAssignmentService = roleAssignmentService;
    }

    @GetMapping
    @Operation(summary = "Get all roles", description = "Get list of all available roles")
    @PreAuthorize("hasAuthority('MANAGE_PERMISSIONS')")
    public ResponseEntity<List<Map<String, String>>> getAllRoles() {
        List<Map<String, String>> roles = Arrays.stream(RoleType.values())
                .map(role -> Map.of(
                        "code", role.getCode(),
                        "description", role.getDescription()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{roleCode}/permissions")
    @Operation(summary = "Get role permissions", description = "Get permissions for a specific role")
    @PreAuthorize("hasAuthority('MANAGE_PERMISSIONS')")
    public ResponseEntity<Set<String>> getRolePermissions(@PathVariable String roleCode) {
        try {
            RoleType role = RoleType.fromCode(roleCode);
            Set<String> permissions = rolePermissionService.getPermissionCodesForRole(role);
            return ResponseEntity.ok(permissions);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/interview-roles")
    @Operation(summary = "Get interview roles", description = "Get roles that can conduct interviews")
    public ResponseEntity<List<Map<String, String>>> getInterviewRoles() {
        List<RoleType> interviewRoles = rolePermissionService.getRolesWithInterviewPermission();
        List<Map<String, String>> roles = interviewRoles.stream()
                .map(role -> Map.of(
                        "code", role.getCode(),
                        "description", role.getDescription()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/permissions")
    @Operation(summary = "Get all permissions", description = "Get list of all available permissions")
    @PreAuthorize("hasAuthority('MANAGE_PERMISSIONS')")
    public ResponseEntity<List<Map<String, String>>> getAllPermissions() {
        List<Map<String, String>> permissions = Arrays.stream(PermissionType.values())
                .map(permission -> Map.of(
                        "code", permission.getCode(),
                        "description", permission.getDescription()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(permissions);
    }

    @PostMapping("/assign")
    @Operation(summary = "Assign role to account", description = "Assign a role to an account")
    @PreAuthorize("hasAuthority('MANAGE_PERMISSIONS')")
    public ResponseEntity<Map<String, Object>> assignRole(@Valid @RequestBody RoleAssignmentRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Roles role = roleAssignmentService.assignRole(request);
            response.put("success", true);
            response.put("message", "Role assigned successfully");
            response.put("role", role);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", "Failed to assign role: " + e.getMessage());
            response.put("error", e.getMessage());
            response.put("accountId", request.getAccountId());
            response.put("roleType", request.getRoleType());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{accountId}")
    @Operation(summary = "Update account role", description = "Update role for an account")
    @PreAuthorize("hasAuthority('MANAGE_PERMISSIONS')")
    public ResponseEntity<Roles> updateRole(@PathVariable Integer accountId,
            @RequestParam RoleType roleType,
            @RequestParam(required = false) String description) {
        try {
            Roles role = roleAssignmentService.updateRole(accountId, roleType, description);
            return ResponseEntity.ok(role);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{accountId}")
    @Operation(summary = "Remove account role", description = "Remove role from an account")
    @PreAuthorize("hasAuthority('MANAGE_PERMISSIONS')")
    public ResponseEntity<Void> removeRole(@PathVariable Integer accountId) {
        try {
            roleAssignmentService.removeRole(accountId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/account/{accountId}")
    @Operation(summary = "Get account role", description = "Get role assigned to an account")
    @PreAuthorize("hasAuthority('MANAGE_PERMISSIONS')")
    public ResponseEntity<Roles> getAccountRole(@PathVariable Integer accountId) {
        Optional<Roles> role = roleAssignmentService.getRoleByAccountId(accountId);
        return role.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{roleType}")
    @Operation(summary = "Get roles by type", description = "Get all accounts with specific role type")
    @PreAuthorize("hasAuthority('MANAGE_PERMISSIONS')")
    public ResponseEntity<List<Roles>> getRolesByType(@PathVariable RoleType roleType) {
        List<Roles> roles = roleAssignmentService.getRolesByType(roleType);
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all roles from database", description = "Get all roles stored in database")
    @PreAuthorize("hasAuthority('MANAGE_PERMISSIONS')")
    public ResponseEntity<List<Roles>> getAllRolesFromDatabase() {
        List<Roles> roles = roleAssignmentService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
}
