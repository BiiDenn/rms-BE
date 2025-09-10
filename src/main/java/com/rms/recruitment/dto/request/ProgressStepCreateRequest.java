package com.rms.recruitment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class ProgressStepCreateRequest {
    @NotBlank(message = "Tên bước không được để trống")
    private String stepName;

    private String description;

    @NotNull(message = "ID người thực hiện không được để trống")
    private Integer executorId;

    @NotNull(message = "ID đơn ứng tuyển không được để trống")
    private Integer applicationId;

    private List<String> attachments;

    // No-args constructor
    public ProgressStepCreateRequest() {
    }

    // All-args constructor
    public ProgressStepCreateRequest(String stepName, String description, Integer executorId, Integer applicationId,
            List<String> attachments) {
        this.stepName = stepName;
        this.description = description;
        this.executorId = executorId;
        this.applicationId = applicationId;
        this.attachments = attachments;
    }

    // Getters and Setters
    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Integer executorId) {
        this.executorId = executorId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String stepName;
        private String description;
        private Integer executorId;
        private Integer applicationId;
        private List<String> attachments;

        public Builder stepName(String stepName) {
            this.stepName = stepName;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder executorId(Integer executorId) {
            this.executorId = executorId;
            return this;
        }

        public Builder applicationId(Integer applicationId) {
            this.applicationId = applicationId;
            return this;
        }

        public Builder attachments(List<String> attachments) {
            this.attachments = attachments;
            return this;
        }

        public ProgressStepCreateRequest build() {
            return new ProgressStepCreateRequest(stepName, description, executorId, applicationId, attachments);
        }
    }
}
