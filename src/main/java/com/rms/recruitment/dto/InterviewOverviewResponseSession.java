package com.rms.recruitment.dto;

public class InterviewOverviewResponseSession {
    private String mode; // Online / Offline
    private String branch;
    private String time; // string for now
    private Integer interviewRounds;

    // Default constructor
    public InterviewOverviewResponseSession() {
    }

    // Constructor with all fields
    public InterviewOverviewResponseSession(String mode, String branch, String time, Integer interviewRounds) {
        this.mode = mode;
        this.branch = branch;
        this.time = time;
        this.interviewRounds = interviewRounds;
    }

    // Getters and Setters
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getInterviewRounds() {
        return interviewRounds;
    }

    public void setInterviewRounds(Integer interviewRounds) {
        this.interviewRounds = interviewRounds;
    }

    // Static method to create builder
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private String mode;
        private String branch;
        private String time;
        private Integer interviewRounds;

        public Builder mode(String mode) {
            this.mode = mode;
            return this;
        }

        public Builder branch(String branch) {
            this.branch = branch;
            return this;
        }

        public Builder time(String time) {
            this.time = time;
            return this;
        }

        public Builder interviewRounds(Integer interviewRounds) {
            this.interviewRounds = interviewRounds;
            return this;
        }

        public InterviewOverviewResponseSession build() {
            return new InterviewOverviewResponseSession(mode, branch, time, interviewRounds);
        }
    }
}
