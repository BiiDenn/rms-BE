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