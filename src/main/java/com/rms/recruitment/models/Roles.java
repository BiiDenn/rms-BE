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
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Integer roleId;

    @Column(name = "name", length = 255)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "accountId")
    private Integer accountId;

     @ManyToOne
     @JoinColumn(name = "accountId", referencedColumnName = "accountId", insertable = false, updatable = false)
     private Accounts account;
     @OneToMany(mappedBy = "role")
     private List<RolePermission> rolePermissions;
}