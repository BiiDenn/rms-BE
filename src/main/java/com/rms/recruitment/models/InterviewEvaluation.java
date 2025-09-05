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
@Table(name = "interviewevaluation")
public class InterviewEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itvEvalId")
    private Integer itvEvalId;

    @Column(name = "candLevel", length = 100)
    private String candLevel;

    @Lob
    @Column(name = "hrComment")
    private String hrComment;

    @Column(name = "recommendationResult", length = 100)
    private String recommendationResult;

    @Lob
    @Column(name = "recommendationNote")
    private String recommendationNote;

    @Column(name = "employeeId")
    private Integer employeeId;

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employees employee;

    @OneToMany(mappedBy = "interviewEvaluation")
    private List<InterviewDetails> interviewDetails;
}