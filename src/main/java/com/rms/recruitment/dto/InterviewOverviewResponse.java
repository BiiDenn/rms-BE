package com.rms.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewOverviewResponse {
    private InterviewOverviewResponseCandidate candidateInfo;
    private InterviewOverviewResponseSession interviewSessionInfo;
    private InterviewOverviewResponseInterviewer interviewerInfo;
}
