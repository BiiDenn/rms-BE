package com.rms.recruitment.dto;

import java.util.List;

public class CandidateProgressDTO {
    private Integer candidateId;
    private String candidateName;
    private List<ApplicationDTO> applications;

    // No-args constructor
    public CandidateProgressDTO() {
    }

    // All-args constructor
    public CandidateProgressDTO(Integer candidateId, String candidateName, List<ApplicationDTO> applications) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.applications = applications;
    }

    // Getters and Setters
    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public List<ApplicationDTO> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplicationDTO> applications) {
        this.applications = applications;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer candidateId;
        private String candidateName;
        private List<ApplicationDTO> applications;

        public Builder candidateId(Integer candidateId) {
            this.candidateId = candidateId;
            return this;
        }

        public Builder candidateName(String candidateName) {
            this.candidateName = candidateName;
            return this;
        }

        public Builder applications(List<ApplicationDTO> applications) {
            this.applications = applications;
            return this;
        }

        public CandidateProgressDTO build() {
            return new CandidateProgressDTO(candidateId, candidateName, applications);
        }
    }
}
