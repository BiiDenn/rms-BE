package com.rms.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewOverviewResponseInterviewer {
    private String interviewerName;
    private String interviewerTitle;
    private String interviewerEmail;
}
