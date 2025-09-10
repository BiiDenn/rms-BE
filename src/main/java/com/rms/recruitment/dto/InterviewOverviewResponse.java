package com.rms.recruitment.dto;

public class InterviewOverviewResponse {
    private InterviewOverviewResponseCandidate candidateInfo;
    private InterviewOverviewResponseSession interviewSessionInfo;
    private InterviewOverviewResponseInterviewer interviewerInfo;

    // Default constructor
    public InterviewOverviewResponse() {
    }

    // Constructor with all fields
    public InterviewOverviewResponse(InterviewOverviewResponseCandidate candidateInfo,
            InterviewOverviewResponseSession interviewSessionInfo,
            InterviewOverviewResponseInterviewer interviewerInfo) {
        this.candidateInfo = candidateInfo;
        this.interviewSessionInfo = interviewSessionInfo;
        this.interviewerInfo = interviewerInfo;
    }

    // Getters and Setters
    public InterviewOverviewResponseCandidate getCandidateInfo() {
        return candidateInfo;
    }

    public void setCandidateInfo(InterviewOverviewResponseCandidate candidateInfo) {
        this.candidateInfo = candidateInfo;
    }

    public InterviewOverviewResponseSession getInterviewSessionInfo() {
        return interviewSessionInfo;
    }

    public void setInterviewSessionInfo(InterviewOverviewResponseSession interviewSessionInfo) {
        this.interviewSessionInfo = interviewSessionInfo;
    }

    public InterviewOverviewResponseInterviewer getInterviewerInfo() {
        return interviewerInfo;
    }

    public void setInterviewerInfo(InterviewOverviewResponseInterviewer interviewerInfo) {
        this.interviewerInfo = interviewerInfo;
    }

    // Static method to create builder
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private InterviewOverviewResponseCandidate candidateInfo;
        private InterviewOverviewResponseSession interviewSessionInfo;
        private InterviewOverviewResponseInterviewer interviewerInfo;

        public Builder candidateInfo(InterviewOverviewResponseCandidate candidateInfo) {
            this.candidateInfo = candidateInfo;
            return this;
        }

        public Builder interviewSessionInfo(InterviewOverviewResponseSession interviewSessionInfo) {
            this.interviewSessionInfo = interviewSessionInfo;
            return this;
        }

        public Builder interviewerInfo(InterviewOverviewResponseInterviewer interviewerInfo) {
            this.interviewerInfo = interviewerInfo;
            return this;
        }

        public InterviewOverviewResponse build() {
            return new InterviewOverviewResponse(candidateInfo, interviewSessionInfo, interviewerInfo);
        }
    }
}
