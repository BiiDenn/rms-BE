package com.rms.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentProcessResponse {
    private Integer id; // recruitProcessId
    private String recruitmentTitle;
    private String status;
    private String code;
    private String position;
    private String department;
}
