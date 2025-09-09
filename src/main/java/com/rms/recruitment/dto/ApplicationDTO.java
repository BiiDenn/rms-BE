package com.rms.recruitment.dto;

import java.util.List;

public class ApplicationDTO {
    private Integer applicationId;
    private String title;
    private String code;
    private String department;
    private String status;
    private Integer daysRemaining;
    private List<ProgressStepDTO> progressSteps;

    // No-args constructor
    public ApplicationDTO() {
    }

    // All-args constructor
    public ApplicationDTO(Integer applicationId, String title, String code, String department, String status,
            Integer daysRemaining, List<ProgressStepDTO> progressSteps) {
        this.applicationId = applicationId;
        this.title = title;
        this.code = code;
        this.department = department;
        this.status = status;
        this.daysRemaining = daysRemaining;
        this.progressSteps = progressSteps;
    }

    // Getters and Setters
    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(Integer daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    public List<ProgressStepDTO> getProgressSteps() {
        return progressSteps;
    }

    public void setProgressSteps(List<ProgressStepDTO> progressSteps) {
        this.progressSteps = progressSteps;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer applicationId;
        private String title;
        private String code;
        private String department;
        private String status;
        private Integer daysRemaining;
        private List<ProgressStepDTO> progressSteps;

        public Builder applicationId(Integer applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder daysRemaining(Integer daysRemaining) {
            this.daysRemaining = daysRemaining;
            return this;
        }

        public Builder progressSteps(List<ProgressStepDTO> progressSteps) {
            this.progressSteps = progressSteps;
            return this;
        }

        public ApplicationDTO build() {
            return new ApplicationDTO(applicationId, title, code, department, status, daysRemaining, progressSteps);
        }
    }
}
