package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "offers")
public class Offers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offerId")
    private Integer offerId;

    @Column(name = "offerPosition", length = 255)
    private String offerPosition;

    @Column(name = "basicSalary", precision = 15, scale = 2)
    private BigDecimal basicSalary;

    @Column(name = "allowances", precision = 15, scale = 2)
    private BigDecimal allowances;

    @Column(name = "bonuses", precision = 15, scale = 2)
    private BigDecimal bonuses;

    @Column(name = "totalFormalIncome", precision = 15, scale = 2)
    private BigDecimal totalFormalIncome;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "onboardDate")
    private LocalDate onboardDate;

    @Column(name = "totalProbationaryIncome", precision = 15, scale = 2)
    private BigDecimal totalProbationaryIncome;

    // Link to job title for which this offer is made
    @Column(name = "jobTitleId")
    private Integer jobTitleId;

    @ManyToOne
    @JoinColumn(name = "jobTitleId", referencedColumnName = "jobTitleId", insertable = false, updatable = false)
    private JobTitles jobTitle;

    @OneToMany(mappedBy = "offer")
    private List<CandidateProcess> candidateProcesses;
}