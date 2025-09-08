package com.rms.recruitment.models;

import com.rms.recruitment.enums.RoleType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "roleType", nullable = false)
    private RoleType roleType;

    @Column(name = "name", length = 255)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "accountId")
    private Integer accountId;

    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "accountId", insertable = false, updatable = false)
    private Accounts account;
    @OneToMany(mappedBy = "role")
    private List<RolePermission> rolePermissions;

    // Constructors
    public Roles() {
    }

    public Roles(Integer roleId, RoleType roleType, String name, String description, Integer accountId,
            Accounts account, List<RolePermission> rolePermissions) {
        this.roleId = roleId;
        this.roleType = roleType;
        this.name = name;
        this.description = description;
        this.accountId = accountId;
        this.account = account;
        this.rolePermissions = rolePermissions;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer roleId;
        private RoleType roleType;
        private String name;
        private String description;
        private Integer accountId;
        private Accounts account;
        private List<RolePermission> rolePermissions;

        public Builder roleId(Integer roleId) {
            this.roleId = roleId;
            return this;
        }

        public Builder roleType(RoleType roleType) {
            this.roleType = roleType;
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

        public Builder accountId(Integer accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder account(Accounts account) {
            this.account = account;
            return this;
        }

        public Builder rolePermissions(List<RolePermission> rolePermissions) {
            this.rolePermissions = rolePermissions;
            return this;
        }

        public Roles build() {
            return new Roles(roleId, roleType, name, description, accountId, account, rolePermissions);
        }
    }

    // Getters and Setters
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
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

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}