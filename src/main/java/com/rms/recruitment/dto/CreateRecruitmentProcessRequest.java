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
    // Create new recruitment_process
    @NotNull(message = "recruitId is required")
    private Integer recruitId;
    
    private String recruitProcessStatus;
}
