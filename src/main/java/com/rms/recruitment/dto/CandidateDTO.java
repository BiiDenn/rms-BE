package com.rms.recruitment.dto;

import java.time.LocalDate;
import java.util.List;

public class CandidateDTO {
    private Integer candidateId;
    private String candidateCode;
    private String firstName;
    private String lastName;
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private String mainEmail;
    private String subEmail;
    private String mainPhone;
    private String subPhone;
    private String primaryLanguage;
    private String secondaryLanguage;
    private String recruitmentSource;
    private String referrer;
    private String maritalStatus;
    private String address;
    private String qualifications;
    private List<String> hardSkills;
    private List<String> softSkills;
    private List<ResumeDTO> resumes;
    private String lastUpdatedBy;
    private String lastUpdatedAt;
    private Integer recruitmentId;
    private String recruitmentCode;

    // No-args constructor
    public CandidateDTO() {
    }

    // All-args constructor
    public CandidateDTO(Integer candidateId, String candidateCode, String firstName, String lastName, String fullName,
            LocalDate dateOfBirth, String gender, String nationality, String mainEmail, String subEmail,
            String mainPhone, String subPhone, String primaryLanguage, String secondaryLanguage,
            String recruitmentSource, String referrer, String maritalStatus, String address,
            String qualifications, List<String> hardSkills, List<String> softSkills, List<ResumeDTO> resumes,
            String lastUpdatedBy, String lastUpdatedAt, Integer recruitmentId, String recruitmentCode) {
        this.candidateId = candidateId;
        this.candidateCode = candidateCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.mainEmail = mainEmail;
        this.subEmail = subEmail;
        this.mainPhone = mainPhone;
        this.subPhone = subPhone;
        this.primaryLanguage = primaryLanguage;
        this.secondaryLanguage = secondaryLanguage;
        this.recruitmentSource = recruitmentSource;
        this.referrer = referrer;
        this.maritalStatus = maritalStatus;
        this.address = address;
        this.qualifications = qualifications;
        this.hardSkills = hardSkills;
        this.softSkills = softSkills;
        this.resumes = resumes;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedAt = lastUpdatedAt;
        this.recruitmentId = recruitmentId;
        this.recruitmentCode = recruitmentCode;
    }

    // Getters and Setters
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMainEmail() {
        return mainEmail;
    }

    public void setMainEmail(String mainEmail) {
        this.mainEmail = mainEmail;
    }

    public String getSubEmail() {
        return subEmail;
    }

    public void setSubEmail(String subEmail) {
        this.subEmail = subEmail;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getSubPhone() {
        return subPhone;
    }

    public void setSubPhone(String subPhone) {
        this.subPhone = subPhone;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public String getSecondaryLanguage() {
        return secondaryLanguage;
    }

    public void setSecondaryLanguage(String secondaryLanguage) {
        this.secondaryLanguage = secondaryLanguage;
    }

    public String getRecruitmentSource() {
        return recruitmentSource;
    }

    public void setRecruitmentSource(String recruitmentSource) {
        this.recruitmentSource = recruitmentSource;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public List<String> getHardSkills() {
        return hardSkills;
    }

    public void setHardSkills(List<String> hardSkills) {
        this.hardSkills = hardSkills;
    }

    public List<String> getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(List<String> softSkills) {
        this.softSkills = softSkills;
    }

    public List<ResumeDTO> getResumes() {
        return resumes;
    }

    public void setResumes(List<ResumeDTO> resumes) {
        this.resumes = resumes;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(String lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Integer getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(Integer recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    public String getRecruitmentCode() {
        return recruitmentCode;
    }

    public void setRecruitmentCode(String recruitmentCode) {
        this.recruitmentCode = recruitmentCode;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer candidateId;
        private String candidateCode;
        private String firstName;
        private String lastName;
        private String fullName;
        private LocalDate dateOfBirth;
        private String gender;
        private String nationality;
        private String mainEmail;
        private String subEmail;
        private String mainPhone;
        private String subPhone;
        private String primaryLanguage;
        private String secondaryLanguage;
        private String recruitmentSource;
        private String referrer;
        private String maritalStatus;
        private String address;
        private String qualifications;
        private List<String> hardSkills;
        private List<String> softSkills;
        private List<ResumeDTO> resumes;
        private String lastUpdatedBy;
        private String lastUpdatedAt;
        private Integer recruitmentId;
        private String recruitmentCode;

        public Builder candidateId(Integer candidateId) {
            this.candidateId = candidateId;
            return this;
        }

        public Builder candidateCode(String candidateCode) {
            this.candidateCode = candidateCode;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder nationality(String nationality) {
            this.nationality = nationality;
            return this;
        }

        public Builder mainEmail(String mainEmail) {
            this.mainEmail = mainEmail;
            return this;
        }

        public Builder subEmail(String subEmail) {
            this.subEmail = subEmail;
            return this;
        }

        public Builder mainPhone(String mainPhone) {
            this.mainPhone = mainPhone;
            return this;
        }

        public Builder subPhone(String subPhone) {
            this.subPhone = subPhone;
            return this;
        }

        public Builder primaryLanguage(String primaryLanguage) {
            this.primaryLanguage = primaryLanguage;
            return this;
        }

        public Builder secondaryLanguage(String secondaryLanguage) {
            this.secondaryLanguage = secondaryLanguage;
            return this;
        }

        public Builder recruitmentSource(String recruitmentSource) {
            this.recruitmentSource = recruitmentSource;
            return this;
        }

        public Builder referrer(String referrer) {
            this.referrer = referrer;
            return this;
        }

        public Builder maritalStatus(String maritalStatus) {
            this.maritalStatus = maritalStatus;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder qualifications(String qualifications) {
            this.qualifications = qualifications;
            return this;
        }

        public Builder hardSkills(List<String> hardSkills) {
            this.hardSkills = hardSkills;
            return this;
        }

        public Builder softSkills(List<String> softSkills) {
            this.softSkills = softSkills;
            return this;
        }

        public Builder resumes(List<ResumeDTO> resumes) {
            this.resumes = resumes;
            return this;
        }

        public Builder lastUpdatedBy(String lastUpdatedBy) {
            this.lastUpdatedBy = lastUpdatedBy;
            return this;
        }

        public Builder lastUpdatedAt(String lastUpdatedAt) {
            this.lastUpdatedAt = lastUpdatedAt;
            return this;
        }

        public Builder recruitmentId(Integer recruitmentId) {
            this.recruitmentId = recruitmentId;
            return this;
        }

        public Builder recruitmentCode(String recruitmentCode) {
            this.recruitmentCode = recruitmentCode;
            return this;
        }

        public CandidateDTO build() {
            return new CandidateDTO(candidateId, candidateCode, firstName, lastName, fullName, dateOfBirth, gender,
                    nationality, mainEmail, subEmail, mainPhone, subPhone, primaryLanguage, secondaryLanguage,
                    recruitmentSource, referrer, maritalStatus, address, qualifications, hardSkills, softSkills,
                    resumes, lastUpdatedBy, lastUpdatedAt, recruitmentId, recruitmentCode);
        }
    }
}
