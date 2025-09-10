package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "locations")
public class Locations {

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public List<DivisionLocation> getDivisionLocations() {
        return divisionLocations;
    }

    public void setDivisionLocations(List<DivisionLocation> divisionLocations) {
        this.divisionLocations = divisionLocations;
    }

    public List<CandidateProcess> getCandidateProcesses() {
        return candidateProcesses;
    }

    public void setCandidateProcesses(List<CandidateProcess> candidateProcesses) {
        this.candidateProcesses = candidateProcesses;
    }

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