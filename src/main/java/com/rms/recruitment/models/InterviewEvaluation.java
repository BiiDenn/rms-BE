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

    @Column(name = "attachment_id")
    private Integer attachmentId;

    @ManyToOne
    @JoinColumn(name = "attachment_id", referencedColumnName = "AttachmentID", insertable = false, updatable = false)
    private Attachments attachment;

    public Integer getItvEvalId() {
        return itvEvalId;
    }

    public void setItvEvalId(Integer itvEvalId) {
        this.itvEvalId = itvEvalId;
    }

    public String getCandLevel() {
        return candLevel;
    }

    public void setCandLevel(String candLevel) {
        this.candLevel = candLevel;
    }

    public String getHrComment() {
        return hrComment;
    }

    public void setHrComment(String hrComment) {
        this.hrComment = hrComment;
    }

    public String getRecommendationResult() {
        return recommendationResult;
    }

    public void setRecommendationResult(String recommendationResult) {
        this.recommendationResult = recommendationResult;
    }

    public String getRecommendationNote() {
        return recommendationNote;
    }

    public void setRecommendationNote(String recommendationNote) {
        this.recommendationNote = recommendationNote;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public List<InterviewDetails> getInterviewDetails() {
        return interviewDetails;
    }

    public void setInterviewDetails(List<InterviewDetails> interviewDetails) {
        this.interviewDetails = interviewDetails;
    }
}