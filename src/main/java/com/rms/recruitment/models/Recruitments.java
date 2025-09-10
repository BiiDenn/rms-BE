package com.rms.recruitment.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recruitments")
public class Recruitments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitId")
    private Integer recruitId;

    @Column(name = "recruitment_code", length = 255)
    private String recruitmentCode;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "durationInUse")
    private Integer durationInUse;

    @Column(name = "expandDays")
    private Integer expandDays;

    @Column(name = "minSalary", precision = 15, scale = 2)
    private BigDecimal minSalary;

    @Column(name = "maxSalary", precision = 15, scale = 2)
    private BigDecimal maxSalary;

    @Column(name = "quantity")
    private Integer quantity;

    @Lob
    @Column(name = "purpose")
    private String purpose;

    @Column(name = "currentWorkforce")
    private Integer currentWorkforce;

    @Lob
    @Column(name = "requirements")
    private String requirements;

    @Column(name = "divisionId")
    private Integer divisionId;

    @Column(name = "employeeId")
    private Integer employeeId;

    @ManyToOne
    @JoinColumn(name = "divisionId", referencedColumnName = "divisionId", insertable = false, updatable = false)
    private Divisions division;

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId", insertable = false, updatable = false)

    private Employees employee;
    @OneToMany(mappedBy = "recruitment")
    private List<RecruitmentHistory> recruitmentHistories;

    public Integer getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
    }

    public String getRecruitmentCode() {
        return recruitmentCode;
    }

    public void setRecruitmentCode(String recruitmentCode) {
        this.recruitmentCode = recruitmentCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Integer getDurationInUse() {
        return durationInUse;
    }

    public void setDurationInUse(Integer durationInUse) {
        this.durationInUse = durationInUse;
    }

    public Integer getExpandDays() {
        return expandDays;
    }

    public void setExpandDays(Integer expandDays) {
        this.expandDays = expandDays;
    }

    public BigDecimal getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    public BigDecimal getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Integer getCurrentWorkforce() {
        return currentWorkforce;
    }

    public void setCurrentWorkforce(Integer currentWorkforce) {
        this.currentWorkforce = currentWorkforce;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Divisions getDivision() {
        return division;
    }

    public void setDivision(Divisions division) {
        this.division = division;
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }

    public List<RecruitmentHistory> getRecruitmentHistories() {
        return recruitmentHistories;
    }

    public void setRecruitmentHistories(List<RecruitmentHistory> recruitmentHistories) {
        this.recruitmentHistories = recruitmentHistories;
    }
}
