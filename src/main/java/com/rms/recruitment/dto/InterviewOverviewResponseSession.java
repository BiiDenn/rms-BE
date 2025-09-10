package com.rms.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewOverviewResponseSession {
    private String mode;           // Online / Offline
    private String branch;
    private String time;           // string for now
    private Integer interviewRounds;
}
