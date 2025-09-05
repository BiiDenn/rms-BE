package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "interviewdetails")
public class InterviewDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itvDetailId")
    private Integer itvDetailId;

    @Column(name = "score")
    private Integer score;

    @Lob
    @Column(name = "comment")
    private String comment;

    @Column(name = "itvEvalId")
    private Integer itvEvalId;

    @Column(name = "itvCriteriaId")
    private Integer itvCriteriaId;

    @ManyToOne
    @JoinColumn(name = "itvEvalId", referencedColumnName = "itvEvalId", insertable = false, updatable = false)
    private InterviewEvaluation interviewEvaluation;

    @ManyToOne
    @JoinColumn(name = "itvCriteriaId", referencedColumnName = "itvCriteriaId", insertable = false, updatable = false)
    private InterviewCriteria interviewCriteria;
}