package com.rms.recruitment.dto;

import java.time.LocalDate;

public class InterviewOverviewResponseCandidate {
    private String fullName;
    private LocalDate dateOfBirth;
    private String maritalStatus;
    private LocalDate expectedOnboardDate;
    private String jobTitle;
    private String department;

    // Default constructor
    public InterviewOverviewResponseCandidate() {
    }

    // Constructor with all fields
    public InterviewOverviewResponseCandidate(String fullName, LocalDate dateOfBirth, String maritalStatus,
            LocalDate expectedOnboardDate, String jobTitle, String department) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.maritalStatus = maritalStatus;
        this.expectedOnboardDate = expectedOnboardDate;
        this.jobTitle = jobTitle;
        this.department = department;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public LocalDate getExpectedOnboardDate() {
        return expectedOnboardDate;
    }

    public void setExpectedOnboardDate(LocalDate expectedOnboardDate) {
        this.expectedOnboardDate = expectedOnboardDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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
        private String fullName;
        private LocalDate dateOfBirth;
        private String maritalStatus;
        private LocalDate expectedOnboardDate;
        private String jobTitle;
        private String department;

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder maritalStatus(String maritalStatus) {
            this.maritalStatus = maritalStatus;
            return this;
        }

        public Builder expectedOnboardDate(LocalDate expectedOnboardDate) {
            this.expectedOnboardDate = expectedOnboardDate;
            return this;
        }

        public Builder jobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public InterviewOverviewResponseCandidate build() {
            return new InterviewOverviewResponseCandidate(fullName, dateOfBirth, maritalStatus,
                    expectedOnboardDate, jobTitle, department);
        }
    }
}
