package com.rms.recruitment.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "permissions")
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permissionId")
    private Integer permissionId;

    @Column(name = "permissionType", length = 100)
    private String permissionType;

    @Column(name = "accessLevel", length = 100)
    private String accessLevel;

    @Column(name = "name", length = 255)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "permission")
    private List<RolePermission> rolePermissions;

    // Constructors
    public Permissions() {
    }

    public Permissions(Integer permissionId, String permissionType, String accessLevel, String name, String description,
            List<RolePermission> rolePermissions) {
        this.permissionId = permissionId;
        this.permissionType = permissionType;
        this.accessLevel = accessLevel;
        this.name = name;
        this.description = description;
        this.rolePermissions = rolePermissions;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer permissionId;
        private String permissionType;
        private String accessLevel;
        private String name;
        private String description;
        private List<RolePermission> rolePermissions;

        public Builder permissionId(Integer permissionId) {
            this.permissionId = permissionId;
            return this;
        }

        public Builder permissionType(String permissionType) {
            this.permissionType = permissionType;
            return this;
        }

        public Builder accessLevel(String accessLevel) {
            this.accessLevel = accessLevel;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder rolePermissions(List<RolePermission> rolePermissions) {
            this.rolePermissions = rolePermissions;
            return this;
        }

        public Permissions build() {
            return new Permissions(permissionId, permissionType, accessLevel, name, description, rolePermissions);
        }
    }

    // Getters and Setters
    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}