package com.rms.recruitment.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
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

    // Constructors
    public Divisions() {
    }

    public Divisions(Integer divisionId, String divisionName, String description,
            List<DivisionLocation> divisionLocations) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.description = description;
        this.divisionLocations = divisionLocations;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer divisionId;
        private String divisionName;
        private String description;
        private List<DivisionLocation> divisionLocations;

        public Builder divisionId(Integer divisionId) {
            this.divisionId = divisionId;
            return this;
        }

        public Builder divisionName(String divisionName) {
            this.divisionName = divisionName;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder divisionLocations(List<DivisionLocation> divisionLocations) {
            this.divisionLocations = divisionLocations;
            return this;
        }

        public Divisions build() {
            return new Divisions(divisionId, divisionName, description, divisionLocations);
        }
    }

    // Getters and Setters
    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DivisionLocation> getDivisionLocations() {
        return divisionLocations;
    }

    public void setDivisionLocations(List<DivisionLocation> divisionLocations) {
        this.divisionLocations = divisionLocations;
    }
}