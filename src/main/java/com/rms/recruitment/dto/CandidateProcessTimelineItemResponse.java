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
public class CandidateProcessTimelineItemResponse {
    private Integer id;
    private LocalDate processDate;
    private Integer typeId;
    private String typeName;
    private String status;
    private String description;
    private Integer interviewerId;
    private String interviewerName;
    private String interviewerEmail;
    private Integer locationId;
    private String locationName;
    private String url;
    private String note;
}
