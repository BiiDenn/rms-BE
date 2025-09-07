package com.rms.recruitment.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "role_permission", uniqueConstraints = @UniqueConstraint(columnNames = { "roleId", "permissionId" }))
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rolePermissionId")
    private Integer rolePermissionId;

    @Column(name = "roleId", nullable = false)
    private Integer roleId;

    @Column(name = "permissionId", nullable = false)
    private Integer permissionId;

    @Column(name = "effectiveDateTime")
    private LocalDateTime effectiveDateTime;

    @Column(name = "expiredDateTime")
    private LocalDateTime expiredDateTime;

    @Column(name = "duration")
    private Integer duration;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "roleId", insertable = false, updatable = false)
    private Roles role;

    @ManyToOne
    @JoinColumn(name = "permissionId", referencedColumnName = "permissionId", insertable = false, updatable = false)
    private Permissions permission;

    // Constructors
    public RolePermission() {
    }

    public RolePermission(Integer rolePermissionId, Integer roleId, Integer permissionId,
            LocalDateTime effectiveDateTime, LocalDateTime expiredDateTime, Integer duration, String description,
            Roles role, Permissions permission) {
        this.rolePermissionId = rolePermissionId;
        this.roleId = roleId;
        this.permissionId = permissionId;
        this.effectiveDateTime = effectiveDateTime;
        this.expiredDateTime = expiredDateTime;
        this.duration = duration;
        this.description = description;
        this.role = role;
        this.permission = permission;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer rolePermissionId;
        private Integer roleId;
        private Integer permissionId;
        private LocalDateTime effectiveDateTime;
        private LocalDateTime expiredDateTime;
        private Integer duration;
        private String description;
        private Roles role;
        private Permissions permission;

        public Builder rolePermissionId(Integer rolePermissionId) {
            this.rolePermissionId = rolePermissionId;
            return this;
        }

        public Builder roleId(Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        public Builder permissionId(Integer permissionId) {
            this.permissionId = permissionId;
            return this;
        }

        public Builder effectiveDateTime(LocalDateTime effectiveDateTime) {
            this.effectiveDateTime = effectiveDateTime;
            return this;
        }

        public Builder expiredDateTime(LocalDateTime expiredDateTime) {
            this.expiredDateTime = expiredDateTime;
            return this;
        }

        public Builder duration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder role(Roles role) {
            this.role = role;
            return this;
        }

        public Builder permission(Permissions permission) {
            this.permission = permission;
            return this;
        }

        public RolePermission build() {
            return new RolePermission(rolePermissionId, roleId, permissionId, effectiveDateTime, expiredDateTime,
                    duration, description, role, permission);
        }
    }

    // Getters and Setters
    public Integer getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public LocalDateTime getEffectiveDateTime() {
        return effectiveDateTime;
    }

    public void setEffectiveDateTime(LocalDateTime effectiveDateTime) {
        this.effectiveDateTime = effectiveDateTime;
    }

    public LocalDateTime getExpiredDateTime() {
        return expiredDateTime;
    }

    public void setExpiredDateTime(LocalDateTime expiredDateTime) {
        this.expiredDateTime = expiredDateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Permissions getPermission() {
        return permission;
    }

    public void setPermission(Permissions permission) {
        this.permission = permission;
    }
}
