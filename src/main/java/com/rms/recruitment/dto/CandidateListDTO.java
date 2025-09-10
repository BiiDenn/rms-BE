package com.rms.recruitment.dto;

public class CandidateListDTO {
    private Integer candidateId;
    private String candidateCode;
    private String fullName;
    private String recruitmentSource;
    private String recruitmentCode;
    private String progress;
    private String status;

    public CandidateListDTO() {
    }

    public CandidateListDTO(Integer candidateId, String candidateCode, String fullName,
            String recruitmentSource, String recruitmentCode, String progress, String status) {
        this.candidateId = candidateId;
        this.candidateCode = candidateCode;
        this.fullName = fullName;
        this.recruitmentSource = recruitmentSource;
        this.recruitmentCode = recruitmentCode;
        this.progress = progress;
        this.status = status;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateCode() {
        return candidateCode;
    }

    public void setCandidateCode(String candidateCode) {
        this.candidateCode = candidateCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRecruitmentSource() {
        return recruitmentSource;
    }

    public void setRecruitmentSource(String recruitmentSource) {
        this.recruitmentSource = recruitmentSource;
    }

    public String getRecruitmentCode() {
        return recruitmentCode;
    }

    public void setRecruitmentCode(String recruitmentCode) {
        this.recruitmentCode = recruitmentCode;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer candidateId;
        private String candidateCode;
        private String fullName;
        private String recruitmentSource;
        private String recruitmentCode;
        private String progress;
        private String status;

        public Builder candidateId(Integer candidateId) {
            this.candidateId = candidateId;
            return this;
        }

        public Builder candidateCode(String candidateCode) {
            this.candidateCode = candidateCode;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder recruitmentSource(String recruitmentSource) {
            this.recruitmentSource = recruitmentSource;
            return this;
        }

        public Builder recruitmentCode(String recruitmentCode) {
            this.recruitmentCode = recruitmentCode;
            return this;
        }

        public Builder progress(String progress) {
            this.progress = progress;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public CandidateListDTO build() {
            return new CandidateListDTO(candidateId, candidateCode, fullName,
                    recruitmentSource, recruitmentCode, progress, status);
        }
    }
}
