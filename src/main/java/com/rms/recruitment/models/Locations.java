package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations")
public class Locations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locationId")
    private Integer locationId;

    @Column(name = "zipcode", length = 20)
    private String zipcode;

    @Column(name = "locationName", length = 255)
    private String locationName;

    @Column(name = "street", length = 255)
    private String street;

    @Column(name = "createdDate")
    private LocalDate createdDate;

    @OneToMany(mappedBy = "location")
    private List<DivisionLocation> divisionLocations;

    @OneToMany(mappedBy = "location")
    private List<CandidateProcess> candidateProcesses;
}