package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "minExpectedSalary", precision = 15, scale = 2)
    private BigDecimal minExpectedSalary;

    @Column(name = "maxExpectedSalary", precision = 15, scale = 2)
    private BigDecimal maxExpectedSalary;

    @Column(name = "employeeId")
    private Integer employeeId;

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employees employee;

    @OneToMany(mappedBy = "phoneItvEval")
    private List<CandidateProcess> candidateProcesses;
}