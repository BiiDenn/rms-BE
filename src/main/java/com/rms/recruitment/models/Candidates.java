package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidates")
public class Candidates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candId")
    private Integer candId;

    @Column(name = "firstName", length = 100)
    private String firstName;

    @Column(name = "lastName", length = 100)
    private String lastName;

    public Integer getCandId() {
        return candId;
    }

    public void setCandId(Integer candId) {
        this.candId = candId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getSubPhone() {
        return subPhone;
    }

    public void setSubPhone(String subPhone) {
        this.subPhone = subPhone;
    }

    public String getMainEmail() {
        return mainEmail;
    }

    public void setMainEmail(String mainEmail) {
        this.mainEmail = mainEmail;
    }

    public String getSubEmail() {
        return subEmail;
    }

    public void setSubEmail(String subEmail) {
        this.subEmail = subEmail;
    }

    public String getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(String referredBy) {
        this.referredBy = referredBy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getJobTitleId() {
        return jobTitleId;
    }

    public void setJobTitleId(Integer jobTitleId) {
        this.jobTitleId = jobTitleId;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public JobTitles getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitles jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public LocalDate getExpectedOnboardDate() {
        return expectedOnboardDate;
    }

    public void setExpectedOnboardDate(LocalDate expectedOnboardDate) {
        this.expectedOnboardDate = expectedOnboardDate;
    }

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "nationality", length = 100)
    private String nationality;

    @Column(name = "mainPhone", length = 50)
    private String mainPhone;

    @Column(name = "subPhone", length = 50)
    private String subPhone;

    @Column(name = "mainEmail", length = 255)
    private String mainEmail;

    @Column(name = "subEmail", length = 255)
    private String subEmail;

    @Column(name = "referredBy", length = 255)
    private String referredBy;

    @Column(name = "primaryLanguage", length = 100)
    private String primaryLanguage;

    @Column(name = "secondaryLanguage", length = 100)
    private String secondaryLanguage;

    @Column(name = "recruitmentSource", length = 255)
    private String recruitmentSource;

    @Column(name = "maritalStatus", length = 50)
    private String maritalStatus;

    @Lob
    @Column(name = "note")
    private String note;

    @Column(name = "employeeId")
    private Integer employeeId;

    @Column(name = "jobTitleId")
    private Integer jobTitleId;

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employees employee;

    @ManyToOne
    @JoinColumn(name = "jobTitleId", referencedColumnName = "jobTitleId", insertable = false, updatable = false)
    private JobTitles jobTitle;

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public String getSecondaryLanguage() {
        return secondaryLanguage;
    }

    public void setSecondaryLanguage(String secondaryLanguage) {
        this.secondaryLanguage = secondaryLanguage;
    }

    public String getRecruitmentSource() {
        return recruitmentSource;
    }

    public void setRecruitmentSource(String recruitmentSource) {
        this.recruitmentSource = recruitmentSource;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}