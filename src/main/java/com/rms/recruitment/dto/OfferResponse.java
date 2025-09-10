package com.rms.recruitment.dto;

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
public class OfferResponse {
    private Integer offerId;
    private LocalDate onboardDate;                    // Ngày onboard
    private String offerPosition;                     // Cấp độ thư mời
    private BigDecimal basicSalary;                   // Lương cơ bản
    private BigDecimal allowances;                    // Phụ cấp
    private BigDecimal bonuses;                       // Thưởng theo năng suất & kết quả doanh thu tạm tính hằng tháng
    private BigDecimal totalFormalIncome;             // Tổng thu nhập chính thức
    private BigDecimal totalProbationaryIncome;       // Tổng thu nhập thử việc
    private String status;                            // Trạng thái offer
}
