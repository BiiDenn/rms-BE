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

    @Column(name = "status", length = 100)
    private String status;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "candId")
    private Integer candId;

    @Column(name = "candProcessName", length = 255)
    private String candProcessName;

    // Link to screening record
    @Column(name = "screeningId")
    private Integer screeningId;

    @ManyToOne
    @JoinColumn(name = "screeningId", referencedColumnName = "screeningId", insertable = false, updatable = false)
    private Screening screening;

    @Column(name = "locationId")
    private Integer locationId;

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId", insertable = false, updatable = false)
    private Locations location;

    @Column(name = "phoneEvalId")
    private Integer phoneEvalId;

    @ManyToOne
    @JoinColumn(name = "phoneEvalId", referencedColumnName = "phoneEvalId", insertable = false, updatable = false)
    private PhoneItvEval phoneItvEval;

    @Column(name = "offerId")
    private Integer offerId;

    @ManyToOne
    @JoinColumn(name = "offerId", referencedColumnName = "offerId", insertable = false, updatable = false)
    private Offers offer;

    @Column(name = "recruitProcessId")
    private Integer recruitProcessId;

    @ManyToOne
    @JoinColumn(name = "recruitProcessId", referencedColumnName = "recruitProcessId", insertable = false, updatable = false)
    private RecruitmentProcess recruitmentProcess;

    @Column(name = "url", length = 512)
    private String url;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "interviewerId")
    private Integer interviewerId;

    @ManyToOne
    @JoinColumn(name = "interviewerId", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employees interviewer;

    @Lob
    @Column(name = "note")
    private String note;
}