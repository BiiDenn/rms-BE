package com.rms.recruitment.dto;

public class InterviewOverviewResponseInterviewer {
    private String interviewerName;
    private String interviewerTitle;
    private String interviewerEmail;

    // Default constructor
    public InterviewOverviewResponseInterviewer() {
    }

    // Constructor with all fields
    public InterviewOverviewResponseInterviewer(String interviewerName, String interviewerTitle,
            String interviewerEmail) {
        this.interviewerName = interviewerName;
        this.interviewerTitle = interviewerTitle;
        this.interviewerEmail = interviewerEmail;
    }

    // Getters and Setters
    public String getInterviewerName() {
        return interviewerName;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }

    public String getInterviewerTitle() {
        return interviewerTitle;
    }

    public void setInterviewerTitle(String interviewerTitle) {
        this.interviewerTitle = interviewerTitle;
    }

    public String getInterviewerEmail() {
        return interviewerEmail;
    }

    public void setInterviewerEmail(String interviewerEmail) {
        this.interviewerEmail = interviewerEmail;
    }

    // Static method to create builder
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private String interviewerName;
        private String interviewerTitle;
        private String interviewerEmail;

        public Builder interviewerName(String interviewerName) {
            this.interviewerName = interviewerName;
            return this;
        }

        public Builder interviewerTitle(String interviewerTitle) {
            this.interviewerTitle = interviewerTitle;
            return this;
        }

        public Builder interviewerEmail(String interviewerEmail) {
            this.interviewerEmail = interviewerEmail;
            return this;
        }

        public InterviewOverviewResponseInterviewer build() {
            return new InterviewOverviewResponseInterviewer(interviewerName, interviewerTitle, interviewerEmail);
        }
    }
}
