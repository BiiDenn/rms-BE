package com.rms.recruitment.dto;

import java.time.LocalDate;
import java.util.List;

public class ProgressStepDTO {
    private Integer stepId;
    private String stepName;
    private LocalDate stepDate;
    private String executor;
    private String status; // COMPLETED, IN_PROGRESS, PASSED, FAILED, CANCELLED
    private List<String> actions;
    private String description;

    // No-args constructor
    public ProgressStepDTO() {
    }

    // All-args constructor
    public ProgressStepDTO(Integer stepId, String stepName, LocalDate stepDate, String executor, String status,
            List<String> actions, String description) {
        this.stepId = stepId;
        this.stepName = stepName;
        this.stepDate = stepDate;
        this.executor = executor;
        this.status = status;
        this.actions = actions;
        this.description = description;
    }

    // Getters and Setters
    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public LocalDate getStepDate() {
        return stepDate;
    }

    public void setStepDate(LocalDate stepDate) {
        this.stepDate = stepDate;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer stepId;
        private String stepName;
        private LocalDate stepDate;
        private String executor;
        private String status;
        private List<String> actions;
        private String description;

        public Builder stepId(Integer stepId) {
            this.stepId = stepId;
            return this;
        }

        public Builder stepName(String stepName) {
            this.stepName = stepName;
            return this;
        }

        public Builder stepDate(LocalDate stepDate) {
            this.stepDate = stepDate;
            return this;
        }

        public Builder executor(String executor) {
            this.executor = executor;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder actions(List<String> actions) {
            this.actions = actions;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public ProgressStepDTO build() {
            return new ProgressStepDTO(stepId, stepName, stepDate, executor, status, actions, description);
        }
    }
}
