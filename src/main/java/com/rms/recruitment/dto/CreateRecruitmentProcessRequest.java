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

    public @NotNull(message = "recruitId is required") Integer getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(@NotNull(message = "recruitId is required") Integer recruitId) {
        this.recruitId = recruitId;
    }

    public String getRecruitProcessStatus() {
        return recruitProcessStatus;
    }

    public void setRecruitProcessStatus(String recruitProcessStatus) {
        this.recruitProcessStatus = recruitProcessStatus;
    }
}
