package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId")
    private Integer accountId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status")
    private Byte status;

    @Column(name = "employeeId", unique = true)
    private Integer employeeId;

    @OneToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employees employee;
}
