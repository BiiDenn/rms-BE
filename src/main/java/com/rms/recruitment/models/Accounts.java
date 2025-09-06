package com.rms.recruitment.models;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId")
    private Integer accountId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "status")
    private Byte status;

    @Column(name = "employeeId", unique = true)
    private Integer employeeId;

    @OneToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId", insertable = false, updatable = false)
    private Employees employee;

    // Constructors
    public Accounts() {
    }

    public Accounts(Integer accountId, String email, String password, Byte status, Integer employeeId,
            Employees employee) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.status = status;
        this.employeeId = employeeId;
        this.employee = employee;
    }

    // Builder pattern
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer accountId;
        private String email;
        private String password;
        private Byte status;
        private Integer employeeId;
        private Employees employee;

        public Builder accountId(Integer accountId) {
            this.accountId = accountId;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder status(Byte status) {
            this.status = status;
            return this;
        }

        public Builder employeeId(Integer employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder employee(Employees employee) {
            this.employee = employee;
            return this;
        }

        public Accounts build() {
            return new Accounts(accountId, email, password, status, employeeId, employee);
        }
    }

    // Getters and Setters
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
}
