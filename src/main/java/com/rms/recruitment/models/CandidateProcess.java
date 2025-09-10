package com.rms.recruitment.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
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

    @ManyToOne
    @JoinColumn(name = "candId", referencedColumnName = "candId", insertable = false, updatable = false)
    private Candidates candidate;

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

    public Candidates getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidates candidate) {
        this.candidate = candidate;
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

    // Default constructor
    public CandidateProcess() {
    }

    // Constructor with all fields
    public CandidateProcess(Integer candProcessId, LocalDate processDate, String status, String description,
            Integer candId, Candidates candidate, String candProcessName, Integer screeningId, Screening screening,
            Integer locationId, Locations location, Integer phoneEvalId, PhoneItvEval phoneItvEval,
            Integer offerId, Offers offer, Integer recruitProcessId, RecruitmentProcess recruitmentProcess,
            String url, String password, Integer interviewerId, Employees interviewer, String note) {
        this.candProcessId = candProcessId;
        this.processDate = processDate;
        this.status = status;
        this.description = description;
        this.candId = candId;
        this.candidate = candidate;
        this.candProcessName = candProcessName;
        this.screeningId = screeningId;
        this.screening = screening;
        this.locationId = locationId;
        this.location = location;
        this.phoneEvalId = phoneEvalId;
        this.phoneItvEval = phoneItvEval;
        this.offerId = offerId;
        this.offer = offer;
        this.recruitProcessId = recruitProcessId;
        this.recruitmentProcess = recruitmentProcess;
        this.url = url;
        this.password = password;
        this.interviewerId = interviewerId;
        this.interviewer = interviewer;
        this.note = note;
    }

    // Static method to create builder
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private Integer candProcessId;
        private LocalDate processDate;
        private String status;
        private String description;
        private Integer candId;
        private Candidates candidate;
        private String candProcessName;
        private Integer screeningId;
        private Screening screening;
        private Integer locationId;
        private Locations location;
        private Integer phoneEvalId;
        private PhoneItvEval phoneItvEval;
        private Integer offerId;
        private Offers offer;
        private Integer recruitProcessId;
        private RecruitmentProcess recruitmentProcess;
        private String url;
        private String password;
        private Integer interviewerId;
        private Employees interviewer;
        private String note;

        public Builder candProcessId(Integer candProcessId) {
            this.candProcessId = candProcessId;
            return this;
        }

        public Builder processDate(LocalDate processDate) {
            this.processDate = processDate;
            return this;
        }
public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder candId(Integer candId) {
            this.candId = candId;
            return this;
        }

        public Builder candidate(Candidates candidate) {
            this.candidate = candidate;
            return this;
        }

        public Builder candProcessName(String candProcessName) {
            this.candProcessName = candProcessName;
            return this;
        }

        public Builder screeningId(Integer screeningId) {
            this.screeningId = screeningId;
            return this;
        }

        public Builder screening(Screening screening) {
            this.screening = screening;
            return this;
        }

        public Builder locationId(Integer locationId) {
            this.locationId = locationId;
            return this;
        }

        public Builder location(Locations location) {
            this.location = location;
            return this;
        }

        public Builder phoneEvalId(Integer phoneEvalId) {
            this.phoneEvalId = phoneEvalId;
            return this;
        }

        public Builder phoneItvEval(PhoneItvEval phoneItvEval) {
            this.phoneItvEval = phoneItvEval;
            return this;
        }

        public Builder offerId(Integer offerId) {
            this.offerId = offerId;
            return this;
        }

        public Builder offer(Offers offer) {
            this.offer = offer;
            return this;
        }

        public Builder recruitProcessId(Integer recruitProcessId) {
            this.recruitProcessId = recruitProcessId;
            return this;
        }

        public Builder recruitmentProcess(RecruitmentProcess recruitmentProcess) {
            this.recruitmentProcess = recruitmentProcess;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder interviewerId(Integer interviewerId) {
            this.interviewerId = interviewerId;
            return this;
        }

        public Builder interviewer(Employees interviewer) {
            this.interviewer = interviewer;
            return this;
        }

        public Builder note(String note) {
            this.note = note;
            return this;
        }

        public CandidateProcess build() {
            return new CandidateProcess(candProcessId, processDate, status, description, candId, candidate,
                    candProcessName,
                    screeningId, screening, locationId, location, phoneEvalId, phoneItvEval,
offerId, offer, recruitProcessId, recruitmentProcess, url, password,
                    interviewerId, interviewer, note);
        }
    }
}