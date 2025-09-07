package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "screening")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screeningId")
    private Integer screeningId;

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

    @Column(name = "minExpectedSalary", precision = 15, scale = 2)
    private BigDecimal minExpectedSalary;

    @Column(name = "maxExpectedSalary", precision = 15, scale = 2)
    private BigDecimal maxExpectedSalary;

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

    @Column(name = "candId")
    private Integer candId;

    @Column(name = "employeeId")
    private Integer employeeId;

    @ManyToOne
    @JoinColumn(name = "candId", referencedColumnName = "candId", insertable = false, updatable = false)
    private Candidates candidate;

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employees employee;
}
