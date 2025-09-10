package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.math.BigDecimal;
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
    @Column(name = "workingExps")
    private String workingExps;

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

    // Moved experience fields to a dedicated table PhoneItvExperience

    @Column(name = "max_expected_salary", precision = 15, scale = 2)
    private BigDecimal maxExpectedSalary;

    @Column(name = "min_expected_salary", precision = 15, scale = 2)
    private BigDecimal minExpectedSalary;

    @Lob
    @Column(name = "note")
    private String note;

    @Column(name = "employeeId")
    private Integer employeeId;

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employees employee;

    @Column(name = "cand_process_id")
    private Integer candProcessId;

    @ManyToOne
    @JoinColumn(name = "cand_process_id", referencedColumnName = "candProcessId", insertable = false, updatable = false)
    private CandidateProcess candidateProcess;

    @OneToMany(mappedBy = "phoneItvEval")
    private List<PhoneItvExperience> experiences;
}