package com.rms.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewOverviewResponseCandidate {
    private String fullName;
    private LocalDate dateOfBirth;
    private String maritalStatus;
    private LocalDate expectedOnboardDate;
    private String jobTitle;
    private String department;
}
