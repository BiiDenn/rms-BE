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
@Table(name = "recruitments")
public class Recruitments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitId")
    private Integer recruitId;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "durationInUse")
    private Integer durationInUse;

    @Column(name = "expandDays")
    private Integer expandDays;

    @Column(name = "minSalary", precision = 15, scale = 2)
    private BigDecimal minSalary;

    @Column(name = "maxSalary", precision = 15, scale = 2)
    private BigDecimal maxSalary;

    @Column(name = "quantity")
    private Integer quantity;

    @Lob
    @Column(name = "purpose")
    private String purpose;

    @Column(name = "currentWorkforce")
    private Integer currentWorkforce;

    @Lob
    @Column(name = "requirements")
    private String requirements;

    @Column(name = "divisionId")
    private Integer divisionId;

    @Column(name = "employeeId")
    private Integer employeeId;

     @ManyToOne
     @JoinColumn(name = "divisionId", referencedColumnName = "divisionId", insertable = false, updatable = false)
     private Divisions division;

     @ManyToOne
     @JoinColumn(name = "employeeId", referencedColumnName = "employeeId", insertable = false, updatable = false)

     private Employees employee;
     @OneToMany(mappedBy = "recruitment")
     private List<RecruitmentHistory> recruitmentHistories;
}
