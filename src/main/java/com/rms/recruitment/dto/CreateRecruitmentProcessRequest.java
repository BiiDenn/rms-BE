package com.rms.recruitment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecruitmentProcessRequest {
    // Always link to an existing recruitment_process
    @NotNull(message = "recruitProcessId is required")
    private Integer recruitProcessId;
    
    private String recruitProcessStatus;
}
