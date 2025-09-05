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
}