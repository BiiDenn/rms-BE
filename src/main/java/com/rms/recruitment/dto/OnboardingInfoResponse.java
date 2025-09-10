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
public class OnboardingInfoResponse {
    private String fullName;                    // Họ và tên từ candidates
    private LocalDate dateOfBirth;              // Ngày sinh từ candidates
    private String maritalStatus;               // Trạng thái hôn nhân từ candidates
    private LocalDate expectedOnboardDate;      // Ngày dự kiến onboard từ offers
    private String jobTitle;                    // Chức danh từ recruitments
}
