package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidates")
public class Candidates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candId")
    private Integer candId;

    @Column(name = "firstName", length = 100)
    private String firstName;

    @Column(name = "lastName", length = 100)
    private String lastName;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "nationality", length = 100)
    private String nationality;

    @Column(name = "mainPhone", length = 50)
    private String mainPhone;

    @Column(name = "subPhone", length = 50)
    private String subPhone;

    @Column(name = "mainEmail", length = 255)
    private String mainEmail;

    @Column(name = "subEmail", length = 255)
    private String subEmail;

    @Column(name = "referredBy", length = 255)
    private String referredBy;

    @Lob
    @Column(name = "note")
    private String note;

    @Column(name = "employeeId")
    private Integer employeeId;

    @Column(name = "jobTitleId")
    private Integer jobTitleId;

    @ManyToOne
    @JoinColumn(name = "gender", referencedColumnName = "MasterDataID", insertable = false, updatable = false)
    private MasterData genderMasterData;

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employees employee;

    @ManyToOne
    @JoinColumn(name = "jobTitleId", referencedColumnName = "jobTitleId", insertable = false, updatable = false)
    private JobTitles jobTitle;
}