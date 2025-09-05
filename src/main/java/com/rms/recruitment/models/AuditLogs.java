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
@Table(name = "audit_logs")
public class AuditLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logId")
    private Integer logId;

    @Column(name = "tableName", nullable = false, length = 255)
    private String tableName;

    @Column(name = "operation", nullable = false, length = 50)
    private String operation;

    @Column(name = "recordId", nullable = false)
    private Integer recordId;

    @Column(name = "changedBy")
    private Integer changedBy;

    @Column(name = "changeDate")
    private LocalDateTime changeDate;

    @Lob
    @Column(name = "oldData")
    private String oldData;

    @Lob
    @Column(name = "newData")
    private String newData;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "changedBy", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employees changedByEmployee;
}