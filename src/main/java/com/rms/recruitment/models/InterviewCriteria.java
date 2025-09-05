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
@Table(name = "interview_criteria")
public class InterviewCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itvCriteriaId")
    private Integer itvCriteriaId;

    @Column(name = "criteriaSkillType", length = 100)
    private String criteriaSkillType;

    @Column(name = "criteriaName", length = 255)
    private String criteriaName;

    @Column(name = "minScore")
    private Integer minScore;

    @Column(name = "maxScore")
    private Integer maxScore;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "interviewCriteria")
    private List<InterviewDetails> interviewDetails;
}