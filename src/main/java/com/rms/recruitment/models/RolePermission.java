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
@Table(name = "role_permission", uniqueConstraints = @UniqueConstraint(columnNames = { "roleId", "permissionId" }))
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rolePermissionId")
    private Integer rolePermissionId;

    @Column(name = "roleId", nullable = false)
    private Integer roleId;

    @Column(name = "permissionId", nullable = false)
    private Integer permissionId;

    @Column(name = "effectiveDateTime")
    private LocalDateTime effectiveDateTime;

    @Column(name = "expiredDateTime")
    private LocalDateTime expiredDateTime;

    @Column(name = "duration")
    private Integer duration;

    @Lob
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "roleId", insertable = false, updatable = false)
    private Roles role;

    @ManyToOne
    @JoinColumn(name = "permissionId", referencedColumnName = "permissionId", insertable = false, updatable = false)
    private Permissions permission;
}
