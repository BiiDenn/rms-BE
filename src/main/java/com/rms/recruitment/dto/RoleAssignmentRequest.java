package com.rms.recruitment.dto;

import com.rms.recruitment.enums.RoleType;
import jakarta.validation.constraints.NotNull;

public class RoleAssignmentRequest {

    @NotNull(message = "Account ID is required")
    private Integer accountId;

    @NotNull(message = "Role type is required")
    private RoleType roleType;

    private String description;

    // Constructors
    public RoleAssignmentRequest() {
    }

    public RoleAssignmentRequest(Integer accountId, RoleType roleType, String description) {
        this.accountId = accountId;
        this.roleType = roleType;
        this.description = description;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer accountId;
        private RoleType roleType;
        private String description;

        public Builder accountId(Integer accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder roleType(RoleType roleType) {
            this.roleType = roleType;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public RoleAssignmentRequest build() {
            return new RoleAssignmentRequest(accountId, roleType, description);
        }
    }

    // Getters and Setters
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
