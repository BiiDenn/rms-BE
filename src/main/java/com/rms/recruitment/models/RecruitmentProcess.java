package com.rms.recruitment.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recruitment_process")
public class RecruitmentProcess {
    public Integer getRecruitProcessId() {
        return recruitProcessId;
    }

    public void setRecruitProcessId(Integer recruitProcessId) {
        this.recruitProcessId = recruitProcessId;
    }

    public String getRecruitProcessStatus() {
        return recruitProcessStatus;
    }

    public void setRecruitProcessStatus(String recruitProcessStatus) {
        this.recruitProcessStatus = recruitProcessStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
    }

    public Recruitments getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitments recruitment) {
        this.recruitment = recruitment;
    }

    public List<CandidateProcess> getCandidateProcesses() {
        return candidateProcesses;
    }

    public void setCandidateProcesses(List<CandidateProcess> candidateProcesses) {
        this.candidateProcesses = candidateProcesses;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitProcessId")
    private Integer recruitProcessId;

    @Column(name = "recruitProcessStatus", length = 100)
    private String recruitProcessStatus;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "recruitId")
    private Integer recruitId;

    @ManyToOne
    @JoinColumn(name = "recruitId", referencedColumnName = "recruitId", insertable = false, updatable = false)
    private Recruitments recruitment;

    @OneToMany(mappedBy = "recruitmentProcess")
    private List<CandidateProcess> candidateProcesses;

    // Default constructor
    public RecruitmentProcess() {
    }

    // Constructor with all fields
    public RecruitmentProcess(Integer recruitProcessId, String recruitProcessStatus, LocalDateTime createdAt,
            Integer recruitId, Recruitments recruitment, List<CandidateProcess> candidateProcesses) {
        this.recruitProcessId = recruitProcessId;
        this.recruitProcessStatus = recruitProcessStatus;
        this.createdAt = createdAt;
        this.recruitId = recruitId;
        this.recruitment = recruitment;
        this.candidateProcesses = candidateProcesses;
    }

    // Static method to create builder
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private Integer recruitProcessId;
        private String recruitProcessStatus;
        private LocalDateTime createdAt;
        private Integer recruitId;
        private Recruitments recruitment;
        private List<CandidateProcess> candidateProcesses;

        public Builder recruitProcessId(Integer recruitProcessId) {
            this.recruitProcessId = recruitProcessId;
            return this;
        }

        public Builder recruitProcessStatus(String recruitProcessStatus) {
            this.recruitProcessStatus = recruitProcessStatus;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder recruitId(Integer recruitId) {
            this.recruitId = recruitId;
            return this;
        }

        public Builder recruitment(Recruitments recruitment) {
            this.recruitment = recruitment;
            return this;
        }

        public Builder candidateProcesses(List<CandidateProcess> candidateProcesses) {
            this.candidateProcesses = candidateProcesses;
            return this;
        }

        public RecruitmentProcess build() {
            return new RecruitmentProcess(recruitProcessId, recruitProcessStatus, createdAt,
                    recruitId, recruitment, candidateProcesses);
        }
    }
}