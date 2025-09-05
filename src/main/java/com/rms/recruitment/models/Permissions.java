package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "permissions")
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permissionId")
    private Integer permissionId;

    @Column(name = "permissionType", length = 100)
    private String permissionType;

    @Column(name = "accessLevel", length = 100)
    private String accessLevel;

    @Column(name = "name", length = 255)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "permission")
    private List<RolePermission> rolePermissions;
}