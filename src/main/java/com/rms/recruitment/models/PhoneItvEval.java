package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phone_itv_eval")
public class PhoneItvEval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phoneEvalId")
    private Integer phoneEvalId;

    @Lob
    @Column(name = "leaveReason")
    private String leaveReason;

    @Lob
    @Column(name = "objective")
    private String objective;

    @Column(name = "positionApplied", length = 255)
    private String positionApplied;

    @Column(name = "availableDate")
    private LocalDate availableDate;

    @Lob
    @Column(name = "other")
    private String other;

    @Column(name = "experienceYear")
    private Integer experienceYear;

    @Column(name = "domain", length = 255)
    private String domain;

    @Column(name = "technologyUsed", length = 255)
    private String technologyUsed;

    @Column(name = "supportTools", length = 255)
    private String supportTools;

    @Lob
    @Column(name = "note")
    private String note;

    @Column(name = "employeeId")
    private Integer employeeId;

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employees employee;

    @OneToMany(mappedBy = "phoneItvEval")
    private List<PhoneItvEvalDetail> phoneItvEvalDetails;

    @OneToMany(mappedBy = "phoneItvEval")
    private List<CandidateProcess> candidateProcesses;
}