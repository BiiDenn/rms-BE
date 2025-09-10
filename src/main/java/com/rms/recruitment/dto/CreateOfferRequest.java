package com.rms.recruitment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOfferRequest {
    @NotNull(message = "onboardDate is required")
    private LocalDate onboardDate;                    // Ngày onboard
    
    private String offerPosition;                     // Cấp độ thư mời
    
    @NotNull(message = "basicSalary is required")
    private BigDecimal basicSalary;                   // Lương cơ bản
    
    private BigDecimal allowances;                    // Phụ cấp
    
    private BigDecimal bonuses;                       // Thưởng theo năng suất & kết quả doanh thu tạm tính hằng tháng
    
    @NotNull(message = "totalFormalIncome is required")
    private BigDecimal totalFormalIncome;             // Tổng thu nhập chính thức
    
    @NotNull(message = "totalProbationaryIncome is required")
    private BigDecimal totalProbationaryIncome;       // Tổng thu nhập thử việc
    
    private Integer jobTitleId;                       // ID của job title
}
