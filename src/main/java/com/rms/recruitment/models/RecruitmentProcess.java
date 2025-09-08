package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}