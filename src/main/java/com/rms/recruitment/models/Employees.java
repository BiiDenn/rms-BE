package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private Integer employeeId;

    @Column(name = "firstName", length = 100)
    private String firstName;

    @Column(name = "lastName", length = 100)
    private String lastName;

    @Lob
    @Column(name = "note")
    private String note;

    @Column(name = "divisionId")
    private Integer divisionId;

    @ManyToOne
    @JoinColumn(name = "divisionId", referencedColumnName = "divisionId", insertable = false, updatable = false)
    private Divisions division;

    @OneToOne(mappedBy = "employee")
    private Accounts account;
}