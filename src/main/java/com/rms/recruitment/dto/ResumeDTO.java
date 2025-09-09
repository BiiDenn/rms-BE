package com.rms.recruitment.dto;

public class ResumeDTO {
    private Integer resumeId;
    private String fileName;
    private String fileUrl;
    private String uploadDate;
    private String description;

    // No-args constructor
    public ResumeDTO() {
    }

    // All-args constructor
    public ResumeDTO(Integer resumeId, String fileName, String fileUrl, String uploadDate, String description) {
        this.resumeId = resumeId;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.uploadDate = uploadDate;
        this.description = description;
    }

    // Getters and Setters
    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
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
        private Integer resumeId;
        private String fileName;
        private String fileUrl;
        private String uploadDate;
        private String description;

        public Builder resumeId(Integer resumeId) {
            this.resumeId = resumeId;
            return this;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder fileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
            return this;
        }

        public Builder uploadDate(String uploadDate) {
            this.uploadDate = uploadDate;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public ResumeDTO build() {
            return new ResumeDTO(resumeId, fileName, fileUrl, uploadDate, description);
        }
    }
}
