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
@Table(name = "divisions")
public class Divisions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "divisionId")
    private Integer divisionId;

    @Column(name = "divisionName", nullable = false, length = 255, unique = true)
    private String divisionName;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "division")
    private List<DivisionLocation> divisionLocations;
}