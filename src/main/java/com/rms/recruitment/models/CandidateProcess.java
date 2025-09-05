package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate_process")
public class CandidateProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candProcessId")
    private Integer candProcessId;

    @Column(name = "processDate")
    private LocalDate processDate;

    @Column(name = "result", length = 100)
    private String status;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "candProcessTypeId")
    private Integer candProcessTypeId;

    @Column(name = "candId")
    private Integer candId;

    @Column(name = "locationId")
    private Integer locationId;

    @Column(name = "phoneEvalId")
    private Integer phoneEvalId;

    @Column(name = "offerId")
    private Integer offerId;

    @Column(name = "recruitProcessId")
    private Integer recruitProcessId;

    @ManyToOne
    @JoinColumn(name = "candProcessTypeId", referencedColumnName = "MasterDataID", insertable = false, updatable = false)
    private MasterData candProcessType;

    @ManyToOne
    @JoinColumn(name = "candId", referencedColumnName = "candId", insertable = false, updatable = false)
    private Candidates candidate;

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId", insertable = false, updatable = false)
    private Locations location;

    @ManyToOne
    @JoinColumn(name = "phoneEvalId", referencedColumnName = "phoneEvalId", insertable = false, updatable = false)
    private PhoneItvEval phoneItvEval;

    @ManyToOne
    @JoinColumn(name = "offerId", referencedColumnName = "offerId", insertable = false, updatable = false)
    private Offers offer;

    @ManyToOne
    @JoinColumn(name = "recruitProcessId", referencedColumnName = "recruitProcessId", insertable = false, updatable = false)
    private RecruitmentProcess recruitmentProcess;
}