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
    @NotNull(message = "Recruit ID is required")
    private Integer recruitId;
    
    private String recruitProcessStatus;
}
