package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "division_location", uniqueConstraints = @UniqueConstraint(columnNames = { "divisionId", "locationId" }))
public class DivisionLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "divisionId", nullable = false)
    private Integer divisionId;

    @Column(name = "locationId", nullable = false)
    private Integer locationId;

    @ManyToOne
    @JoinColumn(name = "divisionId", referencedColumnName = "divisionId", insertable = false, updatable = false)
    private Divisions division;

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId", insertable = false, updatable = false)
    private Locations location;
}