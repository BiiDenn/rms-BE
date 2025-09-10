package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidate_process")
public class CandidateProcess {
    public Integer getCandProcessId() {
        return candProcessId;
    }

    public void setCandProcessId(Integer candProcessId) {
        this.candProcessId = candProcessId;
    }

    public LocalDate getProcessDate() {
        return processDate;
    }

    public void setProcessDate(LocalDate processDate) {
        this.processDate = processDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCandId() {
        return candId;
    }

    public void setCandId(Integer candId) {
        this.candId = candId;
    }

    public String getCandProcessName() {
        return candProcessName;
    }

    public void setCandProcessName(String candProcessName) {
        this.candProcessName = candProcessName;
    }

    public Integer getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Integer screeningId) {
        this.screeningId = screeningId;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Locations getLocation() {
        return location;
    }

    public void setLocation(Locations location) {
        this.location = location;
    }

    public Integer getPhoneEvalId() {
        return phoneEvalId;
    }

    public void setPhoneEvalId(Integer phoneEvalId) {
        this.phoneEvalId = phoneEvalId;
    }

    public PhoneItvEval getPhoneItvEval() {
        return phoneItvEval;
    }

    public void setPhoneItvEval(PhoneItvEval phoneItvEval) {
        this.phoneItvEval = phoneItvEval;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public Offers getOffer() {
        return offer;
    }

    public void setOffer(Offers offer) {
        this.offer = offer;
    }

    public Integer getRecruitProcessId() {
        return recruitProcessId;
    }

    public void setRecruitProcessId(Integer recruitProcessId) {
        this.recruitProcessId = recruitProcessId;
    }

    public RecruitmentProcess getRecruitmentProcess() {
        return recruitmentProcess;
    }

    public void setRecruitmentProcess(RecruitmentProcess recruitmentProcess) {
        this.recruitmentProcess = recruitmentProcess;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(Integer interviewerId) {
        this.interviewerId = interviewerId;
    }

    public Employees getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(Employees interviewer) {
        this.interviewer = interviewer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candProcessId")
    private Integer candProcessId;

    @Column(name = "processDate")
    private LocalDate processDate;

    @Column(name = "status", length = 100)
    private String status;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "candId")
    private Integer candId;

    @Column(name = "candProcessName", length = 255)
    private String candProcessName;

    // Link to screening record
    @Column(name = "screeningId")
    private Integer screeningId;

    @ManyToOne
    @JoinColumn(name = "screeningId", referencedColumnName = "screeningId", insertable = false, updatable = false)
    private Screening screening;

    @Column(name = "locationId")
    private Integer locationId;

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId", insertable = false, updatable = false)
    private Locations location;

    @Column(name = "phoneEvalId")
    private Integer phoneEvalId;

    @ManyToOne
    @JoinColumn(name = "phoneEvalId", referencedColumnName = "phoneEvalId", insertable = false, updatable = false)
    private PhoneItvEval phoneItvEval;

    @Column(name = "offerId")
    private Integer offerId;

    @ManyToOne
    @JoinColumn(name = "offerId", referencedColumnName = "offerId", insertable = false, updatable = false)
    private Offers offer;

    @Column(name = "recruitProcessId")
    private Integer recruitProcessId;

    @ManyToOne
    @JoinColumn(name = "recruitProcessId", referencedColumnName = "recruitProcessId", insertable = false, updatable = false)
    private RecruitmentProcess recruitmentProcess;

    @Column(name = "url", length = 512)
    private String url;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "interviewerId")
    private Integer interviewerId;

    @ManyToOne
    @JoinColumn(name = "interviewerId", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employees interviewer;

    @Lob
    @Column(name = "note")
    private String note;
}