package com.rms.recruitment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferResponse {
    private String name;            // Tên từ bảng candidates
    private String jobTitle;        // Chức danh
    private String department;      // Phòng ban
    private String branch;          // Chi nhánh
}
