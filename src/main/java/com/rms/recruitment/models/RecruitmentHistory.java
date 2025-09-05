package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recruitmenthistory")
public class RecruitmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitmentHistoryId")
    private Integer recruitmentHistoryId;

    @Column(name = "recruitId", nullable = false)
    private Integer recruitId;

    @Column(name = "changedBy")
    private Integer changedBy;

    @Column(name = "changeDate")
    private LocalDateTime changeDate;

    @Column(name = "changeType", length = 50)
    @Builder.Default
    private String changeType = "UPDATE";

    @Lob
    @Column(name = "oldValues", nullable = false)
    private String oldValues;

    @Lob
    @Column(name = "newValues")
    private String newValues;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "recruitId", referencedColumnName = "recruitId", insertable = false, updatable = false)
    private Recruitments recruitment;

    @ManyToOne
    @JoinColumn(name = "changedBy", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employees employee;

    @PrePersist
    protected void onCreate() {
        if (changeDate == null) {
            changeDate = LocalDateTime.now();
        }
    }
}
