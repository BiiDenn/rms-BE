package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jobtitles")
public class JobTitles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobTitleId")
    private Integer jobTitleId;

    @Column(name = "level", length = 100)
    private String level;

    @Column(name = "position", length = 100)
    private String position;

    @Column(name = "name", length = 255)
    private String name;

    @Lob
    @Column(name = "jobDescription")
    private String jobDescription;

    @Column(name = "recruitId")
    private Integer recruitId;

    @ManyToOne
    @JoinColumn(name = "recruitId", referencedColumnName = "recruitId", insertable = false, updatable = false)
    private Recruitments recruitment;

    @OneToMany(mappedBy = "jobTitle")
    private List<Candidates> candidates;

    public Integer getJobTitleId() {
        return jobTitleId;
    }

    public void setJobTitleId(Integer jobTitleId) {
        this.jobTitleId = jobTitleId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Integer getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
    }

    public Recruitments getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitments recruitment) {
        this.recruitment = recruitment;
    }

    public List<Candidates> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidates> candidates) {
        this.candidates = candidates;
    }
}