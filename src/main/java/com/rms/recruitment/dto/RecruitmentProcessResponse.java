package com.rms.recruitment.dto;

public class RecruitmentProcessResponse {
    private Integer id; // recruitProcessId
    private String recruitmentTitle;
    private String status;
    private String code;
    private String position;
    private String department;

    // Default constructor
    public RecruitmentProcessResponse() {
    }

    // Constructor with all fields
    public RecruitmentProcessResponse(Integer id, String recruitmentTitle, String status, String code,
            String position, String department) {
        this.id = id;
        this.recruitmentTitle = recruitmentTitle;
        this.status = status;
        this.code = code;
        this.position = position;
        this.department = department;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecruitmentTitle() {
        return recruitmentTitle;
    }

    public void setRecruitmentTitle(String recruitmentTitle) {
        this.recruitmentTitle = recruitmentTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Static method to create builder
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private Integer id;
        private String recruitmentTitle;
        private String status;
        private String code;
        private String position;
        private String department;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder recruitmentTitle(String recruitmentTitle) {
            this.recruitmentTitle = recruitmentTitle;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder position(String position) {
            this.position = position;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public RecruitmentProcessResponse build() {
            return new RecruitmentProcessResponse(id, recruitmentTitle, status, code, position, department);
        }
    }
}
