package com.rms.recruitment.models;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeId")
    private Integer employeeId;

    @Column(name = "firstName", length = 100)
    private String firstName;

    @Column(name = "lastName", length = 100)
    private String lastName;

    @Lob
    @Column(name = "note")
    private String note;

    @Column(name = "divisionId")
    private Integer divisionId;

    @ManyToOne
    @JoinColumn(name = "divisionId", referencedColumnName = "divisionId", insertable = false, updatable = false)
    private Divisions division;

    @OneToOne(mappedBy = "employee")
    private Accounts account;

    // Constructors
    public Employees() {
    }

    public Employees(Integer employeeId, String firstName, String lastName, String note, Integer divisionId,
            Divisions division, Accounts account) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.note = note;
        this.divisionId = divisionId;
        this.division = division;
        this.account = account;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer employeeId;
        private String firstName;
        private String lastName;
        private String note;
        private Integer divisionId;
        private Divisions division;
        private Accounts account;

        public Builder employeeId(Integer employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder note(String note) {
            this.note = note;
            return this;
        }

        public Builder divisionId(Integer divisionId) {
            this.divisionId = divisionId;
            return this;
        }

        public Builder division(Divisions division) {
            this.division = division;
            return this;
        }

        public Builder account(Accounts account) {
            this.account = account;
            return this;
        }

        public Employees build() {
            return new Employees(employeeId, firstName, lastName, note, divisionId, division, account);
        }
    }

    // Getters and Setters
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Integer divisionId) {
        this.divisionId = divisionId;
    }

    public Divisions getDivision() {
        return division;
    }

    public void setDivision(Divisions division) {
        this.division = division;
    }

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }
}